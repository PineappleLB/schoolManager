package club.pinea.school.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import club.pinea.school.common.BaseController;
import club.pinea.school.config.ShiroPermissionCode;
import club.pinea.school.model.SysUser;
import club.pinea.school.service.AccountService;
import club.pinea.school.shiro.annotation.Permission;
import club.pinea.school.util.AjaxResult;
import club.pinea.school.util.ShiroUtil;

@RestController
@RequestMapping("/main")
public class MainController extends BaseController{
	
	@Autowired
	private AccountService accountService;
	
	@ResponseBody
	@RequestMapping("/index")
	public AjaxResult index() {
		return new AjaxResult().success(new JSONObject().toString());
	}

	@ResponseBody
	@RequestMapping("/menuList")
	@Permission(ShiroPermissionCode.MENU_LIST)
	public AjaxResult menuList(@RequestParam("sessionId")String sessionId) {
		System.out.println("请求菜单列表");
		SysUser user = ShiroUtil.getUser();
		List<Map<String, Object>> data = accountService.getUserAllMenuId(user.getId(), user.getRoleId());
		return new AjaxResult().addSuccess("").setData(createJSONMenu(data));
	}

	/**
	 * 将list<map>转换为jsonArray
	 * @param data
	 * @return
	 */
	public JSONArray createJSONMenu(List<Map<String, Object>> data) {
		if(data != null && !data.isEmpty()) {
			JSONArray arr = JSONArray.parseArray(JSONArray.toJSONString(data));
			return arr;
		}
		return null;
	}

}
