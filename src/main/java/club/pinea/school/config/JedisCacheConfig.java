package club.pinea.school.config;


/**
 * redis存储的前缀枚举类
 * @author Administrator
 *
 */
public enum JedisCacheConfig {
	
	/**
	 * 图片验证码
	 */
	IMG_CODE(1, "PICTURE_CODE_"),
	
	/**
	 * 用户缓存前缀
	 */
	USER_PREFIX(2, "USER_PREFIX_"),
	
	/**
	 * 服务器列表前缀
	 */
	SERVER_ADDRESSES(3, "SERVER_ADDRESSES")
	
	;
	
	
	
	// 成员变量
	private int code;
	private String msg;
	
	private JedisCacheConfig(int code,String msg){
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public String getMsg(Object obj){
		return msg + obj;
	}
	
	public String format(Object ...obj){
		return String.format(getMsg(), obj);
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
