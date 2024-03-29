/**
 * 
 */
package com.ljs.account.mq.kafka.producer;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.ljs.account.common.utils.DateUtil;
import com.ljs.account.common.utils.RandomFlowServiceUtil;

/**
 * @author lijunshi
 *
 */
public class KafkaServerTest {

	public static final String brokerList = "192.168.127.128:9092,192.168.127.129:9092,192.168.127.130:9092";
   
	public static final String topic = "HTTP_REQUEST_ROUTE_TOPIC";
	
	/**
	* @param args
	*/
	public static void main(String[] args) {
		 Properties props = new Properties();
		 props.put("bootstrap.servers",brokerList  );
		 props.put("acks","1"  );
		 props.put("key.serializer",
	                "org.apache.kafka.common.serialization.StringSerializer");
	        props.put("value.serializer",
	                "org.apache.kafka.common.serialization.StringSerializer");
	        props.put("client.id", "producer.client.id.demo");
	        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
	       
	        try {
	        	for(int i=0;i<29;i++) {
	        		 ProducerRecord<String, String> record =
	     	                new ProducerRecord<>(topic,RandomFlowServiceUtil.generateRandomFlow(), "Hello, Kafka2!");
	        		Future<RecordMetadata> future =producer.send(record);
		        	RecordMetadata metadata = future.get();
		        	int partitionNum = metadata.partition();
		        	long offset = metadata.offset();
		            System.out.println("消息发送成功,偏移量 : "+offset+",分区号:"+partitionNum);
	        	}
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
