package club.pinea.school.mapper;

import java.util.List;
import java.util.Map;

import club.pinea.school.model.SysMenu;
import club.pinea.school.model.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

	SysUser selectByAccount(String account);

	List<SysMenu> getSysMenuByRoleId(Integer roleId);
	
	List<Map<String, Object>> getAllSysMenuList(Integer roleId);
}