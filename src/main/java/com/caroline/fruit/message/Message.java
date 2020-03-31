package com.caroline.fruit.message;

import com.caroline.fruit.enums.ResultEnum;
import org.springframework.validation.BindingResult;

/**
 * 错误信息处理
 * 
 * @author jiekechoo
 *
 */
public class Message {

	private String code;
	private String message;
	private Object content;

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getContent() {
		return content;
	}

	public Message() {

	}
	

	public Message(ResultEnum resultEnum) {
		this.code = resultEnum.getCode();
		this.message = resultEnum.getMessage();
		this.content = "no content";
		
	}
	public Message(ResultEnum resultEnum, Object content) {
		this.code = resultEnum.getCode();
		this.message = resultEnum.getMessage();
		this.content = content;
		
	}
	public Message(String message) {
		this.code = "500";
		this.message = message;
		this.content = "";
	}
	
	public Message(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public Message(String code, String message, Object content) {
		this.code = code;
		this.message = message;
		this.content = content;
	}

	public void setMsg(ResultEnum resultEnum) {
		this.code = resultEnum.getCode();
		this.message = resultEnum.getMessage();
		this.content = "no content";
		
	}
	public void setMsg(BindingResult bindingResult) {
		this.code =  bindingResult.getGlobalError().getCode();
		this.message = bindingResult.getGlobalError().getDefaultMessage();
		this.content = "no content";
	}
	public void setMsg(ResultEnum resultEnum, Object content) {
		this.code = resultEnum.getCode();
		this.message = resultEnum.getMessage();
		this.content = content;
		
	}
	public void setMsg(String code, String message) {
		this.code = code;
		this.message = message;
		this.content = "no content";
	}

	public void setMsg(String code, String message, Object content) {
		this.code = code;
		this.message = message;
		this.content = content;
	}
}
