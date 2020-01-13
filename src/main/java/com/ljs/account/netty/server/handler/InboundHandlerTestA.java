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
public class InboundHandlerTestA extends ChannelInboundHandlerAdapter {

	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("InboundHandlerA : "+msg);
		/***
		 * super.channelRead(ctx,msg)内部调用ctx.fireChanelRead(msg).
		 * 下面super.channelRead(ctx, msg)等价于ctx.fireChannelRead(msg)
		 */
		super.channelRead(ctx, msg);
		//ctx.fireChannelRead(msg);
    }
}
