/**
 * 
 */
package com.ljs.account.netty.client;

import com.ljs.account.netty.client.handler.WeichartClientHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * @author lijunshi
 *
 */
public class ClientChannelInitializer extends ChannelInitializer<Channel> {

	 

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ChannelPipeline pipeline =  ch.pipeline();
		//pipeline.addLast(new FirstClientHanlder());
		pipeline.addLast(new WeichartClientHandler());
	}

}
