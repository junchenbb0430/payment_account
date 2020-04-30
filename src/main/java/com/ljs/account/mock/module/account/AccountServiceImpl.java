/**
 * 
 */
package com.ljs.account.mock.module.account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ljs.account.mock.module.enums.AccountStatusEnum;
import com.ljs.account.mock.module.response.AccountResp;

/**
 * @author lijunshi
 *
 */
@Service
public class AccountServiceImpl implements AccountService{
	
	private static  Map<String,EgfAccount> acctMap = new  HashMap<String,EgfAccount>();
	static {
		acctMap.put("1111111111111",new EgfAccount("1111111111111","赵四","1",new BigDecimal(500)));
		acctMap.put("2222222222222",new EgfAccount("2222222222222","刘能","2",new BigDecimal(500)));
		acctMap.put("3333333333333",new EgfAccount("3333333333333","王五","2",new BigDecimal(500)));
		acctMap.put("4444444444444",new EgfAccount("4444444444444","张三","1",new BigDecimal(500)));
		acctMap.put("5555555555555",new EgfAccount("5555555555555","谢广坤","3",new BigDecimal(500)));
		acctMap.put("6666666666666",new EgfAccount("6666666666666","李强","4",new BigDecimal(500)));
	}
	
	@Override
	public AccountResp  transfer(String payerAcct,String payerAcctName
            ,String payeeAcctNo,String payeeAcctName,BigDecimal amount) {
		AccountResp  acctResp = new AccountResp();
		EgfAccount payerAccount = acctMap.get(payerAcct);
		EgfAccount payeeAccount = acctMap.get(payeeAcctNo);
		if(payerAccount.getBalance().compareTo(amount)<0) {
			acctResp.setCode("500");
			acctResp.setInfo("金额不足");
			return acctResp;
		}
		if(!StringUtils.equals(payerAccount.getAcctName(), payerAcctName)) {
			acctResp.setCode("100");
			acctResp.setInfo("付款人户名不对");
			return acctResp;
		}
		
		if(!StringUtils.equals(payeeAccount.getAcctName(), payeeAcctName)) {
			acctResp.setCode("200");
			acctResp.setInfo("收款人户名不对!");
			return acctResp;
		}
		
		if(AccountStatusEnum.ACCOUNT_STATUS_NOT_OUT.getStatus().equals(payerAccount.getAcctStatus())
		  ||AccountStatusEnum.ACCOUNT_STATUS_FORBID.getStatus().equals(payerAccount.getAcctStatus())) {
			acctResp.setCode("400");
			acctResp.setInfo("付款账户状态不对");
			return acctResp;
		}
		if(AccountStatusEnum.ACCOUNT_STATUS_NOT_IN.getStatus().equals(payeeAccount.getAcctStatus())
			||AccountStatusEnum.ACCOUNT_STATUS_FORBID.getStatus().equals(payeeAccount.getAcctStatus())) {
			acctResp.setCode("300");
			acctResp.setInfo("收款状态不对");
			return acctResp;
		}
		return acctResp;
	}
}
