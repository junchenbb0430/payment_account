package com.ljs.account.mq.rocketmq;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
 

public class ProducerTest {
	 

    @Test
    public void testProducerSendMessage(){
        DefaultMQProducer producer = new DefaultMQProducer("payment_account");
        producer.setNamesrvAddr("");
        String  topicName = "PAYMENT_ACCOUNT";
        String value = "{\"bankSeq\":\"2019070880683925920481280110806\",\"channelId\":\"NUCC_epcc\",\"channelType\":\"0331\",\"errorCode\":\"6301\",\"errorType\":\"ERROR_REEXCHANGE\",\"msgKey\":\"check_error_20190723162822_746752468134\",\"oriPaymentSeq\":\"2019070400000000003621690210406\",\"paymentSeq\":\"2019070880683925920481280110806\",\"requestTrxId\":\"2019070880683925920481280110806\",\"transAmount\":750,\"transTime\":\"2019-07-23 16:28:21\",\"trxId\":\"2019070880683925920481280110806\"}";
        Message  message  = new Message(topicName,value.getBytes());
        try {
            SendResult sendResult = producer.send(message);
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
