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

import com.inventory.mgmt.ims.model.UnitOfMeasure;
import com.inventory.mgmt.ims.service.unitofmeasure.UnitOfMeasureService;
import com.inventory.mgmt.ims.util.CustomErrorType;



@RestController
@RequestMapping("/unitofmeasuremgmt")

public class UnitOfMeasureController {
	
	public static final Logger logger = LoggerFactory.getLogger(UnitOfMeasureController.class);

	@Autowired
	UnitOfMeasureService unitOfmeasureservice; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All unitOfmeasures---------------------------------------------

	@RequestMapping(value = "/unitofmeasure/", method = RequestMethod.GET)
	public ResponseEntity<List<UnitOfMeasure>> listAllUsers() {
		List<UnitOfMeasure> unitofmeasure = unitOfmeasureservice.findAllUnitOfMeasure();
		if (unitofmeasure.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<UnitOfMeasure>>(unitofmeasure, HttpStatus.OK);
	}

	// -------------------Retrieve Single UnitOfMeasure------------------------------------------

	@RequestMapping(value = "/unitofmeasure/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUnitOfMeasure(@PathVariable("id") long id) {
		logger.info("Fetching UnitOfMeasure with id {}", id);
		UnitOfMeasure unitofmeasure = unitOfmeasureservice.findById(id);
		if (unitofmeasure == null) {
			logger.error("unitofmeasure with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("unitofmeasure with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UnitOfMeasure>(unitofmeasure, HttpStatus.OK);
	}

	// -------------------Create a UnitOfMeasure-------------------------------------------

	@RequestMapping(value = "/unitofmeasure/", method = RequestMethod.POST)
	public ResponseEntity<?> createUnitOfMeasure(@RequestBody UnitOfMeasure unitofmeasure, UriComponentsBuilder ucBuilder) {
		logger.info("Creating UnitOfMeasure : {}", unitofmeasure);

		if (unitOfmeasureservice.isUnitOfMeasureExist(unitofmeasure)) {
			logger.error("Unable to create. A UnitOfMeasure with name {} already exist", unitofmeasure.getUnitOfMeasureName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A UnitOfMeasure with name " + 
					unitofmeasure.getUnitOfMeasureName() + " already exist."),HttpStatus.CONFLICT);
		}
		unitOfmeasureservice.saveUnitOfMeasure(unitofmeasure);

		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(ucBuilder.path("/api/subcategory/{id}").buildAndExpand(unitofmeasure.getUnitOfMeasureId()).toUri());// need to change
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a UnitOfMeasure ------------------------------------------------

	@RequestMapping(value = "/unitofmeasure/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUnitOfMeasure(@PathVariable("id") long id, @RequestBody UnitOfMeasure unitofmeasure) {
		logger.info("Updating User with id {}", id);

		UnitOfMeasure currentunitofmeasure = unitOfmeasureservice.findById(id);

		if (unitofmeasure == null) {
			logger.error("Unable to update. unitofmeasure with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. unitofmeasure with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentunitofmeasure.setUnitOfMeasureName(unitofmeasure.getUnitOfMeasureName());
		currentunitofmeasure.setUnitOfMeasureNameDesc(unitofmeasure.getUnitOfMeasureNameDesc());
		currentunitofmeasure.setUnitOfMeasureCode(unitofmeasure.getUnitOfMeasureCode());

		unitOfmeasureservice.updateUnitOfMeasure(currentunitofmeasure);
		return new ResponseEntity<UnitOfMeasure>(currentunitofmeasure, HttpStatus.OK);
	}

	// ------------------- Delete a UnitOfMeasure-----------------------------------------

	@RequestMapping(value = "/unitofmeasure/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUnitOfMeasure(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting UnitOfMeasure with id {}", id);

		UnitOfMeasure unitofmeasure = unitOfmeasureservice.findById(id);
		if (unitofmeasure == null) {
			logger.error("Unable to delete. UnitOfMeasure with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. UnitOfMeasure with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		unitOfmeasureservice.deleteUnitOfMeasureById(id);
		return new ResponseEntity<UnitOfMeasure>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All UnitOfMeasure's-----------------------------

	@RequestMapping(value = "/unitofmeasure/", method = RequestMethod.DELETE)
	public ResponseEntity<UnitOfMeasure> deleteAllUnitOfMeasure() {
		logger.info("Deleting All UnitOfMeasure");

		unitOfmeasureservice.deleteAllUnitOfMeasure();
		return new ResponseEntity<UnitOfMeasure>(HttpStatus.NO_CONTENT);
	}

}
