/**
 * 
 */
package com.ljs.account.service.supplier;

import java.math.BigDecimal;

import com.ljs.account.service.supplier.screen.PhoneScreen;

/**
 * @author lijunshi
 *
 */
public class SupplierBService implements IPhoneSupplierService{

	@Override
	public PhoneScreen getSupplierPhoneScreenInfo() {
		PhoneScreen screen = new PhoneScreen();
		double priceFactor = getPriceFactor()*11;
		BigDecimal price = new BigDecimal(priceFactor);
		screen.setPrice(price);
		screen.setSupplierCode("B1000");
		screen.setSupplierName(this.getClass().getName());
		try {
			Thread.sleep(30*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return screen;
	}

	@Override
	public int getSupplierTransportHoure() {
		// TODO Auto-generated method stub
		return 0;
	}

	 
}

