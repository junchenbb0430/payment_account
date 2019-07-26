package com.ljs.account.json;

import java.util.Date;

import lombok.Data;
@Data
public class MessageBo {

	private String bankSeq;
	
	private String channelId;
	
	private String channelType;
	
	private Date transTime;

	 
}
