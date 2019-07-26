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
public class PullTaskWithFixedRate implements Runnable {
 
	@Override
	public void run() {
		 String currentTime = DateUtil.formateDateStr(new Date(),DateUtil.FULL_DATETIME_PATTERN);
		 System.out.println( " current time : "+currentTime);
//		 try {
//			    if(count %15==0) {
//			    	Thread.sleep(10*1000l);
//			    }else {
//			    	Thread.sleep(5*1000l);
//			    }
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	}

}
