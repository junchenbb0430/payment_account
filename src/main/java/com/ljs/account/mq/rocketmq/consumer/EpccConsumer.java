/**
 * 
 */
package com.ljs.account.mq.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author lijunshi
 *
 */
@Component
public class EpccConsumer extends AbstractDefaultPushConsumer {
	private static Logger logger = LoggerFactory.getLogger(EpccConsumer.class);
	@Override
	public ConsumeConcurrentlyStatus consumeMessage(String messageContent) {
		logger.info("接收到消息为={}",messageContent);
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}

}
