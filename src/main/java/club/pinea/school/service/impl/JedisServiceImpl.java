package club.pinea.school.service.impl;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

import club.pinea.school.config.JedisCacheConfig;
import club.pinea.school.model.SysUser;
import club.pinea.school.service.JedisService;
import club.pinea.school.util.ShiroUtil;
import redis.clients.jedis.JedisCluster;

@Service("jedisServiceImpl")
public class JedisServiceImpl implements JedisService {
	
	private Logger log = LoggerFactory.getLogger(JedisServiceImpl.class);
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@Value("${config.imgcode.timeout}")
	private String imgcodeTimeout;
	
	@Override
	public boolean validateImgCode(String sessionId, String imgCode) {
		if(imgCode == null || "".equals(imgCode)) {
			return false;
		}
		String code = jedisCluster.get(JedisCacheConfig.IMG_CODE.getMsg(sessionId));
		return imgCode.toUpperCase().equals(code);
	}

	@Override
	public void setImgCode(String sessionId, String imgCode) {
		jedisCluster.setex(JedisCacheConfig.IMG_CODE.getMsg(sessionId), Integer.parseInt(imgcodeTimeout), imgCode.toUpperCase());
	}

	@Override
	public SysUser authUser(String sessionId) {
		String str = jedisCluster.get(sessionId);
		if(!StringUtils.isEmpty(str)) {
			try {
				//解析json对象
				SysUser user = JSONObject.parseObject(str,SysUser.class);
				if(user != null) {
					Subject subject = ShiroUtil.getSubject();
					UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
					token.setRememberMe(true);
					try {
						//dbrealm进行认证
						subject.login(token);
						log.info("account:"+user.getAccount()+" 跨服务器登录系统成功！");
						return user;
					//dbrealm认证失败
					} catch (IncorrectCredentialsException e) {
						e.printStackTrace();
						log.error("account:"+user.getAccount()+" 跨服务器登录系统失败！");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int logout(String sessionId) {
		long result = jedisCluster.del(sessionId);
		return (int)result;
	}

	@Override
	public SysUser getUser(String sessionId) {
		String str = jedisCluster.get(sessionId);
		if(!StringUtils.isEmpty(str)) {
			try {
				//解析json对象
				SysUser user = JSONObject.parseObject(str,SysUser.class);
				return user;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
