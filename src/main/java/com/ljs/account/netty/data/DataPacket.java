/**
 * 
 */
package com.ljs.account.netty.data;

import lombok.Data;

/**
 * @author lijunshi
 * 数据包协议格式：
 *--------------------------------------------- 
 *|协议格式:魔法数|版本号|序列化算法|指令|数据长度|数据内容|
 * --------------------------------------------------------
 *|占用字节 : 4     1      1      1    1      4        N   |
 *---------------------------------------------------------
 */
@Data
public abstract class DataPacket {
	
	private byte  version = 1;
	
	
	/**
	 * 指令
	 * @return
	 */
	public  abstract Byte  getCommand();
}
