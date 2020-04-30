/**
 * 
 */
package com.ljs.account.concurrent.asynExecute;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.ljs.account.service.supplier.IPhoneSupplierService;
import com.ljs.account.service.supplier.SupplierAService;
import com.ljs.account.service.supplier.SupplierBService;
import com.ljs.account.service.supplier.SupplierCService;
import com.ljs.account.service.supplier.screen.PhoneScreen;

/**
 * @author lijunshi
 *
 */
public class CompletableFutureTest {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/**
		 * lambda表达式如同接口中使用匿名内部类
		 * CompletableFuture属于异步执行任务，
		 * 创建CompletableFuture实例主要有如下四个方法：
		 * CompletableFuture.supplyAsync(supplier)，返回结果，通过get方法获取执行结果
		 * CompletableFuture.supplyAsync(Supplier,Executor)，返回结果，通过get方法获取执行结果
		 * CompletableFuture.runAsync(Runnable),无返回值
		 * CompletableFuture.runAsync(Runnable,Executor),无返回值
		 */
		IPhoneSupplierService supplierA = new SupplierAService();
		CompletableFuture<PhoneScreen>  fa = CompletableFuture.supplyAsync(()->{
			 return supplierA.getSupplierPhoneScreenInfo();
		}); 
		IPhoneSupplierService supplierB = new SupplierBService();
		CompletableFuture<PhoneScreen>  fb = CompletableFuture.supplyAsync(()->{
			 return supplierB.getSupplierPhoneScreenInfo();
		}); 
		IPhoneSupplierService supplierC = new SupplierCService();
		CompletableFuture<PhoneScreen>  fc = CompletableFuture.supplyAsync(()->{
			 return supplierC.getSupplierPhoneScreenInfo();
		}); 
		System.out.println("供应商A ： "+fa.get().toString());
		System.out.println("供应商B ： "+fb.get().toString());
		System.out.println("供应商C ： "+fc.get().toString());
		System.out.println("ddddddddddddddd");
	}

}
