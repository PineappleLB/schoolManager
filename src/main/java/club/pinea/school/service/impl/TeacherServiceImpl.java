package club.pinea.school.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.pinea.school.mapper.TeacherMapper;
import club.pinea.school.model.Teacher;
import club.pinea.school.service.TeacherService;

@Service("teacherServiceImpl")
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherMapper teacherMapper;
	
	@Override
	public List<Teacher> queryAllTeachers() {
		try {
			return teacherMapper.queryAllTeachers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

}
