package club.pinea.school.shiro.aop;

import java.lang.reflect.Method;

import javax.naming.NoPermissionException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import club.pinea.school.common.exception.NoUserException;
import club.pinea.school.model.SysUser;
import club.pinea.school.shiro.annotation.Permission;
import club.pinea.school.util.ShiroUtil;

@Aspect
@Component
public class PermissionAop {
	
	@Pointcut(value = "@annotation(club.pinea.school.shiro.annotation.Permission)")
	private void cutPermission() {
	}

	//拦截带有@Permission注解的请求
	@Around("cutPermission()")
	public Object doPermission(ProceedingJoinPoint point) throws Throwable {
		MethodSignature ms = (MethodSignature) point.getSignature();
		Method method = ms.getMethod();
		Permission permission = method.getAnnotation(Permission.class);
		Object permissions = permission.value();
		// 如果没有权限验证直接通过
		if (permissions == null) {
			point.proceed();
		}
		SysUser user = ShiroUtil.getUser();
		if(user == null){
			throw new NoUserException();
		}
		//redis中有用户数据，shiro中没有，以redis中数据为准，重新认证
		else if(user == null) {
			jedisService.authUser(JedisCacheConfig.USER_PREFIX.getMsg(point.getArgs()[0]+""));
		}
		//更新用户过期时间
		jedisService.updateUserExpiryTime(JedisCacheConfig.USER_PREFIX.getMsg(point.getArgs()[0]+""));
		//判断是否含有该角色code
		if (!ShiroUtil.hasRole(permissions.toString())) {
			throw new NoPermissionException();
		}
		return point.proceed();
	}
	
}
