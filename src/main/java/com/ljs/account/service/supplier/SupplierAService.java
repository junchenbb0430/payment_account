/**
 * 
 */
package com.ljs.account.service.supplier;

import java.math.BigDecimal;
import java.util.function.Supplier;

import com.ljs.account.service.supplier.screen.PhoneScreen;

/**
 * @author lijunshi
 *
 */
public class SupplierAService implements IPhoneSupplierService{

	@Override
	public PhoneScreen getSupplierPhoneScreenInfo() {
		PhoneScreen screen = new PhoneScreen();
		double priceFactor = getPriceFactor()*14;
		BigDecimal price = new BigDecimal(priceFactor);
		screen.setPrice(price);
		screen.setSupplierCode("A1000");
		screen.setSupplierName(this.getClass().getName());
		try {
			Thread.sleep(5*1000);
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
