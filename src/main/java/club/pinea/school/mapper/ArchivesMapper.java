package club.pinea.school.mapper;

import java.util.List;
import java.util.Map;

import club.pinea.school.model.Archives;

public interface ArchivesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Archives record);

    int insertSelective(Archives record);

    Archives selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Archives record);

    int updateByPrimaryKey(Archives record);

    /**
     * 查询学生档案集合
     * @param param
     * @return
     */
	List<Map<String, Object>> queryArchivesList(Map<String, Object> param);
}