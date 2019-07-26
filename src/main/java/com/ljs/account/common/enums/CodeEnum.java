/**
 * 
 */
package com.ljs.account.common.enums;

/**
 * @author lijunshi
 *
 */
public enum CodeEnum {

	  SYSTEM_SUCCESS("200","成功");
	
	
	
	CodeEnum(String code, String info) {
		this.code = code;
		this.info = info;
	}

	private String code;
	
	private String info;

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
