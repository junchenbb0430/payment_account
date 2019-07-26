package com.ljs.account.mq.rocketmq.producer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

/**
 * @author lijunshi
 *
 */
@Data
public class ProducerConfig {

	@Value("${rocketMQNameServerAddr}")
	private String nameServAddr;
	 
	 
	public void checkParam() {
		if(StringUtils.isBlank(nameServAddr)) {
			throw new IllegalArgumentException("MQ Address must not be null!");
		}
		 
	}
}
