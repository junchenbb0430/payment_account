/**
 * 
 */
package com.ljs.account.mq.rocketmq;

/**
 * @author lijunshi
 *
 */
public class MqCommonConfig {

	/**
	 * 开发环境NAMESERVER地址
	 */
	public static final String MQ_NAME_SERVER_DEV ="10.12.4.87:9876;10.12.4.88:9876";
	/**
	 * test2环境NAMESERVER地址
	 */
	public static final String MQ_NAME_SERVER_TEST2=":9876;:9876";
	/**
	 * test1环境NAMESERVER地址
	 */
	public static final String MQ_NAME_SERVER_TEST1="10.12.9.137:9876;10.12.9.138:9876";
	
	/**
	 * 交易平台发送对账平台group
	 */
	public static final String MQ_GROUP_TO_SBPILL="CHECK_TRANS_SPBILL_GROUP";
	
	/**
	 * 对账平台发送至交易系统的GROUP
	 * */
	public static final String  MQ_GROUP_TO_TRANS="CHECK_TRANS_ERROR_GROUP";
	
	/**
	 * 对账平台消费TOPIC
	 */
	public static final String CONSUMER_TRANS_DATA_TOPIC="TRANS_PUSH_SPBILL_TOPIC";

	/**
	 * 消费者TOPIC中的tag
	 */
	public static final String CONSUMER_TRANS_DATA_TOPIC_TAGS="deal";
	
	public static final String CONSUMER_TRANS_DATA_TOPIC_NORMAL="D100000001";
	
	/**
	 * 消息消费者的实例名
	 */
	public static final String MQ_CONSUME_INSTANCE="transPush";
	
	/**
	 * 消息发送者实例名
	 */
	public static final String MQ_PRODUCER_INSTANCE="checkProducer";
	
	/**
	 * 对账平台发送消息至交易系统的TOPIC
	 */
	public static final String CHECK_ERROR_DATA_PUSH_TOPIC="CHECK_ERROR_TOPIC";
	/**
	 * 对账平台发送消息至交易系统的TOPIC的TAG标识
	 */
	public static final String CHECK_ERROR_DATA_PUSH_TOPIC_TAG="spbill";
	
	public static final String EPCC_ERROR_TOPIC="CHECK_ERROR_WITHDRAW_TOPIC2";

	public static final String MQ_GROUP_TO_WITHDRAW="CHECK_EPCC_ERROR_GROUP";
}
