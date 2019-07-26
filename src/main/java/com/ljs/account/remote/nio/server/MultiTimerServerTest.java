package com.ljs.account.remote.nio.server;

public class MultiTimerServerTest {

	public static void main(String[] args) {
		 Thread t = new Thread(new MultiplexerTimeServer(9999));
		 t.start();
		 try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
