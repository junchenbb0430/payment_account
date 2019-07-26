/**
 * 
 */
package com.ljs.account.mq.rocketmq;

import com.ljs.account.mq.IProducerService;
import com.ljs.account.mq.payload.MessagePayLoad;
import com.ljs.account.mq.result.CommonSendResult;
import com.ljs.account.result.RespData;

/**
 * @author lijunshi
 *	增加事物消息
 */
public interface IProducerExtensionService extends IProducerService {

	/**
	 * 
	* @return
	 */
	public RespData<? extends CommonSendResult>  sendTransactionMessage(MessagePayLoad  msgPayLoad);
}
