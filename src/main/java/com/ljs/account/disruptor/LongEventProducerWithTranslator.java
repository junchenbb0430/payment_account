/**
 * 
 */
package com.ljs.account.disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * @author lijunshi
 * disruptor:使用Translator发布消息
 * 发布消息可以通过EventPublisher或Event Translator两种方式
 */
public class LongEventProducerWithTranslator {
	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
		super();
		this.ringBuffer = ringBuffer;
	}
	
	private static final EventTranslatorOneArg<LongEvent,ByteBuffer> translator = 
			new EventTranslatorOneArg<LongEvent,ByteBuffer>() {

				@Override
				public void translateTo(LongEvent event, long sequence, ByteBuffer bb) {
					 event.setValue(bb.getLong());
				}};
				
	public void onData(ByteBuffer bb) {
		ringBuffer.publishEvent(translator,bb);
	}
}
