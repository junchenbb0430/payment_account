/**
 * 
 */
package com.ljs.account.service.supplier;

import java.math.BigDecimal;
import java.util.Random;

import com.ljs.account.service.supplier.screen.PhoneScreen;

/**
 * @author lijunshi
 * 功能：定义供应商服务功能，主要包含如下功能：
 * 	  定义某一个商品零件的服务价格
 *        供应商的供货时效 
 *   从不同的供应商提供的手机屏幕价格中，选择价格最低的供应商。
 */
public interface IPhoneSupplierService {

	/**
	 * 获取供应商手机屏幕的价格
	 * @return
	 */
	PhoneScreen   getSupplierPhoneScreenInfo();
	
	/***
	 * 获取供应商的供货时间
	 * @return
	 */
	int  getSupplierTransportHoure();
	
	default double getPriceFactor() {
		
		Random random = new Random(10);
		
		return random.nextDouble();
	}
}
