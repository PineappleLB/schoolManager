package club.pinea.school.service.impl;

import java.util.ArrayList;
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
	@Override
	public List<Map<String, Object>> getUserAllMenuId(Integer userId, Integer roleId) {
		List<Map<String, Object>> list = sysUserMapper.getAllSysMenuList(roleId);
		return list;
	}

}
