/**
 * 
 */
package com.ljs.account.netty.data.login;

import com.ljs.account.netty.data.Command;
import com.ljs.account.netty.data.DataPacket;

import lombok.Data;

/**
 * @author lijunshi
 *  应答数据包对象
 */
@Data
public class LoginResponseDataPacket extends DataPacket {

	private String userId;
	
	private String userName;
	
	private String  status;
	
	private String  statusInfo;
	
	@Override
	public Byte getCommand() {
		 
		return Command.LOGIN_RESPONSE;
	}

	
}
