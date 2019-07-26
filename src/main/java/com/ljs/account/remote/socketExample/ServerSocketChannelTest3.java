/**
 * 
 */
package com.ljs.account.remote.socketExample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author lijunshi
 *
 */
public class ServerSocketChannelTest3 {
 
	public void startServerInChannel() throws IOException {
		// 1.创建ServerSocketChannel
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		//serverSocketChannel.configureBlocking(false);
		//2. 绑定地址
		serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",8888));
		// 3.监听client连接，如果设置非阻塞，则socketChannel返回null
		SocketChannel socketChannel = serverSocketChannel.accept();
		// 4.读取客户端发送数据
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		int length = socketChannel.read(byteBuffer);
		while(length != -1) {
			 
			String newStr = new String(byteBuffer.array());
			System.out.println("server receive message : "+newStr);
			length = socketChannel.read(byteBuffer);
		}
		// 5.关闭连接
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		 
		ServerSocketChannelTest3 test3 = new ServerSocketChannelTest3();
		try {
			test3.startServerInChannel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
