/**
 * 
 */
package com.ljs.account.mock.module.response;

/**
 * @author lijunshi
 *
 */
public class DebtCardInfo {

	private String custId;
	
	private String cardNo;
	
	private String masterAcctNo;
	
	private String cardStatus;
	
	private String acctName;

	
	
	public DebtCardInfo() {
		super();
	}

	
	
	public DebtCardInfo(String custId, String cardNo, String masterAcctNo, String cardStatus, String acctName) {
		super();
		this.custId = custId;
		this.cardNo = cardNo;
		this.masterAcctNo = masterAcctNo;
		this.cardStatus = cardStatus;
		this.acctName = acctName;
	}

	

	public DebtCardInfo(String custId, String masterAcctNo, String acctName) {
		super();
		this.custId = custId;
		this.masterAcctNo = masterAcctNo;
		this.acctName = acctName;
	}

	public DebtCardInfo(String custId, String masterAcctNo) {
		super();
		this.custId = custId;
		this.masterAcctNo = masterAcctNo;
	}
	
	
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	 

	public String getMasterAcctNo() {
		return masterAcctNo;
	}

	public void setMasterAcctNo(String masterAcctNo) {
		this.masterAcctNo = masterAcctNo;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}


	
	
}
