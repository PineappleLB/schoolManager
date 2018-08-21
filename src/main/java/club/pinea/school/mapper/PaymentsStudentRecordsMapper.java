package club.pinea.school.mapper;

import club.pinea.school.model.PaymentsStudentRecords;

public interface PaymentsStudentRecordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PaymentsStudentRecords record);

    int insertSelective(PaymentsStudentRecords record);

    PaymentsStudentRecords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PaymentsStudentRecords record);

    int updateByPrimaryKey(PaymentsStudentRecords record);
}