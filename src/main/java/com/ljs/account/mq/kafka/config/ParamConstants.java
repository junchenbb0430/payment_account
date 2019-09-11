/**
 * 
 */
package com.ljs.account.mq.kafka.config;

/**
 * @author lijunshi
 *  kafka参数配置常量
 */
public class ParamConstants {

	/**
	 * KAFKA的brokerIP地址  ip:hort;ip:hort;ip:port
	 */
	public final static  String KAFKA_SERVER_LIST="bootstrap.servers";
	
	/**
	 * 消费者组标识
	 */
	public  final static String CONSUMER_GROUP_ID = "group.id"; 
	
	/**
	 * 消费者提交offset方式
	 */
	public  final static String CONSUMER_AUTO_COMMIT_TYPE="enable.auto.commit";
	
	/**
	 * key值反序列化方式
	 */
	public  final static String CONSUMER_KEY_DESERIALIZER = "key.deserializer";
	
	/**
	 * value值反序列化方式
	 */
	public  final static  String CONSUMER_VALUE_DESERIALIZER = "value.deserializer";
	
	/**
	 * 每次最大消费记录数
	 */
	public final static  String  CONSUMER_MAX_POOL_RECORDS = "max.poll.records";
	
	/**
	 * 相邻两次拉取时间间隔
	 */
	public final static  String  CONSUMER_MAX_POOL_INTERVAL = "max.poll.interval.ms";

	/**
	 * 消息发送机制ACK：all,-1,1,0
	 */
	public final static  String  PRODUCER_MESSAGE_ACK = "acks";
	
	/**
	 * 消息发送key 序列化方式
	 */
	public final static  String  PRODUCER_KEY_SERIALIZER ="key.serializer";
	
	/**
	 * 消息发送value序列化方式
	 */
	public final static	 String  PRODUCER_VALUE_SERIALIZER = "value.serializer";
	
	/**
	 * 消息发送者buffer大小
	 */
	public final static  String  PRODUCER_BATCH_SIZE = "batch.size";
	
	/**
	 * 消息发送buffer时间间隔
	 */
	public final static  String  PRODUCER_LINER_MS = "liner.ms";
	
	 public static final String SESSION_TIMEOUT_MS_CONFIG = "session.timeout.ms";
}
