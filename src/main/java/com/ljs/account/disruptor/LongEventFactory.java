/**
 * 
 */
package com.ljs.account.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author lijunshi
 * @param <T>
 *  disruptor:定义事件工厂
 */
public class LongEventFactory implements EventFactory<LongEvent> {

	@Override
	public LongEvent newInstance() {
		 
		return new LongEvent();
	}

}
