/**
 * 
 */
package com.ljs.account.netty.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lijunshi
 *
 */
public class NettyClient {

	/**
	 * 最大重试次数
	 */
	
	
	public   static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for(int i=0;i<1;i++) {
			executorService.execute(new ClientService());
		}
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
