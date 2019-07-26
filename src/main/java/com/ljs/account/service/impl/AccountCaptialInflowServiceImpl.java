package com.ljs.account.service.impl;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.stereotype.Service;

import com.ljs.account.entity.AccountEntity;
import com.ljs.account.result.RespData;
import com.ljs.account.service.AccountCaptialInflowService;

import lombok.Data;
@Service
@Data
public class AccountCaptialInflowServiceImpl implements AccountCaptialInflowService,Callable<AccountEntity> {

 
	private String acctNo;
	
	@Override
	public AccountEntity hotspotAccountInflow(String acctNo) {
		AccountEntity acct = new AccountEntity();
		 System.out.println(acctNo + "execute begin!......");
		 try {
			Thread.sleep(5*1000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(acctNo + " execute success!......");
		 

		return acct;
	}

	@Override
	public AccountEntity call() throws Exception {
		 	
		AccountEntity acctEntity =  this.hotspotAccountInflow(acctNo);
		return acctEntity;
	}

	 

}
