/**
 * 
 */
package com.ljs.account.mock.trans;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljs.account.mock.module.account.AccountServiceImpl;
import com.ljs.account.mock.module.card.DebtCardServiceImpl;
import com.ljs.account.mock.module.enums.CardStatusEnum;
import com.ljs.account.mock.module.response.AccountResp;
import com.ljs.account.mock.module.response.DebtCardInfo;
import com.ljs.account.mock.module.response.TransferResult;

/**
 * @author lijunshi
 *
 */
@Service
public class CardTransferService {


	// RPC, HTTP ,Webservice，mq
	@Autowired
	private DebtCardServiceImpl  cardService;
	@Autowired
	private AccountServiceImpl  acctService;
	
	public TransferResult  transfer(String fromCardNo,String toCardNo,BigDecimal amount) {
		TransferResult  result = new TransferResult();
		// 1. 根据卡号查询出活期结算账号
		// MOCK 点
		DebtCardInfo outCard = cardService.queryCardInfoByCardNo(fromCardNo);
		DebtCardInfo inCard = cardService.queryCardInfoByCardNo(toCardNo);
		// 2. 检查相关卡状态
		if(StringUtils.isBlank(outCard.getCustId())||StringUtils.isBlank(outCard.getMasterAcctNo())) {
			result.setCode("9999");
			result.setInfo("付款卡号不存在");
		}
		if(StringUtils.isBlank(inCard.getCustId())||StringUtils.isBlank(inCard.getMasterAcctNo())) {
			result.setCode("66666");
			result.setInfo("收款卡号不存在");
			return result;
		}
		if(!CardStatusEnum.CARD_STATUS_NORMAL.getStatus().equals(outCard.getCardStatus())) {
			result.setCode("4444");
			result.setInfo("付款卡号状态下不能进行转账");
			return result;
		}
		if(CardStatusEnum.CARD_STATUS_FREEZING.getStatus().equals(inCard.getCardStatus())) {
			result.setCode("8888");
			result.setInfo("收款卡号被司法冻结，禁止转入");
			return result;
		}
		// 3. 调用核心转账服务
		 // mock点
		AccountResp  acctResp = acctService.transfer(outCard.getMasterAcctNo(),outCard.getAcctName(),
							inCard.getMasterAcctNo(),inCard.getAcctName(), amount);
		result.setCode(acctResp.getCode());
		result.setInfo(acctResp.getInfo());
		// 4. 更新状态
		
		// 5. 调用短信服务
		
		return result;
	}

	public void setCardService(DebtCardServiceImpl cardService) {
		this.cardService = cardService;
	}

	public void setAcctService(AccountServiceImpl acctService) {
		this.acctService = acctService;
	} 
	
	
}
