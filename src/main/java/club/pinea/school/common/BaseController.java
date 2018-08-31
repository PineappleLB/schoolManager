package club.pinea.school.common;

import java.io.IOException;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import club.pinea.school.common.exception.NoUserException;
import club.pinea.school.util.AjaxResult;

/**
 * 处理controller异常
 * @author Administrator
 *
 */
public class BaseController {
	
	
	//处理异常
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public AjaxResult exceptionHandler(Exception ex, HttpServletResponse response, HttpServletRequest request) throws IOException  {
		ex.printStackTrace();
		if(ex instanceof NoPermissionException) {
			return new AjaxResult().noPermissionError("");
		} else if(ex instanceof NoUserException) {
			return new AjaxResult().noUserError("");
		}
		return new AjaxResult().addError(ex);
	}
	
}
