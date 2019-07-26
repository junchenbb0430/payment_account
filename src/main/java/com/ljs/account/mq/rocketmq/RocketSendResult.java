/**
 * 
 */
package com.ljs.account.mq.rocketmq;

import com.ljs.account.mq.result.CommonSendResult;

import lombok.Data;

/**
 * @author lijunshi
 *
 */
@Data
public class RocketSendResult extends CommonSendResult {

	/**
	 * 事物ID
	 */
	private String transactionId;
}
