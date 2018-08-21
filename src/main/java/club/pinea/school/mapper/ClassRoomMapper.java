package club.pinea.school.mapper;

import club.pinea.school.model.ClassRoom;

public interface ClassRoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassRoom record);

    int insertSelective(ClassRoom record);

    ClassRoom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassRoom record);

    int updateByPrimaryKey(ClassRoom record);
}