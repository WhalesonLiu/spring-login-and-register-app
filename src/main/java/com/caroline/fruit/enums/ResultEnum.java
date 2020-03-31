package com.caroline.fruit.enums;

public enum ResultEnum {

	ADD_NCI_SUCCESS("100","添加新型冠状病毒感染记录成功"),

	ADD_NCI_FAILD("101","添加新型冠状病毒感染记录失败"),

	QUERY_SECCESS("200","查询成功"),
	
	QUERY_EMPTY("207","没有查询到对应的数据"),

	PARAM_EMPEY("250","参数为空"),

	INNER_ERROR("250","系统错误,请稍后再试"),

	ADD_SUCCESS("200","添加成功"),

	ADD_FAILD("300","添加失败");
	

	
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
