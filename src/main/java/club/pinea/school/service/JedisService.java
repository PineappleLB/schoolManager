package club.pinea.school.service;

import java.util.List;

import club.pinea.school.model.SysUser;

public interface JedisService {
	
	/**
	 * 校验图片验证码是否正确
	 * @param sessionId
	 * @param imgCode
	 * @return
	 */
	boolean validateImgCode(String sessionId, String imgCode);
	
	/**
	 * 设置图片验证码值
	 * @param sessionId
	 * @param imgCode
	 */
	void setImgCode(String sessionId, String imgCode);

	/**
	 * 在redis中检查是否有该用户
	 * @param sessionId
	 * @return
	 */
	SysUser authUser(String sessionId);

	/**
	 * 将redis里面存储的user数据删除
	 * @param sessionId
	 * @return
	 */
	int logout(String sessionId);

	/**
	 * 从redis中查询用户信息
	 * @param sessionId
	 * @return
	 */
	SysUser getUser(String sessionId);

	/**
	 * 登录成功保存用户信息
	 * @param sessionId
	 * @param user
	 * @return
	 */
	int saveUser(String sessionId, SysUser user);

	/**
	 * 更新用户redis过期时间
	 * @param msg
	 */
	void updateUserExpiryTime(String key);

	/**
	 * 保存服务器地址
	 * @param localAddress
	 * @return
	 */
	int saveServerAddress(String localAddress);

	/**
	 * 删除服务器地址
	 * @param localAddress
	 * @return
	 */
	int removeLocalAddress(String localAddress);

	/**
	 * 获取所有服务器地址
	 * @return
	 */
	List<String> getAllServerAddresses();

}
