/**
 * 
 */
package com.ljs.account.mq.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ljs.account.PaymentAccountApplicationTests;
import com.ljs.account.mq.kafka.consumer.KafaConsumerService;

/**
 * @author lijunshi
 *
 */
public class KafkaConsumerTest extends PaymentAccountApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerTest.class);
	 
	private static final String KafkaServer = "192.168.127.128:9092,192.168.127.129:9092,192.168.127.130:9092";
//	private static final String KafkaServer = "172.18.33.8:9092,172.18.33.9:9092,172.18.98.38:9092";
//	private static final String KafkaServer = "10.100.156.98:9192,10.100.156.98:9292,10.100.156.98:9392";
	private static final String Topic = "HTTP_REQUEST_ROUTE_TOPIC3";
	private static final String ConsumerGroup = "consumer0308"; //Ea123,FB
	
	@Autowired
	private KafaConsumerService  kafkaConsumer;
	@Test
	public  void testKafkaConsume() {
		kafkaConsumer.consumer();
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testKafkaConsumer() {
		
		Properties prop = buildProperties();
		@SuppressWarnings("resource")
		Consumer<String, String>  consumer = new KafkaConsumer<String, String>(prop);
		
		//subscribe cannot work together with assignment pattern
		consumer.subscribe(Arrays.asList(Topic));
			
		ConsumerRecords<String, String> records = null;
		while(true){
			records = consumer.poll(1000);
			
			if(records.count() <= 0){
				continue;
			}
//			logger.info("records count:" + records.count());
			for(ConsumerRecord<String, String> record : records){
				logger.info("partition:{}, offset:{}, tostring:{}" ,
						record.partition() , record.offset(),  record.toString());
			}
		}
//		kafkaConsumer.consumer();
//		try {
//			Thread.sleep(Integer.MAX_VALUE);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	private static Properties buildProperties(){
		Properties prop = new Properties();
		
		prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaServer);
		//auto commit offset and commit interval time
		prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
		
		prop.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 256);
		
		prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
//		prop.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 5000); // should be between group.min.session.timeout.ms(default:6000) and group.max.session.timeout.ms(default:10000)
//		prop.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 6000);
		
		
		prop.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumerTest-lsd-0308-2");
		prop.put(ConsumerConfig.GROUP_ID_CONFIG, ConsumerGroup);
		prop.put(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, 180 * 1000L);
		
		prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		return prop;
	}
}
