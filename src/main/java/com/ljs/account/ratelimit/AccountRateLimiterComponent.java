/**
 * 
 */
package com.ljs.account.ratelimit;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.RateLimiter;
import com.ljs.account.common.utils.DateUtil;

/**
 * @author lijunshi
 *  使用Guava RateLimiter进行单机限流
 */
public class AccountRateLimiterComponent {

	
	public  static void main(String[] args) {
		/* 创建一个速率为3的 限流器, 即 3/秒  */
		RateLimiter limiter = RateLimiter.create(3.0);
		
		// 创建线程池
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		// 创建同步控制器，等线程全部执行完，再执行
		CountDownLatch countDownLatch = new CountDownLatch(30);
		 long beginTime = System.currentTimeMillis();
		for(int i=0;i<120;i++) {//模拟30个请求
			
			System.out.println(limiter.acquire()); 
			executorService.execute(()->{
				 System.out.println(DateUtil.formateDateStr(new Date(),DateUtil.FULL_DATETIME_PATTERN));
				 try {
					Thread.sleep(1*1000l);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// countDownLatch.countDown();
			});
			
		}
//		try {
//			countDownLatch.await();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		long endTime = System.currentTimeMillis();
		System.out.println("cost time is : "+(endTime-beginTime)/1000);
	}
}
