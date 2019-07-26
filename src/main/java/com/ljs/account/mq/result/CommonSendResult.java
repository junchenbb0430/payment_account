/**
 * 
 */
package com.ljs.account.mq.result;

import java.util.Date;

import lombok.Data;

/**
 * @author lijunshi
 *
 */
@Data
public class CommonSendResult {

	/**
	 * 消息偏移量
	 */
	private long  offset;
	
	/**
	 * topic名称
	 */
	private String topicName;
	
	/**
	 * 消息时间戳
	 */
	private Date timestamp;
	
	/**
	 * 消息发送key
	 */
	private String  msgKeyId;
	
	/**
	 * kafka：partition
	 * rocketmq:queue
	 */
	private int  partitonOrQueueId;
	
	/**
	 * 发送状态
	 */
	private String  sendStatus;
}
