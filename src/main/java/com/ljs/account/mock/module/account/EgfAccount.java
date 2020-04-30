/**
 * 
 */
package com.ljs.account.mock.module.account;

import java.math.BigDecimal;

/**
 * @author lijunshi
 *
 */
public class EgfAccount {

	private String acctNo;
	
	private String acctName;
	
	private String acctStatus;
	
	
	private BigDecimal balance;

	

	public EgfAccount() {
		super();
	}

	

	public EgfAccount(String acctName, String acctStatus, BigDecimal balance) {
		super();
		this.acctName = acctName;
		this.acctStatus = acctStatus;
		this.balance = balance;
	}



	public EgfAccount(String acctNo, String acctName, String acctStatus, BigDecimal balance) {
		super();
		this.acctNo = acctNo;
		this.acctName = acctName;
		this.acctStatus = acctStatus;
		this.balance = balance;
	}


	public String getAcctNo() {
		return acctNo;
	}


	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}


	public String getAcctName() {
		return acctName;
	}


	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}


	public String getAcctStatus() {
		return acctStatus;
	}


	public void setAcctStatus(String acctStatus) {
		this.acctStatus = acctStatus;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
}
