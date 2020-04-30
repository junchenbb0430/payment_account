package com.ljs.account.mock.module.response;

public class TransferResult {


	private String code;
	
	private String info;

	public TransferResult( ) {
		 this.init();
	}

	private void init() {
		this.code = "200";
		this.info = "SUCCESS";
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
