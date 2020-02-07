package com.kgregorczyk.library.message;

import com.kgregorczyk.library.enums.ResultEnum;
import org.springframework.validation.BindingResult;

/**
 * 错误信息处理
 * 
 * @author jiekechoo
 *
 */
public class Message {

	private int code;
	private String message;
	private Object content;

	public int getCode() {
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
		this.code = 500;
		this.message = message;
		this.content = "";
	}
	
	public Message(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public Message(int code, String message, Object content) {
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
		this.code =  Integer.parseInt(bindingResult.getGlobalError().getCode());
		this.message = bindingResult.getGlobalError().getDefaultMessage();
		this.content = "no content";
	}
	public void setMsg(ResultEnum resultEnum, Object content) {
		this.code = resultEnum.getCode();
		this.message = resultEnum.getMessage();
		this.content = content;
		
	}
	public void setMsg(int code, String message) {
		this.code = code;
		this.message = message;
		this.content = "no content";
	}

	public void setMsg(int code, String message, Object content) {
		this.code = code;
		this.message = message;
		this.content = content;
	}
}
