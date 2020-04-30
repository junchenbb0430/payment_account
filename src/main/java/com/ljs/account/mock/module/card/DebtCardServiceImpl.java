/**
 * 
 */
package com.ljs.account.mock.module.card;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ljs.account.mock.module.enums.CardStatusEnum;
import com.ljs.account.mock.module.response.DebtCardInfo;

/**
 * @author lijunshi
 *
 */
@Service
public class DebtCardServiceImpl implements DebtCardService{

	private static Map<String,DebtCardInfo> cardMap = new HashMap<String,DebtCardInfo>();
	static {
		cardMap.put("6226220104832852", new DebtCardInfo("00002","6226220104832852","1111111111111","1","赵四"));
		cardMap.put("6226220104832853", new DebtCardInfo("00003","6226220104832853","2222222222222","1","刘能"));
		cardMap.put("6226220104832854", new DebtCardInfo("00004","6226220104832854","3333333333333","2","王五"));
		cardMap.put("6226220104832855", new DebtCardInfo("00005","6226220104832855","4444444444444","1","谢广坤"));
		cardMap.put("6226220104832856", new DebtCardInfo("00006","6226220104832856","5555555555555","3","刘老根"));
		cardMap.put("6226220104832857", new DebtCardInfo("00007","6226220104832857","6666666666666","1","李强"));
	}
	@Override
	public DebtCardInfo  queryCardInfoByCardNo(String cardNo) {
		DebtCardInfo  cardInfo = new DebtCardInfo();
		cardInfo.setCardNo(cardNo);
		if(!cardNo.startsWith("6226")) {
			 return cardInfo;
		} 
	  return cardMap.get(cardNo);
	}
}
