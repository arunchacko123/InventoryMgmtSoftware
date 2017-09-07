package com.inventory.mgmt.ims.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.inventory.mgmt.ims.model.Vendor;
import com.inventory.mgmt.ims.service.vendor.VendorService;
import com.inventory.mgmt.ims.util.CustomErrorType;

@RestController
@RequestMapping("/vendormgmt")

public class VendorController {
	
	public static final Logger logger = LoggerFactory.getLogger(VendorController.class);

	@Autowired
	VendorService vendorService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Vendor---------------------------------------------

	@RequestMapping(value = "/vendor/", method = RequestMethod.GET)
	public ResponseEntity<List<Vendor>> listAllVendors() {
		List<Vendor> vendor = vendorService.findAllVendors();
		if (vendor.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Vendor>>(vendor, HttpStatus.OK);
	}

	// -------------------Retrieve Single Vendor------------------------------------------

	@RequestMapping(value = "/vendor/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getVendor(@PathVariable("id") long id) {
		logger.info("Fetching Vendor with id {}", id);
		Vendor vendor = vendorService.findById(id);
		if (vendor == null) {
			logger.error("Vendor with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Vendor with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vendor>(vendor, HttpStatus.OK);
	}

	// -------------------Create a Vendor-------------------------------------------

	@RequestMapping(value = "/vendor/", method = RequestMethod.POST)
	public ResponseEntity<?> createVendor(@RequestBody Vendor vendor, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Vendor : {}", vendor);

		if (vendorService.isVendorExist(vendor)) {
			logger.error("Unable to create. A Vendor with name {} already exist", vendor.getVendorName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Vendor with name " + 
					vendor.getVendorName() + " already exist."),HttpStatus.CONFLICT);
		}
		vendorService.saveVendor(vendor);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Vendor ------------------------------------------------

	@RequestMapping(value = "/vendor/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateVendor(@PathVariable("id") long id, @RequestBody Vendor vendor) {
		logger.info("Updating Vendor with id {}", id);

		Vendor currentVendor = vendorService.findById(id);

		if (currentVendor == null) {
			logger.error("Unable to update. Vendor with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Vendor with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentVendor.setVendorName(vendor.getVendorName());
		currentVendor.setVendorAddress(vendor.getVendorAddress());
		currentVendor.setVendorCode(vendor.getVendorCode());

		vendorService.updateVendor(currentVendor);
		return new ResponseEntity<Vendor>(currentVendor, HttpStatus.OK);
	}

	// ------------------- Delete a Vendor-----------------------------------------

	@RequestMapping(value = "/vendor/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteVendor(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Vendor with id {}", id);

		Vendor vendor = vendorService.findById(id);
		if (vendor == null) {
			logger.error("Unable to delete. Vendor with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Vendor with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		vendorService.deleteVendorById(id);
		return new ResponseEntity<Vendor>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Vendor's-----------------------------

	@RequestMapping(value = "/vendor/", method = RequestMethod.DELETE)
	public ResponseEntity<Vendor> deleteAllVendors() {
		logger.info("Deleting All Vendor");

		vendorService.deleteAllVendors();
		return new ResponseEntity<Vendor>(HttpStatus.NO_CONTENT);
	}

}
