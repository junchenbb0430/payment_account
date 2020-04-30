package com.ljs.account.mock.module.response;

public class AccountResp {

	private String code;
	
	private String info;

	public AccountResp( ) {
		 this.init();
	}

	
	
	public AccountResp(String code, String info) {
		super();
		this.code = code;
		this.info = info;
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
