package com.ljs.account.remote.nio.client;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class TimeClientHandlerTest {

	public static void main(String[] args) {
		 Thread t = new Thread(new TimeClientHandle("127.0.0.1",9999));
		 t.start();
		 try {
//			 SocketChannel socketChannel = SocketChannel.open();
//			 socketChannel.configureBlocking(false);
//			boolean connectResult =  socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));
//			if(connectResult == false) {
//				System.out.println("连接结果 connectResult = "+connectResult);
//				while(!socketChannel.finishConnect()) {
//					System.out.println("客户端一直尝试连接");
//				}
//			}
//			System.out.println("客户端已经连接上服务端!");
//			Selector selector = Selector.open();
//			socketChannel.register(selector,SelectionKey.OP_READ);
			Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
