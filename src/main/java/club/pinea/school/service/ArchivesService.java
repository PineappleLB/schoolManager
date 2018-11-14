package club.pinea.school.service;

import java.util.List;
import java.util.Map;

public interface ArchivesService {

	/**
	 * 查询学生档案信息
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> queryList(Map<String, Object> param);

}
