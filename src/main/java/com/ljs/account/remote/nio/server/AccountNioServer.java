/**
 * 
 */
package com.ljs.account.remote.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author lijunshi
 *
 */
public class AccountNioServer {

	public void openServer() {
		/**1. 创建ServerSocketChannel,用于监听客户端连接，所有客户端连接的父管道
		 * 此时ServerSocketChannel没有绑定任何地址和端口，不能接受连接。
		 */
		try {
			ServerSocketChannel serverSocket = ServerSocketChannel.open();
			// 2. 绑定监听地址，默认绑定本机地址 
			InetSocketAddress address = new InetSocketAddress(9999);
			serverSocket.bind(address);
			// 2.1 设置非阻塞
			serverSocket.configureBlocking(false);
			/**
			 * 3. 创建Selector，调用Linux底层使用epoll，Windows底层select;
			 * 3.1 创建多路复用器，并启动线程
			 */
			Selector selector = Selector.open();
			// 4. 将ServerSocketChannel注册到Selector上，监听
			SelectionKey selectionKey = serverSocket.register(selector,SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}
