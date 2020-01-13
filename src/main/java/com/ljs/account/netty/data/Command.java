/**
 * 
 */
package com.ljs.account.netty.data;

/**
 * @author lijunshi
 *
 */
public interface Command {

	/**
	 * 1. 登录命令
	 */
	Byte  LOGIN_REQUEST = 1;
	
	Byte  LOGIN_RESPONSE =2;
	
	byte  SEND_REQUEST = 3;
	
	byte  SEND_RESPONSE = 4;
}
