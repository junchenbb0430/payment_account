/**
 * 
 */
package com.ljs.account.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lijunshi
 *
 */
public class InboundHandlerTestB extends ChannelInboundHandlerAdapter {

	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("InboundHandlerB : "+msg);
		ctx.fireChannelRead(msg);
    }
}
