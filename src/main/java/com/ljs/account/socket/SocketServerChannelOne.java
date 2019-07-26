package com.ljs.account.socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import  java.io.InputStream;
import java.nio.channels.ServerSocketChannel;

public class SocketServerChannelOne {

    public void  startServer(){
        // 1. 创建ServerSocketChannel
        ServerSocketChannel  serverSocketChannel = null;

        try {
            serverSocketChannel = ServerSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 2. 创建ServerSocket实例
            ServerSocket  serverSocket = serverSocketChannel.socket();
        try {
            serverSocket.bind(new InetSocketAddress("127.0.0.1",8888));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket  socket = null;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader  inputStreamReader = new InputStreamReader(inputStream);



    }

}
