/**
 * 
 */
package com.ljs.account.mq.kafka.consumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljs.account.mq.kafka.Config.ParamConstants;

/**
 * @author lijunshi
 *
 */
@Service
public class KafaConsumerService<K,V>   implements  InitializingBean{

	/**
	 * Broker连接地址
	 */
	@NotBlank
	private String  brokerServerList;
	
	/**
	 * group标识
	 */
	private  String consumerGroupId;
	
	/**
	 * key反序列化方式
	 */
	@NotBlank
	private String keyDeserializer;
	
	/**
	 * value反序列化方式
	 */
	@NotBlank
	private String valueDeserializer;
	
	/**
	 * 消费者提交方式
	 */
	@NotBlank
	private String consumerCommitType;
	
	private long pullIntervalTime;
	
	@Autowired
	private KafkaConsumer<String,V>  kafkaConsumer;
	
	@Autowired
	private String topicName;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		 
		Properties pro  = new Properties();
		pro.put(ParamConstants.KAFKA_SERVER_LIST,this.brokerServerList);
		pro.put(ParamConstants.CONSUMER_GROUP_ID,this.consumerGroupId);
		pro.put(ParamConstants.CONSUMER_KEY_DESERIALIZER,this.keyDeserializer);
		pro.put(ParamConstants.CONSUMER_VALUE_DESERIALIZER,this.valueDeserializer);
		pro.put(ParamConstants.CONSUMER_AUTO_COMMIT_TYPE,this.consumerCommitType);
		kafkaConsumer = new KafkaConsumer<String,V>(pro);
		List<String> topicList = new ArrayList<String>();
		topicList.add(topicName);
		kafkaConsumer.subscribe(topicList);
	}

	public void consumer() {
		while(true) {
			ConsumerRecords<String, V> records = kafkaConsumer.poll(Duration.ofMillis(this.pullIntervalTime));	
		}
		
	}
	
	public void consumerOnRebalancec() {
		
	}
}
