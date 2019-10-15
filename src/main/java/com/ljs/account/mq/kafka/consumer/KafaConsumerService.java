/**
 * 
 */
package com.ljs.account.mq.kafka.consumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ljs.account.mq.kafka.config.ParamConstants;

/**
 * @author lijunshi
 *
 */
@Service
public class KafaConsumerService<K,V>   implements  InitializingBean{

	private static final Logger logger = LoggerFactory.getLogger(KafaConsumerService.class);
	
	/**
	 * Broker连接地址
	 */
	@NotBlank
	@Value("${kafka.brokerServerList}")
	private String  brokerServerList;
	
	/**
	 * group标识
	 */
	@Value("${kafka.consumerGroupId}")
	private  String consumerGroupId;
	
	/**
	 * key反序列化方式
	 */
	@NotBlank
	@Value("${kafka.keyDeserializer}")
	private String keyDeserializer;
	
	/**
	 * value反序列化方式
	 */
	@NotBlank
	@Value("${kafka.valueDeserializer}")
	private String valueDeserializer;
	
	/**
	 * 消费者提交方式
	 */
	@NotBlank
	@Value("${kafka.consumerCommitType}")
	private String consumerCommitType;
	
	@Value("${kafka.pullIntervalTime}")
	private long pullIntervalTime;
	
 
	private KafkaConsumer<String,V>  kafkaConsumer;
	
	 
	private String topicName="HTTP_REQUEST_ROUTE_TOPIC3";
	
	@Override
	public void afterPropertiesSet() throws Exception {
		 
		Properties pro  = new Properties();
		pro.put(ParamConstants.KAFKA_SERVER_LIST,this.brokerServerList);
		pro.put(ParamConstants.CONSUMER_GROUP_ID,this.consumerGroupId);
		pro.put(ParamConstants.CONSUMER_KEY_DESERIALIZER,StringDeserializer.class);
		pro.put(ParamConstants.CONSUMER_VALUE_DESERIALIZER,StringDeserializer.class);
		
		
		pro.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 256);
		pro.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
		pro.put(ParamConstants.CONSUMER_AUTO_COMMIT_TYPE,this.consumerCommitType);
		pro.put(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, 180 * 1000L);
		//pro.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
		kafkaConsumer = new KafkaConsumer<String,V>(pro);
		List<String> topicList = new ArrayList<String>();
		topicList.add(topicName);
		kafkaConsumer.subscribe(topicList);
		logger.info("Kafka消费者连接kafka={}实例启动成功!",this.brokerServerList);
	 
	}

	public void consumer() {
		List<PartitionInfo> partitions = kafkaConsumer.partitionsFor(topicName);
		System.out.println("partitions 的数量是 ： "+partitions.size());
		try {
			while(true) {
				//ConsumerRecords<String, V> records = kafkaConsumer.poll(Duration.ofMillis(this.pullIntervalTime));
				ConsumerRecords<String, V> records = kafkaConsumer.poll( this.pullIntervalTime);	
				 
				Iterable<ConsumerRecord<String, V>> iterator = records.records(topicName); 
				iterator.forEach(record->{
					String value = (String)record.value();
					logger.info("消费kafka内容：partition={},offset={},key={},value={}",record.partition(),
									record.offset(),record.key(),record.value());
					 
				});
				kafkaConsumer.commitSync();
			}
		}catch(Exception ex) {
			logger.error("获取异常",ex);
		}
		
		
	}
	
	public void consumerOnRebalancec() {
		
	}
}
