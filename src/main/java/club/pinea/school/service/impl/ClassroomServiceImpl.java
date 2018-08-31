package club.pinea.school.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.pinea.school.mapper.ClassRoomMapper;
import club.pinea.school.model.ClassRoom;
import club.pinea.school.service.ClassroomService;

@Service("classroomServiceImpl")
public class ClassroomServiceImpl implements ClassroomService {

	@Autowired
	private ClassRoomMapper classroomMapper;
	
	@Override
	public List<Map<String, List<String>>> queryAllGrades() {
		try {
			return classroomMapper.queryAllGrades();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public List<ClassRoom> queryAllClassrooms() {
		try {
			return classroomMapper.queryAllClassrooms();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}


}
