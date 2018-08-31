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
import club.pinea.school.config.CoursesEnum;
import club.pinea.school.config.FeedsType;
import club.pinea.school.config.NationEnum;
import club.pinea.school.config.PunishmentTypeEnum;
import club.pinea.school.model.ClassRoom;
import club.pinea.school.model.Manager;
import club.pinea.school.model.Teacher;
import club.pinea.school.service.ClassroomService;
import club.pinea.school.service.ManagerService;
import club.pinea.school.service.TeacherService;
import club.pinea.school.util.AjaxResult;

@RestController
public class CommonController extends BaseController {
	
	@Autowired
	private ClassroomService classroomService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private ManagerService managerService;

	@ResponseBody
	@RequestMapping("/noUser")
	public AjaxResult noUserResult(@RequestParam(value = "sessionId", required = false) String sessionId) {
		return new AjaxResult().noUserError("");
	}

	/**
	 * 请求选择性数据列表
	 * 
	 * @param sessionId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/enumList")
	public AjaxResult enumList(@RequestParam(value = "sessionId", required = false) String sessionId,
			@RequestParam("requestList") String requestList) {
		JSONArray arr = JSONArray.parseArray(requestList);
		JSONObject resultObj = new JSONObject();
		for (Object object : arr) {
			switch (object+"") {
			case "courses":
				resultObj.put("courses", coursesEnums());
				break;
			case "specialties":
				resultObj.put("courses", coursesEnums());
				break;
			case "nations":
				resultObj.put("nations", nationsEnums());
				break;
			case "grades":
				resultObj.put("grades", gradesEnums());
				break;
			case "classes":
				resultObj.put("classes", classesEnums());
				break;
			case "teachers":
				resultObj.put("teachers", teachersEnums());
				break;
			case "managers":
				resultObj.put("managers", managersEnums());
				break;
			case "paymentsTypes":
				resultObj.put("paymentsTypes", paymentsTypesEnums());
				break;
			case "punishmentTypes":
				resultObj.put("punishmentTypes", punishmentTypesEnums());
				break;
			default:
				break;
			}
		}
		return new AjaxResult().addSuccess("ok").setData(resultObj);
	}

	private JSONArray classesEnums() {
		List<ClassRoom> classromms = classroomService.queryAllClassrooms();
		JSONArray arr = JSONArray.parseArray(JSONArray.toJSONString(classromms));
		return arr;
	}

	private JSONArray punishmentTypesEnums() {
		PunishmentTypeEnum[] types = PunishmentTypeEnum.values();
		JSONArray resultArr = new JSONArray();
		for (int i = 0; i < types.length; i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", i + 1);
			obj.put("name", types[i].getName());
			resultArr.add(obj);
		}
		return resultArr;
	}

	private JSONArray paymentsTypesEnums() {
		FeedsType[] types = FeedsType.values();
		JSONArray resultArr = new JSONArray();
		for (int i = 0; i < types.length; i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", i + 1);
			obj.put("name", types[i].getName());
			resultArr.add(obj);
		}
		return resultArr;
	}

	private JSONArray managersEnums() {
		List<Manager> managers = managerService.queryAllManagers();
		JSONArray arr = JSONArray.parseArray(JSONArray.toJSONString(managers));
		return arr;
	}

	private JSONArray teachersEnums() {
		List<Teacher> teachers = teacherService.queryAllTeachers();
		JSONArray arr = JSONArray.parseArray(JSONArray.toJSONString(teachers));
		return arr;
	}


	private JSONArray gradesEnums() {
		List<Map<String, List<String>>> lists = classroomService.queryAllGrades();
		JSONArray arr = JSONArray.parseArray(JSONArray.toJSONString(lists));
		return arr;
	}

	/**
	 * 获取民族枚举列表
	 * @return
	 */
	private JSONArray nationsEnums() {
		NationEnum []nations = NationEnum.values();
		JSONArray resultArr = new JSONArray();
		for (int i = 0; i < nations.length; i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", nations[i].getKey());
			obj.put("name", nations[i].getDesc());
			resultArr.add(obj);
		}
		return resultArr;
	}

	/**
	 * 获取学科/专业枚举列表
	 * @return
	 */
	private JSONArray coursesEnums() {
		CoursesEnum [] enums = CoursesEnum.values();
		JSONArray resultArr = new JSONArray();
		for (int i = 0; i < enums.length; i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", i + 1);
			obj.put("name", enums[i].getName());
			resultArr.add(obj);
		}
		return resultArr;
	}
	
	public String formatNumberToChinese(int i) {
		String result = "";
		switch(i) {
		case 1:
			result = "一";
			break;
		case 2:
			result = "二";
			break;
		case 3:
			result = "三";
			break;
		case 4:
			result = "四";
			break;
		case 5:
			result = "五";
			break;
		case 6:
			result = "六";
			break;
		case 7:
			result = "七";
			break;
		case 8:
			result = "八";
			break;
		case 9:
			result = "九";
			break;
		}
		return result;
	}
}
