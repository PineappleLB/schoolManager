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
import club.pinea.school.model.SysUser;
import club.pinea.school.service.AccountService;
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

	public JSONObject createJSONMenu(Map<String, List<Map<String, Object>>> data) {
		
		return null;
	}

	public JSONArray createTestMenu() {
		String arr = "[{\"id\":1,\"pid\":0,\"mname\":\"档案管理\",\"url\":\"/archives.html\",\"pname\":\"\"},{\"id\":2,\"pid\":0,\"mname\":\"班级管理\",\"url\":\"/classroom.html\",\"pname\":\"\"},{\"id\":3,\"pid\":0,\"mname\":\"缴费管理\",\"url\":\"/payments.html\",\"pname\":\"\"},{\"id\":4,\"pid\":0,\"mname\":\"课程管理\",\"url\":\"/courses.html\",\"pname\":\"\"},{\"id\":5,\"pid\":0,\"mname\":\"成绩管理\",\"url\":\"/scores.html\",\"pname\":\"\"}]";
		JSONArray jsonArr = JSONArray.parseArray(arr);
//		System.out.println(jsonArr);
//		JSONObject result =new JSONObject();
//		for(Object menu:jsonArr){
//			JSONObject map = (JSONObject)menu;
//			String pid = map.getString("pid");
//			String pname = map.getString("mname");
//			String key =pid+"-"+pname;
//			JSONArray mlist = result.getJSONArray(key);
//			if(mlist==null){
//				mlist = new JSONArray();
//			}
//			mlist.add(map);
//			result.put(key, mlist);
//		}
		return jsonArr;
	}
	
}
