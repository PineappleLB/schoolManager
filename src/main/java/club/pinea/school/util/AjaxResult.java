package club.pinea.school.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import club.pinea.school.config.CodeEnum;

public class AjaxResult {
	// 标记成功失败，默认200:成功,500:服务器错误
	private int code = CodeEnum.SUCCESS.getCode();

	// 返回的中文消息
	private String message = "ok";

	// 成功时携带的数据
	private Object data;

	public int getCode() {
		return code;
	}

	public AjaxResult setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public AjaxResult setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getData() {
		return data;
	}

	public AjaxResult setData(Object data) {
		this.data = data;
		return this;
	}

	// 添加成功，用于alertSuccess
	public AjaxResult addSuccess(String message) {
		this.message = message;
		this.code = CodeEnum.SUCCESS.getCode();
		this.data = null;
		return this;
	}
	//没有权限
	public AjaxResult noPermissionError(String message) {
		this.message = message;
		this.code = CodeEnum.NO_PERMISSION.getCode();
		this.data = null;
		return this;
	}
	//返回用户未登录错误
	public AjaxResult noUserError(String message) {
		this.message = message;
		this.code = CodeEnum.NO_USER.getCode();
		this.data = null;
		return this;
	}
	
	//返回服务器错误消息
	public AjaxResult addError(Exception e) {
		this.message = e.getMessage();
		this.code = CodeEnum.SERVER_ERROR.getCode();
		this.data = null;
		return this;
	}

	// 添加错误，用于alertError
	public AjaxResult addError(String message) {
		this.message = message;
		this.code = CodeEnum.SERVER_ERROR.getCode();
		this.data = null;
		return this;
	}

	// 添加错误，用于alertFail
	public AjaxResult addFail(String message) {
		this.message = message;
		this.code = 999;
		this.data = null;
		return this;
	}

	// 添加警告，用于alertWarn
	public AjaxResult addWarn(String message) {
		this.message = message;
		this.code = 2;
		this.data = null;
		return this;
	}

	/**
	 * 
	 * 封装成功时的数据
	 * 
	 * @param data
	 * 
	 * @return AjaxResult
	 */
	public AjaxResult success(Object data) {
		this.message = "success";
		this.data = data;
		this.code = 0;
		return this;
	}

	@JSONField(serialize = false, deserialize = false)
	public boolean isSuccess() {
		return getCode() == 0;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
