package com.ljs.account.concurrent.multithread.traditional;

public class NumberPrinterWaitNotifyTest {

	public static void main(String[] args) {
		 NumberPrinter np = new NumberPrinter();
		 Thread  evenThread = new Thread(new EvenNumberThread(np));
		 evenThread.setName("偶数线程");
		 Thread  unevenThread = new Thread(new UnevenNumberThread(np));
		 unevenThread.setName("奇数线程");
		 evenThread.start();
		 unevenThread.start();
		 
		 try {
			 evenThread.join(15*1000l);
			 System.out.println("************************");
			 Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
