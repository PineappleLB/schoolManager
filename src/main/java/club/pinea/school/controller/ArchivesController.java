package club.pinea.school.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import club.pinea.school.common.BaseController;
import club.pinea.school.config.CoursesEnum;
import club.pinea.school.model.SysUser;
import club.pinea.school.service.ArchivesService;
import club.pinea.school.util.AjaxResult;
import club.pinea.school.util.ShiroUtil;

@RestController
@RequestMapping("/archives")
public class ArchivesController extends BaseController {
	
	@Autowired
	private ArchivesService archivesService;
	
	@RequestMapping("/list")
	@ResponseBody
	public AjaxResult archivesList(@RequestParam Map<String,Object> param) {
		SysUser user = ShiroUtil.getUser();
		//学生身份
		if(user.getRoleId() == 3) {
			param.put("id", user.getId());
		}
//		if(param.get("specialtyType") != null && !"".equals(param.get("specialtyType"))) {
//			param.put("specialty", 
//					(CoursesEnum.values()[Integer.parseInt(param.get("specialtyType")+"") - 1]));
//		}
		List<Map<String, Object>> data = archivesService.queryList(param);
		return new AjaxResult().setData(data);
	}
	

}
