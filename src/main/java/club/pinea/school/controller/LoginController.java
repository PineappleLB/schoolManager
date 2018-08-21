package club.pinea.school.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import club.pinea.school.common.BaseController;
import club.pinea.school.service.JedisService;
import club.pinea.school.util.AjaxResult;
import club.pinea.school.util.ShiroUtil;
import club.pinea.school.util.VerifyCode;

@RestController
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private JedisService jedisService;
	
	@RequestMapping("/captcha")
	public void captcha(@RequestParam("sessionId")String sessionId, HttpSession session, HttpServletResponse resp) {
		try {
			VerifyCode code = new VerifyCode();
			BufferedImage img = code.getImage();
			VerifyCode.output(img, resp.getOutputStream());
			String text = code.getText();
			jedisService.setImgCode(sessionId, text);
			log.info("请求验证码成功--验证码："+text);
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult login(@RequestParam("account")String account, @RequestParam("password")String password, 
			@RequestParam("imgCode")String code, @RequestParam("sessionId")String sessionId,
			HttpSession session) {
		//校验图片验证码
		if(!jedisService.validateImgCode(sessionId, code)) {
			return new AjaxResult().addError("图片验证码不正确！");
		}
		//账号密码判空
		if(StringUtils.isEmpty(account)||StringUtils.isEmpty(password)){
			return new AjaxResult().addError("账号密码不能为空!");
		}
		//shior登录
		Subject subject = ShiroUtil.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(account, password);
		token.setRememberMe(true);
		try {
			//dbrealm进行认证
			subject.login(token);
			log.info("account:"+account+" 登录系统成功！");
		//dbrealm认证失败
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			log.error("account:"+account+" 账号或密码错误");
			return new AjaxResult().addError("账号或密码错误");
		//其他错误
		} catch (Exception e) {
			e.printStackTrace();
			log.error("account:"+account+" 未知错误");
			return new AjaxResult().addError("账号或密码错误");
		}
		return new AjaxResult().success("登录成功");
	}
	
	

}
