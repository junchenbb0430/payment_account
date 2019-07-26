/**
 * 
 */
package com.ljs.account.mq.kafka.producer;

import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.ljs.account.mq.kafka.KafkaSendResult;
import com.ljs.account.mq.result.CommonSendResult;
import com.ljs.account.result.RespData;

/**
 * @author lijunshi
 *
 */
//@Service
public class KafkaProcuderService<V> implements InitializingBean {

	private final static Logger logger  = LoggerFactory.getLogger(KafkaProcuderService.class);
	
	/**
	 * Broker连接地址
	 */
	@NotBlank
	private String  brokerServerList;
	
	 
	
	/**
	 * key序列化方式
	 */
	@NotBlank
	private String keySerializer;
	
	/**
	 * value发送序列化方式
	 */
	@NotBlank
	private String valueSerializer;
	
	/**
	 * 消息发送确认机制
	 */
	@NotBlank
	private String msgAckType;
	
	/**
	 * Kafka中的key必须是String类型
	 */
	private  KafkaProducer<String,V> kafkaProducer;
	
	
	/**
	 * 
	* @param topicName
	* @param value
	* @return
	 */
	public RespData<? extends CommonSendResult>  sendMessage(String topicName,V value) {
		 
		return  this.sendMessage(topicName,null,null, value);
	}
	
	/**
	 * 
	* @param topicName
	* @param key
	* @param value
	* @return
	 */
	public RespData<? extends CommonSendResult>  sendMessage(String topicName,String key,V value) {
		 
		return  this.sendMessage(topicName,null,key, value);
	}
	
	/**
	 * 
	* @param topicName
	* @param partition
	* @param key
	* @param value
	* @return
	 */
	public RespData<? extends CommonSendResult>  sendMessage(String topicName,Integer partition ,String key,V value){
		RespData<KafkaSendResult> respData = new RespData<KafkaSendResult>();
		ProducerRecord<String, V> record = new ProducerRecord<String,V>(topicName,partition,key,value);
		Future<RecordMetadata> future = kafkaProducer.send(record);
		try {
			RecordMetadata metadata = future.get();
			KafkaSendResult  sendResult = new KafkaSendResult();
			sendResult.setOffset(metadata.offset());
			sendResult.setPartitonOrQueueId(metadata.partition());
			sendResult.setTopicName(metadata.topic());//topic名称
			respData.setResult(sendResult);
			//sendResult.setTimestamp(DateUtil); 
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respData;
	}
	
	
	
	public RespData<? extends CommonSendResult>	 sendMessageOnCallback(String topicName,Integer partition,String key,V value) {
		RespData<KafkaSendResult> respData = new RespData<KafkaSendResult>();
		ProducerRecord<String, V> record = new ProducerRecord<String,V>(topicName,partition,key,value);
		Future<RecordMetadata> future = kafkaProducer.send(record, new  Callback() {

			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				 
			}
			
		});
		try {
			RecordMetadata metadata = future.get();
			KafkaSendResult  sendResult = new KafkaSendResult();
			sendResult.setOffset(metadata.offset());
			sendResult.setPartitonOrQueueId(metadata.partition());
			sendResult.setTopicName(metadata.topic());//topic名称
			respData.setResult(sendResult);
			//sendResult.setTimestamp(DateUtil); 
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respData;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Properties pro = new Properties();
		pro.put("bootstrap.servers",this.brokerServerList);
		pro.put("key.serializer", this.keySerializer);
		pro.put("value.serializer",this.valueSerializer);
	    pro.put("acks",this.msgAckType);
		//kafkaProducer = new KafkaProducer<String,V>(pro);
	}
	
	 
}
