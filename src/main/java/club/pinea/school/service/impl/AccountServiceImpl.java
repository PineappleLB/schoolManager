package club.pinea.school.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.pinea.school.mapper.SysUserMapper;
import club.pinea.school.model.SysMenu;
import club.pinea.school.model.SysUser;
import club.pinea.school.service.AccountService;

@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private SysUserMapper sysUserMapper;

	//根据id查询系统用户信息
	@Override
	public SysUser querySysuserInfoByPrimaryKey(int id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	//根据账号查询系统用户信息
	@Override
	public SysUser querySysuserInfoByAccount(String account) {
		return sysUserMapper.selectByAccount(account);
	}

	//根据id和角色id查询所有的权限code
	@Override
	public List<String> getMenuCodeList(Integer userId, Integer roleId) {
		List<String> menuCodeList = new ArrayList<String>();
		//查询角色的拆弹信息
		List<SysMenu> menuList = sysUserMapper.getSysMenuByRoleId(roleId);
		for(SysMenu menu : menuList){
			menuCodeList.add(menu.getCode()==null?"":menu.getCode());
		}
		return menuCodeList;
	}

	//根据用户id查询展示所有的菜单
	//TODO 校验正确性
	@Override
	public Map<String, List<Map<String, Object>>> getUserAllMenuId(Integer userId, Integer roleId) {
		Map<String, List<Map<String, Object>>> result =new LinkedHashMap<>();
		List<Map<String, Object>> list = sysUserMapper.getAllSysMenuList(roleId);
		for(Map<String, Object> menu:list){
			String pid = menu.get("pid").toString();
			String pname = menu.get("pname").toString();
			String key =pid+"-"+pname;
			List<Map<String, Object>> mlist = result.get(key);
			if(mlist==null){
				mlist = new LinkedList<>();
			}
			mlist.add(menu);
			result.put(key, mlist);
		}
//		for(Entry<String, List<Map<String, Object>>> a:result.entrySet()){
//			System.out.println(a.getKey());
//			System.out.println(a.getValue().size());
//		}
		return result;
	}
	
	

}
