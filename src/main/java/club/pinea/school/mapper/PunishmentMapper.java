package club.pinea.school.mapper;

import club.pinea.school.model.Punishment;

public interface PunishmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Punishment record);

    int insertSelective(Punishment record);

    Punishment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Punishment record);

    int updateByPrimaryKey(Punishment record);
}