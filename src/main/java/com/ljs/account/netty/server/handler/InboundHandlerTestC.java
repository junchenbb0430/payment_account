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
public class InboundHandlerTestC extends ChannelInboundHandlerAdapter {

	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("InboundHandlerC : "+msg);
		ctx.fireChannelRead(msg);
    }
}
