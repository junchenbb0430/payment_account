/**
 * 
 */
package com.ljs.account.concurrent.multithread.traditional;

/**
 * @author lijunshi
 *	奇数打印线程
 */
public class UnevenNumberThread implements Runnable {

	private NumberPrinter  np;
	
	
	
	
	public UnevenNumberThread(NumberPrinter np) {
		super();
		this.np = np;
	}




	@Override
	public void run() {
		while(true) {
		
		 synchronized(np) {
			 String threadName = Thread.currentThread().getName();
			 try {
				  
			 
				 if(np.getNumber()%2==0) {
					 Thread.sleep(1000);
					 np.print(threadName);
					 /**
					  *  唤醒当前等待该锁np的一个线程，
					  *  方法notify或notifyAll只能由持有锁的线程调用才生效。
					  */
					 np.notify();
					 System.out.println(threadName+" 执行 notfify方法");
					  
				 }else {
					 np.wait();;
				 }
			 }catch(Exception ex) {
				 
			 }
		 }
		  	
		}
	}

}
