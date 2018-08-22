package club.pinea.school.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import club.pinea.school.common.BaseController;
import club.pinea.school.util.AjaxResult;

@RestController
public class CommonController extends BaseController{

	@ResponseBody
	@RequestMapping("/noUser")
	public AjaxResult noUserResult(@RequestParam(value="sessionId",required=false)String sessionId) {
		return new AjaxResult().noUserError("");
	}
	
}
