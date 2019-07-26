package com.ljs.account.mq.rocketmq.producer;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

import org.apache.rocketmq.client.producer.SendResult;

import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ljs.account.mq.result.CommonSendResult;

import lombok.Data;

/**
 * @author lijunshi
 *  统一的生产者发送服务父类，子类只需要实例化具体的生产者即可，
 *  无需每次都要从头开发Producer代码。
 */
@Component
@Data
public abstract class AbstractProducerSendMessageService extends ProducerConfig implements ProducerSendMessageService,InitializingBean {

	private final static Logger logger = LoggerFactory.getLogger(AbstractProducerSendMessageService.class);
	
	private final static String CHARSET = "UTF-8";
	

	private TransactionMQProducer  transactionProducer;
	
	private DefaultMQProducer  producer;
	 
	 
	
	@Resource
	private TransactionListener  transactionListener;
	
	/**
	 * 具体子类只是需要实例化生产者实例即可
	* void
	 */
	public abstract void initProducer();
	
	@Override
	public CommonSendResult sendMessageInTransaction(Object object, String msgKey, String topicName) {
		 
		return this.sendMessageInTransaction(object,msgKey, topicName, null);
	}

	@Override
	public CommonSendResult sendMessageInTransaction(Object object, String msgKey, String topicName, String msgTag) {
		String msgBody = JSONObject.toJSONString(object);	
		CommonSendResult sendResult = new CommonSendResult();
		
		try {
			Message msg = new Message(topicName,msgTag,msgKey,msgBody.getBytes(CHARSET));
			logger.info("往topic={}发送消息msgkey={},消息内容={}", topicName,msgKey,msgBody);
			//sendResult.setMsgKey(msgKey); 
			TransactionSendResult transSendResult = transactionProducer.sendMessageInTransaction(msg, null);
			logger.info("该条消息mesKey={},msgId={},发送状态={}",msgKey,transSendResult.getMsgId(),transSendResult.getSendStatus());
			 
		} catch (Exception ex) {
			//sendResult.setMsgKey(msgKey); 
			//sendResult.setSendResult(MqSendStatusEnum.SEND_STATUS_FAILURE.getSendCode());
			logger.error("该笔消息msg={},content={}发送失败",msgKey,msgBody,ex);
		}
		return sendResult;
	}

	@Override
	public CommonSendResult sendCommonMessage(Object object, String msgKey, String topicName) {
		 
		return this.sendCommonMessage(object, msgKey,topicName, null);
	}

	@Override
	public CommonSendResult sendCommonMessage(Object object,  String msgKey,String topicName, String msgTag) {
		String msgBody = JSONObject.toJSONString(object);	
		CommonSendResult liftSendResult = new CommonSendResult();
		 
		try {
			Message msg = new Message(topicName,msgTag,msgKey,msgBody.getBytes(CHARSET));
			logger.info("往topic={}发送消息msgkey={},消息内容={}",topicName,msgKey,msgBody);
			 
			SendResult sendResult = producer.send(msg);
			logger.info("发送消息key={},msgId={},sendResult={}",
					msgKey, sendResult.getMsgId(),sendResult.getSendStatus());
			 
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liftSendResult;
		 
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.checkParam(); 
		this.initProducer();
	}
}
