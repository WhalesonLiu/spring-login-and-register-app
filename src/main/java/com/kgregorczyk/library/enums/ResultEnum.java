package com.kgregorczyk.library.enums;

public enum ResultEnum {

	ADD_NCI_SUCCESS("100","添加新型冠状病毒感染记录成功"),

	ADD_NCI_FAILD("101","添加新型冠状病毒感染记录失败"),

	QUERY_SECCESS("206","search successful."),
	
	QUERY_EMPTY("207","The query is empty."),

	PARAM_EMPEY("209","查询参数为空");
	

	
	private String code;
	
	private String message;
	
	ResultEnum( String code,String message){
		
		this.code = code;
		
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
