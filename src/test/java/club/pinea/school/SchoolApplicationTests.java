package club.pinea.school;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import club.pinea.school.mapper.SysUserMapper;
import club.pinea.school.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolApplicationTests {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Test
	public void test() {
		System.out.println(accountService.getUserAllMenuId(1, 2));
		System.out.println(sysUserMapper.getAllSysMenuList(2));
	}
	
//	@Test
//	public void contextLoads() {
//		String arr = "[{\"id\":1,\"pid\":0,\"mname\":\"档案管理\",\"url\":\"/archives/\",\"pname\":\"\"},{\"id\":2,\"pid\":0,\"mname\":\"班级管理\",\"url\":\"/classroom/\",\"pname\":\"\"},{\"id\":3,\"pid\":0,\"mname\":\"缴费管理\",\"url\":\"/payments/\",\"pname\":\"\"},{\"id\":4,\"pid\":0,\"mname\":\"课程管理\",\"url\":\"/courses/\",\"pname\":\"\"},{\"id\":5,\"pid\":0,\"mname\":\"成绩管理\",\"url\":\"/scores/\",\"pname\":\"\"}]";
//		JSONArray jsonArr = JSONArray.parseArray(arr);
////		System.out.println(jsonArr);
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
//		System.out.println(result.toJSONString());
//	}

}
