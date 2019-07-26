package com.ljs.account.mq.rocketmq.consumer;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

 
/**
 * @author lijunshi
 *
 */
@Component
public  abstract class  AbstractDefaultPushConsumer implements MessageListenerConcurrently,InitializingBean {

private static Logger logger = LoggerFactory.getLogger(AbstractDefaultPushConsumer.class);
	
private static String CHARSET = "UTF-8"; 
	
	@Value("${rocketMQNameServerAddr}")
	private String nameServAddr;
	
	@Value("${rocketMQConsumerTopicName}")
	private String topicName;
	
	@Value("${rocketMQConsumeGroupName}")
	private String groupName;
	
	@Value("${rocketMQConsumeInstance}")
	private String consumeInstance;
	
	@Value("${rocketMQConsumeTags}")
	private String messageTags;
	
	private DefaultMQPushConsumer  consumer;
	
	 
	
	/**
	 * 所有子类定义具体的业务处理,同一个topic下面根据tag进行区分处理
	* @param messageContent
	* @return
	 */
	public abstract ConsumeConcurrentlyStatus consumeMessage(String messageContent);
	
	 
	
	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		 
		// 1. 设置每次消费消息只有一条
		 
				if(msgs.size()>1) {
					 return  ConsumeConcurrentlyStatus.RECONSUME_LATER;
				 }
				// 2. 增加业务重复消费处理
				MessageExt msgExt = msgs.get(0);
				 
				String messageContent = null;
				try {
					messageContent = new String(msgExt.getBody(),CHARSET);
					logger.info("该条消息key={},msgId={}对应内容={}",msgExt.getKeys(),msgExt.getMsgId(),messageContent);
					
					return consumeMessage(messageContent);
				} catch (UnsupportedEncodingException e) {
					logger.error("该条消息key={},msgId={}消费消息转成字符串失败",msgExt.getKeys(),msgExt.getMsgId());
					return ConsumeConcurrentlyStatus.RECONSUME_LATER; 
				}
//			 
//				ConsumeConcurrentlyStatus consumeStatus = consumeMessage(messageContent,msgExt.getTags());
//				if(ConsumeConcurrentlyStatus.RECONSUME_LATER.equals(consumeStatus)) {
//					context.setDelayLevelWhenNextConsume(5);
//				}
//				return consumeStatus;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		/*设置消费者组*/
		consumer = new DefaultMQPushConsumer(this.groupName);
		/*设置消费最小线程数，默认20*/
		consumer.setConsumeThreadMin(8);
		/*设置消费者最大线程数，默认64*/
		consumer.setConsumeThreadMax(10);
		/*设置消费实例名*/
		consumer.setInstanceName(consumeInstance);
		/* 每次最多消费1条消息,默认一次消费32条*/
		consumer.setConsumeMessageBatchMaxSize(1);
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		 
		/*注册消息监听者*/
		consumer.registerMessageListener(this);
		/*设置nameserver地址*/
		consumer.setNamesrvAddr(this.nameServAddr);
		consumer.subscribe(this.topicName,this.messageTags);
		 
		logger.info("nameServAddr={}消费者实例开始启动",this.nameServAddr);
		try {
			consumer.start();
			logger.info("对账平台消费者实例启动成功");
		}catch(Exception ex) {
			logger.error("对账平台消费者实例启动失败!",ex);
		}
		 
	}





}