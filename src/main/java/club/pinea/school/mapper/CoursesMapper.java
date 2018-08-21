package club.pinea.school.mapper;

import club.pinea.school.model.Courses;

public interface CoursesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Courses record);

    int insertSelective(Courses record);

    Courses selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Courses record);

    int updateByPrimaryKey(Courses record);
}