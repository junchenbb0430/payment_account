/**
 * 
 */
package com.ljs.account.mq;

import com.ljs.account.mq.payload.MessagePayLoad;
import com.ljs.account.mq.result.CommonSendResult;
import com.ljs.account.result.RespData;

/**
 * @author lijunshi
 *  定义消息发送行为：
 *  	1. 发送普通消息
 *  	2. 发送普通消息带回调通知功能
 *  	3. 发送顺序消息功能
 */
public interface IProducerService<V> {

	/**
	 * 
	* @return
	 */
	public RespData<? extends CommonSendResult>  sendMessage(MessagePayLoad<V>  msgPayLoad);
	
	/**
	 * 
	* @return
	 */
	public RespData<? extends CommonSendResult>	 sendMessageOnCallback(MessagePayLoad<V>  msgPayLoad);
	
	/**
	 * 
	* @return
	 */
	public  RespData<? extends CommonSendResult>  sendOrderdMessage(MessagePayLoad<V>  msgPayLoad);
}
