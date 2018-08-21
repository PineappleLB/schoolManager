package club.pinea.school.mapper;

import club.pinea.school.model.AwardInfo;

public interface AwardInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AwardInfo record);

    int insertSelective(AwardInfo record);

    AwardInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AwardInfo record);

    int updateByPrimaryKey(AwardInfo record);
}