package com.demo.poi.config;

public class HttpResult {
	/**
	 * 状态码
	 */
	private int code;
	
	/**
	 * 返回内容
	 */
	private String body;

	public HttpResult(){
		
	}
	
	public HttpResult(int statusCode, String string) {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
