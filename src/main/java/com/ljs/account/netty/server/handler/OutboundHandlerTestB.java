/**
 * 
 */
package com.ljs.account.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author lijunshi
 *
 */
public class OutboundHandlerTestB extends ChannelOutboundHandlerAdapter {

	@Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
     System.out.println("执行OutboundHanderTestB : "+msg);   
     super.write(ctx, msg, promise);
    }
}
