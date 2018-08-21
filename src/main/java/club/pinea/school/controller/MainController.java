package club.pinea.school.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import club.pinea.school.common.BaseController;
import club.pinea.school.util.AjaxResult;

@RestController
@RequestMapping("/main")
public class MainController extends BaseController{
	
	@ResponseBody
	@RequestMapping("/index")
	public AjaxResult index() {
		return new AjaxResult().success(new JSONObject().toString());
	}

}
