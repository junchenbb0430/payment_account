/**
 * 
 */
package com.ljs.account.remote.socketExample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author lijunshi
 *	
 */
public class ServerSocketChannelBlockTest2 {

	/**
	 * @throws IOException 
	 * 
	 */
	public void startServerSocketBlocking() throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		// 1. 设置非阻塞模式
		serverSocketChannel.configureBlocking(true);
		serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",8888));
		System.out.println("SocketChannel Blocking Mode is : "+serverSocketChannel.isBlocking());
		// 3.监听客户端连接，一直阻塞到有client连接进来
		SocketChannel socketChannel = serverSocketChannel.accept();
		System.out.println("listing client socketChannel : "+socketChannel);
	} 
	
	/**
	 * @throws IOException 
	 * 非阻塞模式serverSocketChannel.configureBlocking(false);
	 * accept()返回一个空对象，
	 */
	public void startServerSocketNotBlocked() throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		// 1. 设置非阻塞模式
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",8888));
		System.out.println("SocketChannel Blocking Mode is : "+serverSocketChannel.isBlocking());
		// 2. 非阻塞模式，没有client连接，则返回null。
		// 2.1如果有连接进来，则是OS进行异步通知
		SocketChannel socketChannel = serverSocketChannel.accept();
		System.out.println("listing client socketChannel : "+socketChannel);
	}
	
	
	public static void main(String[] args) throws IOException {
		ServerSocketChannelBlockTest2  test2 = new ServerSocketChannelBlockTest2();
		//test2.startServerSocketBlocking();
		System.out.println("***********非阻塞模式启动Server****************");
		test2.startServerSocketNotBlocked();
	}

}
