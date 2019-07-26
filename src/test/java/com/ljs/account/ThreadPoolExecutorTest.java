/**
 * 
 */
package com.ljs.account;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.ljs.account.concurrent.future.ThreadPoolServiceExecutor;
import com.ljs.account.entity.AccountEntity;
import com.ljs.account.service.impl.AccountCaptialInflowServiceImpl;

/**
 * @author lijunshi
 *
 */
public class ThreadPoolExecutorTest extends CoreAccountApplicationTests {

	
	@Resource
	private ThreadPoolServiceExecutor  threadPoolExecutor;
	
	@Resource
	private AccountCaptialInflowServiceImpl  accountCaptialService;
	
	@Test
	public void testThreadFuture() {
		accountCaptialService.setAcctNo("6226ABCD11129876");
		FutureTask<AccountEntity> futureTask = new FutureTask<AccountEntity>(accountCaptialService);
		try {
			//AccountEntity acctEntity = threadPoolExecutor.executeTaskReturnResult(accountCaptialService);
			threadPoolExecutor.executeFutureTask(futureTask);
			System.out.println(JSON.toJSONString(futureTask.get()));
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
