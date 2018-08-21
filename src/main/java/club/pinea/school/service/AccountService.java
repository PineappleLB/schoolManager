package club.pinea.school.service;

import java.util.List;
import java.util.Map;

import club.pinea.school.model.SysUser;

public interface AccountService {
	
	/**
	 * 根据id查询系统用户信息
	 * @param id
	 * @return
	 */
	SysUser querySysuserInfoByPrimaryKey(int id);
	
	/**
	 * 根据账号查询系统用户信息
	 * @param account
	 * @return
	 */
	SysUser querySysuserInfoByAccount(String account);
	
	/**
	 * 根据id和角色id查询所有的权限code
	 * @param userId
	 * @param roleId
	 * @return
	 */
	List<String> getMenuCodeList(Integer userId, Integer roleId);
	
	/**
	 * 根据用户id查询展示所有的菜单
	 * @param userid
	 * @return
	 */
	Map<String, List<Map<String, Object>>> getUserAllMenuId(Integer userId, Integer roleId);

}
