package club.pinea.school.shiro.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAop {
	//拦截所有controller包中的方法
	@Pointcut(value = "execution(* club.pinea.school.controller..*.*(..))")
	private void cutController() {
	}
	
	

}
