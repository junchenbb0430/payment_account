package com.ljs.account.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {

	public static void main(String[] args) throws InterruptedException {
		// 创建速率为5的限流器，即每秒产生5个令牌
		RateLimiter limiter = RateLimiter.create(5);
//		try {
//			Thread.sleep(2*1000l);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(limiter.acquire(10));
		//System.out.println(limiter.acquire(5));
		
		System.out.println(limiter.acquire());
		long begin = System.currentTimeMillis();
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
		long end = System.currentTimeMillis();
		System.out.println(end-begin);
	}

}
