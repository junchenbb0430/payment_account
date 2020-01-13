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
public class SupplierCService implements IPhoneSupplierService{

	@Override
	public PhoneScreen getSupplierPhoneScreenInfo() {
		PhoneScreen screen = new PhoneScreen();
		double priceFactor = getPriceFactor()*13;
		BigDecimal price = new BigDecimal(priceFactor);
		screen.setPrice(price);
		screen.setSupplierCode("C1000");
		screen.setSupplierName(this.getClass().getName());
		return screen;
	}

	@Override
	public int getSupplierTransportHoure() {
		// TODO Auto-generated method stub
		return 0;
	}

 

}

