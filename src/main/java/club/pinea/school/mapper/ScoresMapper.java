package club.pinea.school.mapper;

import club.pinea.school.model.Scores;

public interface ScoresMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Scores record);

    int insertSelective(Scores record);

    Scores selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Scores record);

    int updateByPrimaryKey(Scores record);
}