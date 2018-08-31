package club.pinea.school.service;

import java.util.List;
import java.util.Map;

import club.pinea.school.model.ClassRoom;

public interface ClassroomService {

	/**
	 * 查询所有年级班级信息
	 * @return
	 */
	List<Map<String, List<String>>> queryAllGrades();

	/**
	 * 查询所有班级信息
	 * @return
	 */
	List<ClassRoom> queryAllClassrooms();
	

}
