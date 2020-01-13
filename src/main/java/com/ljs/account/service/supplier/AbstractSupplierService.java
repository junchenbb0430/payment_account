package com.ljs.account.service.supplier;

import java.util.function.Supplier;

import com.ljs.account.service.supplier.screen.PhoneScreen;

public abstract class AbstractSupplierService implements IPhoneSupplierService, Supplier<PhoneScreen> {

	public PhoneScreen  get() {
		return this.getSupplierPhoneScreenInfo();
	}
}
