/**
 * 
 */
package com.ljs.account.mock.module.enums;

/**
 * @author lijunshi
 *
 */
public enum CardStatusEnum {


	CARD_STATUS_NORMAL("1","正常"),
	CARD_STATUS_LOSS("2","挂失"),
	CARD_STATUS_FREEZING("3","司法冻结");
	
	
	
	CardStatusEnum(String status, String info) {
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
