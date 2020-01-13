/**
 * 
 */
package com.ljs.account.service.supplier.screen;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author lijunshi
 *   包含 手机屏幕价格，供应商名称，供应商代码
 */
@Data
public class PhoneScreen {
	
	private BigDecimal  price;
	
	private String supplierName;
	
	private String supplierCode;

	@Override
	public String toString() {
		return "PhoneScreen [price=" + price + ", supplierName=" + supplierName + ", supplierCode=" + supplierCode
				+ "]";
	}
	
	
}
