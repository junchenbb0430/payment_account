/**
 * 
 */
package com.ljs.account.disruptor.main;

import java.nio.ByteBuffer;

import com.ljs.account.disruptor.LongEvent;
import com.ljs.account.disruptor.LongEventFactory;
import com.ljs.account.disruptor.LongEventHandler;
import com.ljs.account.disruptor.LongEventProducer;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * @author lijunshi
 *
 */
public class LongEventMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 // 定义事件工厂
		LongEventFactory factory = new LongEventFactory();
		// 指定RingBuffer的大小，大小只能是2的N次幂
		int bufferSize = 1024;
		//构造Disruptor实例
		Disruptor<LongEvent> disruptor =new Disruptor<>(factory,bufferSize,DaemonThreadFactory.INSTANCE);
	    // 连接handler
		disruptor.handleEventsWith(new LongEventHandler());
		// 开启disruptor,开启所有的线程
		disruptor.start();
		// 获取RingBuffer，使用RingBuffer进行发布事件
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		// 根据RingBuffer构造Producer实例，生产者producer往RingBuffer中发布消息
		LongEventProducer producer = new LongEventProducer(ringBuffer);
		try {
			ByteBuffer bb = ByteBuffer.allocate(8);
			for(long l=0;true;l++) {
				bb.putLong(0,l);
				producer.onData(bb);
	            Thread.sleep(1000);
			}
		}catch(Exception ex) {
			
		}
		
		
	}

}
