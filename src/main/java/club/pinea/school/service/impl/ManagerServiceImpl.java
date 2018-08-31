package club.pinea.school.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.pinea.school.mapper.ManagerMapper;
import club.pinea.school.model.Manager;
import club.pinea.school.service.ManagerService;

@Service("managerServiceImpl")
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerMapper managerMapper;
	
	@Override
	public List<Manager> queryAllManagers() {
		try {
			return managerMapper.queryAllManagers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

}
