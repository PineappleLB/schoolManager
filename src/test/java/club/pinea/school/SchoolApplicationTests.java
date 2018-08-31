package club.pinea.school;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import club.pinea.school.mapper.SysUserMapper;
import club.pinea.school.service.AccountService;
import club.pinea.school.service.ClassroomService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolApplicationTests {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ClassroomService classroomService;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Test
	public void test() {
		System.out.println(accountService.getUserAllMenuId(1, 2));
		System.out.println(sysUserMapper.getAllSysMenuList(2));
	}
	
	@Test
	public void contextLoads() {
		List<Map<String, List<String>>> lists = classroomService.queryAllGrades();
		System.out.println(lists);
	}

}
