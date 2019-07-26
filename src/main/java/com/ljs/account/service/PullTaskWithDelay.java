/**
 * 
 */
package com.ljs.account.service;

import java.util.Date;

import com.ljs.account.common.utils.DateUtil;

/**
 * @author lijunshi
 *
 */
public class PullTaskWithDelay implements Runnable {

	@Override
	public void run() {
		String currentTime = DateUtil.formateDateStr(new Date(),DateUtil.FULL_DATETIME_PATTERN);
		 System.out.println("current time : "+currentTime);
		 try {
			Thread.sleep(3*1000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
