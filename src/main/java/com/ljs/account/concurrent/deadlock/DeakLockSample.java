/**
 * 
 */
package com.ljs.account.concurrent.deadlock;

/**
 * @author lijunshi
 *  死锁检测实例，线程A持有锁1，获取锁2，线程B持有锁2,获取锁1;
 */
public class DeakLockSample extends Thread {

	private String first;
	
	private String second;
	
	private String threadName;

	 
	
	public DeakLockSample(String first, String second, String threadName) {
		super();
		this.first = first;
		this.second = second;
		this.threadName = threadName;
	}



	public void run() {
		// 尝试获取锁first
		synchronized(first) {
			System.out.println(this.getThreadName()+" obtained lock :"+this.getFirst());
		
			try {
				Thread.sleep(1000);
				// 尝试获取锁sencond
				synchronized(second) {
					System.out.println(this.getThreadName()+" obtained lock :"+this.getSecond());
					
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	public String getFirst() {
		return first;
	}



	public void setFirst(String first) {
		this.first = first;
	}



	public String getSecond() {
		return second;
	}



	public void setSecond(String second) {
		this.second = second;
	}



	public String getThreadName() {
		return threadName;
	}



	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	
	
}
