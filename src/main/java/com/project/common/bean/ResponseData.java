package com.project.common.bean;

import com.project.common.constants.CommonConstant.RESPONSE_STATUS;

public class ResponseData<T> {
	private RESPONSE_STATUS status = RESPONSE_STATUS.FAILED;
	private String message;
	private T data;

	public RESPONSE_STATUS getStatus() {
		return status;
	}

	public void setStatus(RESPONSE_STATUS status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}