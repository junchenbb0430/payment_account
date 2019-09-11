/**
 * 
 */
package com.ljs.account.common.utils;

import java.util.Date;
import java.util.Random;

/**
 * @author lijunshi
 *
 */
public class RandomFlowServiceUtil {

	public static String generateRandomFlow() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(DateUtil.formateDateStr(new Date(),DateUtil.FULL_DATETIME_PATTERN));
		Random  random = new Random();
		buffer.append(random.nextInt(1000000));
		return  buffer.toString();
	}
}
