package club.pinea.school.mapper;

import java.util.List;
import java.util.Map;

import club.pinea.school.model.ClassRoom;

public interface ClassRoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassRoom record);

    int insertSelective(ClassRoom record);

    ClassRoom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassRoom record);

    int updateByPrimaryKey(ClassRoom record);

	List<Map<String, List<String>>> queryAllGrades();

	List<ClassRoom> queryAllClassrooms();
}