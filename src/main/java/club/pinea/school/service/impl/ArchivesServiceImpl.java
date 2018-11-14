package club.pinea.school.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.pinea.school.mapper.ArchivesMapper;
import club.pinea.school.service.ArchivesService;

@Service("archivesServiceImpl")
public class ArchivesServiceImpl implements ArchivesService {

	@Autowired
	private ArchivesMapper mapper;
	
	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> param) {
		try {
			return mapper.queryArchivesList(param);
		} catch (Exception e) {
		}
		return new ArrayList<>();
	}

}
