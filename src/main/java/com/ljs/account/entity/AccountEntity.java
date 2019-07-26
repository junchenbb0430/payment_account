/**
 * 
 */
package com.ljs.account.entity;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author lijunshi
 *
 */
@Data
public class AccountEntity {

	private String acctNo;
	
	private BigDecimal amount;
}
