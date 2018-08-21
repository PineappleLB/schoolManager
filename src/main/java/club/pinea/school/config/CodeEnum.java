package club.pinea.school.config;

/**
 * 定义服务器返回的错误码
 * @author Administrator
 *
 */
public enum CodeEnum {

	
	/**
	 * 成功
	 */
	SUCCESS(200),
	/**
	 * 服务器错误
	 */
	SERVER_ERROR(500),
	/**
	 * 权限不足
	 */
	NO_PERMISSION(300),
	/**
	 * 未登录
	 */
	NO_USER(400);
	
	private int code;
	
	CodeEnum(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	
}
