/**
 * 
 */
package com.ljs.account.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author lijunshi
 * disruptor:定义consumer,消费producer产生的事件
 */
public class LongEventHandler implements EventHandler<LongEvent> {

	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		 System.out.println("event : "+event+"-->value:"+event.getValue()+"--->sequence : "+sequence);

	}

}
