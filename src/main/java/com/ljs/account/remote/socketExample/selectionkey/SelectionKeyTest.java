/**
 * 
 */
package com.ljs.account.remote.socketExample.selectionkey;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * @author lijunshi
 *
 */
public class SelectionKeyTest {
	
	public static void main(String[] args) throws IOException {
		// 1. 
		ServerSocketChannel  serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",8888));
		serverSocketChannel.configureBlocking(false);
		 
		Selector selector = Selector.open();
		/** 2. 注册到多路复用器的时候，必须是非阻塞模式。
		 *   源码中检查阻塞模式：
		 *   if (blocking)
                throw new IllegalBlockingModeException();
		 */
		
		SelectionKey selectionKey = serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
		 
		System.out.println("ServerSocketChannel register result : "+serverSocketChannel.isRegistered());
		System.out.println("ServerSocketChannel block result : "+serverSocketChannel.isBlocking()); 
		System.out.println("ServerSocketChannel open result : "+serverSocketChannel.isOpen());;
		
		System.out.println(selectionKey.isReadable());
		System.out.println(selectionKey.isWritable());
		serverSocketChannel.accept();
		System.out.println(selectionKey.isAcceptable());
		System.out.println("SelectionKey is valid : "+selectionKey.isValid());
		System.out.println("SelectChannel is connected : "+selectionKey.isConnectable());
		selectionKey.cancel();
		System.out.println("SelectionKey is valid : "+selectionKey.isValid());
		/**
		 * keys()：返回所有注册在Selector上的通道，无论是否可用。
		 * SelectedKeys():返回所有注册在Selector上的可用通道
		 */
		Set<SelectionKey> keySet = selector.keys();
		for(SelectionKey key:keySet) {
			System.out.println(key.channel());
		}
	}
}
