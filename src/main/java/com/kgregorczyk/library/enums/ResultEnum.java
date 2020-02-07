package com.kgregorczyk.library.enums;

public enum ResultEnum {

	ADD_NCI_SUCCESS(100,"添加新型冠状病毒感染记录成功"),

	ADD_NCI_FAILD(101,"添加新型冠状病毒感染记录失败"),

	PARAM_EMPTY(200,"The parameter is empty."),
	
	SERVER_INTERNAL_ERROR(201,"Server internal error."),
	
	INSUFFICIENT_PERMISSIONS(202,"Insufficient permissions."),
	
	ACCESS_TOKEN_EMPTY(204,"The access token is empty."),
	
	ILLEGAL_PARAMETER(205,"Illegal parameters, please pass the correct parameters."),
	
	QUERY_SECCESS(206,"search successful."),
	
	QUERY_EMPTY(207,"The query is empty."),
	
	UPDATE_SUCCESS(208,"Update successful."),
	
	UPDATE_FAILED(209,"Update failed."),
	
	DELETED_SUCCESSFULLY(210,"Deleted successfully."),
	
	ACCESS_TOKEN_ILLEGAL(211,"Access token illegal."),
	
	COMMODITY_CLASS_INSERT_SUCCESS(212,"Commodity class insert success."),
	
	CANNOT_DELETED_CONTAINS_CHILDREN(213,"Contains children, cannot be deleted."),
	
	INSERT_SUCCESS(214,"Insert successful."),
	
	INSERT_FAILED(215,"Insert failed."),
	
	DELETE_FAILED(216,"Deleted failed."),
	
	COMMODITY_INSERT_SUCCESS(217,"Commodity insert success.");
	
	
	private int code;
	
	private String message;
	
	ResultEnum( int code,String message){
		
		this.code = code;
		
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
