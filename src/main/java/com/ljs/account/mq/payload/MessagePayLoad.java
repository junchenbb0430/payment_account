package com.ljs.account.mq.payload;

import lombok.Data;

/**
 * 
 * @author lijunshi
 *	消息对外封装的统一传输载体
 */
@Data
public class MessagePayLoad<V> {
	
	/**
	 * 消息key
	 */
	private String msgKeyId;
	
	 
	/**
	 * 消息体
	 */
	private V msgObj;
	
	/**
	 * 发送消息格式：JSON或POJO
	 */
	private String msgFormate;
	
	/**
	 * 序列化方式
	 */
	private String serializeType;
}
