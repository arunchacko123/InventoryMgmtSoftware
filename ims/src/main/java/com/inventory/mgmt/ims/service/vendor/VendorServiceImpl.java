package com.inventory.mgmt.ims.service.vendor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.mgmt.ims.model.Vendor;
import com.inventory.mgmt.ims.repositories.VendorRepository;

@Service("vendorService")
@Transactional
public class VendorServiceImpl implements VendorService{
	@Autowired
	
	private VendorRepository vendorRepository;

	public Vendor findById(Long id) {
		return vendorRepository.findOne(id);
	}
	public Vendor findByName(String name) {
		return vendorRepository.findByName(name);
	}

	public void saveVendor(Vendor vendor) {
		vendorRepository.save(vendor);
	}

	public void updateVendor(Vendor vendor){
		saveVendor(vendor);
	}

	public void deleteVendorById(Long id){
		vendorRepository.delete(id);
	}

	public void deleteAllVendors(){
		vendorRepository.deleteAll();
	}

	public List<Vendor> findAllVendors(){
		return vendorRepository.findAll();
	}

	public boolean isVendorExist(Vendor vendor) {
		return findByName(vendor.getVendorName()) != null;
	}


}
