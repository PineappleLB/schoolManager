package club.pinea.school.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import club.pinea.school.model.SysUser;
import club.pinea.school.service.AccountService;
import club.pinea.school.util.ShiroUtil;

public class ShiroDBRealm extends AuthorizingRealm{
	
	private static final Logger log = LoggerFactory.getLogger(AuthorizingRealm.class);
	@Autowired
	private AccountService accountServcie;
	

	/**
	 * shior-dbrealm用户角色认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if(principals==null){
			return info;
		}
		SysUser shiroUser =  (SysUser) principals.getPrimaryPrincipal();
		if(shiroUser==null){
			return info;
		}
		Integer userId = shiroUser.getId();
		if(userId ==null){
			return info;
		}
		//查询用户的所有权限编码
		Set<String> roleSetCode = new HashSet<>();
		List<String> userMenuCodeList = accountServcie.getMenuCodeList(userId, shiroUser.getRoleId());
		for(String code:userMenuCodeList){
			roleSetCode.add(code);
		}
		info.addRoles(roleSetCode);
		return info;
	}

	/**
	 * shior-dbrealm用户权限认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		log.info("Shiro登录认证启动");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String inputusername = token.getUsername();
		String inputpwd = String.valueOf(token.getPassword());
		if(StringUtils.isEmpty(inputusername)||StringUtils.isEmpty(inputpwd)){
			return null;
		}
		//后台管理人员登录realm
		SysUser user = accountServcie.querySysuserInfoByAccount(inputusername);
		if(user ==null){
			throw new IncorrectCredentialsException();
		}
		String dbusername = user.getAccount();
		String dbpassword = user.getPassword();
		/**判断输入的密码是否相等*/
		if(!dbusername.equals(inputusername)||!dbpassword.equals(ShiroUtil.md5(inputpwd, user.getSalt()))){
			throw new IncorrectCredentialsException();
		}
		
		return new SimpleAuthenticationInfo(user,inputpwd,getName());
	}

}
