package club.pinea.school.mapper;

import java.util.List;

import club.pinea.school.model.Teacher;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

	List<Teacher> queryAllTeachers();
}