package com.ljs.account.io.mappedByteBuffer;

import java.io.IOException;
import java.net.URI;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MappedByteBufferTest {

	private static final String FILE_NAME = "payment_account_Info.log";
	
	private static final String BASIC_PATH="file";
	
	public static void main(String[] args) {
		 
		// 1. 根据文件构造Path对象
		Path path = FileSystems.getDefault().getPath(BASIC_PATH,new String[] {FILE_NAME});
		try {
		// 2. 创建fileChannel(读，写，映射，持有文件内容)
			FileChannel fileChannel = FileChannel.open(path, new StandardOpenOption[] {StandardOpenOption.READ,StandardOpenOption.WRITE});
		// 3.映射文件内容
			 MappedByteBuffer  mappedBuffer = fileChannel.map(MapMode.READ_WRITE, 0,"ssssssssssss22".getBytes().length);
			 
			 mappedBuffer.put("ssssssssssss22".getBytes());
			  
			 // 4. 强制刷新写入到文件中
			 mappedBuffer.force();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
