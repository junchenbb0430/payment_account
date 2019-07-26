package com.ljs.account.mq.rocketmq.producer;

import com.ljs.account.mq.result.CommonSendResult;

/**
 * @author lijunshi
 *
 */
public interface ProducerSendMessageService {
	
	  /** 发送事物消息至默认的topic中
	   * @param object
	   * @param msgKey
	   * @param topicName
	  * LiftSendResult 返回发送结果信息
	   */
	CommonSendResult sendMessageInTransaction(Object object,String msgKey,String topicName);

	  /** 发送事物消息至默认的topic中
	   * @param object    消息体
	   * @param msgKey    消息Key
	   * @param msgTag    消息tag符
	   * @param topicName topic名称
	  * LiftSendResult 返回发送结果信息
	   */
	CommonSendResult sendMessageInTransaction(Object object,String msgKey,String topicName,String msgTag);
	  
	 
	  
	  /** 发送事普通息至默认的topic中
	   * @param object    消息体
	   * @param msgKey    消息Key
	   * @param msgTag    消息tag符
	   * @param topicName topic名称
	  * LiftSendResult 返回发送结果信息
	   */
	CommonSendResult sendCommonMessage(Object object,String msgKey,String topicName);
	  
	  /** 发送事物消息至topic中
	   * @param object    消息体
	   * @param msgKey    消息Key
	   * @param msgTag    消息tag符
	   * @param topicName topic名称
	  * LiftSendResult 返回发送结果信息
	   */
	CommonSendResult sendCommonMessage(Object object,String msgKey,String topicName,String msgTag);
	  
	 
}
