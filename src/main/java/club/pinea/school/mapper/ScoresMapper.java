package club.pinea.school.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import club.pinea.school.model.Scores;

public interface ScoresMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Scores record);

    int insertSelective(Scores record);

    Scores selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Scores record);

    int updateByPrimaryKey(Scores record);
    
    /**
     * 查询学生成绩
     * @return
     */
    List<Map<String, Object>> selectStudentScores(@Param("name")String name,
    		@Param("specialty")String specialty, @Param("scope")Integer scope);
}