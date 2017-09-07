package com.inventory.mgmt.ims.service.vendor;

import java.util.List;

import com.inventory.mgmt.ims.model.Vendor;

public interface VendorService {
	
	
	Vendor findById(Long id);
	Vendor findByName(String name);
	void saveVendor(Vendor vendor);

	void updateVendor(Vendor vendor);

	void deleteVendorById(Long id);

	void deleteAllVendors();

	List<Vendor> findAllVendors();

	boolean isVendorExist(Vendor vendor);
}

