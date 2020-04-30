/**
 * 
 */
package com.ljs.account.mock.module.enums;

/**
 * @author lijunshi
 *
 */
public enum AccountStatusEnum {

	ACCOUNT_STATUS_NORMAL("1","正常"),
	ACCOUNT_STATUS_NOT_OUT("2","禁止转出"),
	ACCOUNT_STATUS_NOT_IN("3","禁止转入"),
	ACCOUNT_STATUS_FORBID("4","司法冻结");
	
	
	
 AccountStatusEnum(String status, String info) {
		this.status = status;
		this.info = info;
	}

	private String status;
	
	private String info;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
