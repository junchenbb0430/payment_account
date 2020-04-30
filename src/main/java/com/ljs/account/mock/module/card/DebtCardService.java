package com.ljs.account.mock.module.card;

import com.ljs.account.mock.module.response.DebtCardInfo;

public interface DebtCardService {
	public DebtCardInfo  queryCardInfoByCardNo(String cardNo);
}
