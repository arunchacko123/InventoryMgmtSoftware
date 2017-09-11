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

import com.inventory.mgmt.ims.model.PurchaseOrder;
import com.inventory.mgmt.ims.service.purchaseorder.POService;
import com.inventory.mgmt.ims.util.CustomErrorType;



@RestController
@RequestMapping("/pomgmt")

public class POController {
	
	public static final Logger logger = LoggerFactory.getLogger(POController.class);

	@Autowired
	POService poService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Purchase order---------------------------------------------

	@RequestMapping(value = "/purchaseorder/", method = RequestMethod.GET)
	public ResponseEntity<List<PurchaseOrder>> listAllPurchaseOrders() {
		List<PurchaseOrder> purchaseOrder = poService.findAllPurchaseOrders();
		if (purchaseOrder.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<PurchaseOrder>>(purchaseOrder, HttpStatus.OK);
	}

	// -------------------Retrieve Single Purchase order------------------------------------------

	@RequestMapping(value = "/purchaseorder/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPurchaseOrder(@PathVariable("id") long id) {
		logger.info("Fetching PurchaseOrder with id {}", id);
		PurchaseOrder purchaseOrder = poService.findById(id);
		if (purchaseOrder == null) {
			logger.error("PurchaseOrder with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("PurchaseOrder with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PurchaseOrder>(purchaseOrder, HttpStatus.OK);
	}

	// -------------------Create a Purchase order-------------------------------------------

	@RequestMapping(value = "/purchaseorder/", method = RequestMethod.POST)
	public ResponseEntity<?> createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder, UriComponentsBuilder ucBuilder) {
		logger.info("Creating PurchaseOrder : {}", purchaseOrder);

		if (poService.isPurchaseOrderExist(purchaseOrder)) {
			logger.error("Unable to create. A PurchaseOrder with name {} already exist", purchaseOrder.getPoNumber());
			return new ResponseEntity(new CustomErrorType("Unable to create. A PurchaseOrder with name " + 
					purchaseOrder.getPoNumber() + " already exist."),HttpStatus.CONFLICT);
		}
		poService.savePurchaseOrder(purchaseOrder);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/purchaseOrder/{id}").buildAndExpand(purchaseOrder.getPoId()).toUri());// need to change
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Purchase order ------------------------------------------------

	@RequestMapping(value = "/purchaseorder/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePurchaseOrder(@PathVariable("id") long id, @RequestBody PurchaseOrder purchaseOrder) {
		logger.info("Updating PurchaseOrder with id {}", id);

		PurchaseOrder currentPurchaseOrder = poService.findById(id);

		if (currentPurchaseOrder == null) {
			logger.error("Unable to update. PurchaseOrder with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. PurchaseOrder with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentPurchaseOrder.setDescription(purchaseOrder.getDescription());
		currentPurchaseOrder.setTotalAmount(purchaseOrder.getTotalAmount());
		currentPurchaseOrder.setVendor(purchaseOrder.getVendor());

		poService.updatePurchaseOrder(currentPurchaseOrder);
		return new ResponseEntity<PurchaseOrder>(currentPurchaseOrder, HttpStatus.OK);
	}

	// ------------------- Delete a Purchase order-----------------------------------------

	@RequestMapping(value = "/purchaseorder/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePurchaseOrder(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting PurchaseOrder with id {}", id);

		PurchaseOrder purchaseOrder = poService.findById(id);
		if (purchaseOrder == null) {
			logger.error("Unable to delete. PurchaseOrder with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. PurchaseOrder with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		poService.deletePurchaseOrderById(id);
		return new ResponseEntity<PurchaseOrder>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Purchase order's-----------------------------

	@RequestMapping(value = "/purchaseorder/", method = RequestMethod.DELETE)
	public ResponseEntity<PurchaseOrder> deleteAllPurchaseOrders() {
		logger.info("Deleting All Purchaseorders");

		poService.deleteAllPurchaseOrders();
		return new ResponseEntity<PurchaseOrder>(HttpStatus.NO_CONTENT);
	}

}
