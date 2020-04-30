package com.ljs.account.concurrent.multithread.traditional;

public class ThreadJoinTest {

	public static void main(String[] args) {
		System.out.println("3/10 = "+3/10);
		System.out.println("3%10 = "+3%10);
		System.out.println("10/3 = "+10/3);
		System.out.println("10%3 = "+10%3);
		Thread ta = new Thread(new ThreadA());
		Thread tb = new Thread(new ThreadB());
		ta.start();
		tb.start();
		try {
			/***
			 * join方法，加入的意思。即thread A 调用该方法join，即是让线程A加入到当前线程
			 * 执行中，即A要比当前线程优先执行。
			 * 线程A执行完后，再执行当前线程。
			 * join(time):当前线程调用线程 B的join方法，等到时间到了之后，即可执行当前线程。
			 * join线程可以实现线程条件达到一定程度之后，触发当前线程运行。可以参考
			 * CountDownLatch类的功能.
			 */
			ta.join();
			tb.join(10*1000l);;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main thead starting");
	}

	
}
class ThreadA implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(5*1000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("****ThreadA is starting!****");
	}
	
}

class ThreadB implements Runnable{

	@Override
	public void run() {
		 try {
			Thread.sleep(20*1000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("****ThreadB is starting!****");
	}
	
}
