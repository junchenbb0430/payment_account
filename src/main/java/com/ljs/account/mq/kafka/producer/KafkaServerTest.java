/**
 * 
 */
package com.ljs.account.mq.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author lijunshi
 *
 */
public class KafkaServerTest {

	public static final String brokerList = "10.112.4.7:9092,10.112.9.13:9092,10.112.9.12:9092";
    public static final String topic = "ACCOUNT";
	
	/**
	* @param args
	*/
	public static void main(String[] args) {
		 Properties props = new Properties();
		 props.put("bootstrap.servers",brokerList  );
		 props.put("key.serializer",
	                "org.apache.kafka.common.serialization.StringSerializer");
	        props.put("value.serializer",
	                "org.apache.kafka.common.serialization.StringSerializer");
	        props.put("client.id", "producer.client.id.demo");
	        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
	        ProducerRecord<String, String> record =
	                new ProducerRecord<>(topic, "Hello, Kafka2!");
	        try {
	            producer.send(record);
	            System.out.println("消息发送成功!");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
