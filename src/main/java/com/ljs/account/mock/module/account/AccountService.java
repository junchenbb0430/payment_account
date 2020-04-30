package com.ljs.account.mock.module.account;

import java.math.BigDecimal;

import com.ljs.account.mock.module.response.AccountResp;

public interface AccountService {
	public AccountResp  transfer(String payerAcct,String payerAcctName
			            ,String payeeAcctNo,String payeeAcctName,BigDecimal amount);
}
