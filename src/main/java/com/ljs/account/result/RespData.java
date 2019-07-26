/**
 * 
 */
package com.ljs.account.result;

import com.ljs.account.common.enums.CodeEnum;

import lombok.Data;

/**
 * @author lijunshi
 *
 */
@Data
public class RespData<T> {

	private String code;
	
	private String info;
	
	private T result;
	
	public void  setCodeEnum(CodeEnum codeEnum) {
		this.code = codeEnum.getCode();
		this.info = codeEnum.getInfo();
	}
}
