package club.pinea.school.mapper;

import club.pinea.school.model.Payments;

public interface PaymentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Payments record);

    int insertSelective(Payments record);

    Payments selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Payments record);

    int updateByPrimaryKey(Payments record);
}