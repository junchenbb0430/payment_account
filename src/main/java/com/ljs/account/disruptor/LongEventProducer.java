/**
 * 
 */
package com.ljs.account.disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

/**
 * @author lijunshi
 *
 */
public class LongEventProducer {

		private final RingBuffer<LongEvent> ringBuffer;
		
		public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
			this.ringBuffer = ringBuffer;
		}
		
		public void onData(ByteBuffer bb) {
			// 获取sequence
			long sequence = ringBuffer.next();
			try {
				LongEvent event = ringBuffer.get(sequence);
				event.setValue(bb.getLong(0));
			}catch(Exception ex) {
				
			}finally {
				ringBuffer.publish (sequence);
			}
			
		}
}
