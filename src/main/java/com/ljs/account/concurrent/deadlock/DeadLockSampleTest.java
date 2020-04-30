package com.ljs.account.concurrent.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DeadLockSampleTest {

	public static void main(String[] args) {
		String first = "firstLock";
		String second = "secondLock";
		DeakLockSample  lockSampleA = new DeakLockSample(first,second,"Thread A");
		
		DeakLockSample  lockSampleB = new DeakLockSample(second,first,"Thread B");
		lockSampleA.start();
		
		lockSampleB.start();
		
		ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
		Runnable dcheck = new Runnable() {

			@Override
			public void run() {
				long[] threadIds =  mbean.findDeadlockedThreads();
				if(null != threadIds) {
					ThreadInfo[] threadInfos = mbean.getThreadInfo(threadIds);
					System.out.println("detected deadlock threads :");
					for(ThreadInfo threadInfo:threadInfos) {
						System.out.println(threadInfo.getThreadName());
					}
				}
			}};
			
			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			scheduler.scheduleAtFixedRate(dcheck, 5l, 10l, TimeUnit.SECONDS);
		try {
//			lockSampleA.join();
//			
//			lockSampleB.join();
		}catch(Exception ex) {
			
		}
		
	}

}
