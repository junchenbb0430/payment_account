/**
 * 
 */
package com.ljs.account.netty.data.login;

import com.ljs.account.netty.data.Command;
import com.ljs.account.netty.data.DataPacket;

import lombok.Data;

/**
 * @author lijunshi
 *
 */
@Data
public class LoginRequestPacket extends DataPacket {

	private String userId;
	
	private String userName;
	
	private String password;
	
	@Override
	public Byte getCommand() {
		// TODO Auto-generated method stub
		return Command.LOGIN_REQUEST;
	}

	@Override
	public String toString() {
		return "LoginRequestPacket [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	}
	
}
