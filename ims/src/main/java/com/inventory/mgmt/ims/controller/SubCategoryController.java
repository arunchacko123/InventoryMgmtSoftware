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

import com.inventory.mgmt.ims.model.Subcategory;
import com.inventory.mgmt.ims.service.subcategory.SubCategoryService;
import com.inventory.mgmt.ims.util.CustomErrorType;



@RestController
@RequestMapping("/subcategorymgmt")

public class SubCategoryController {
	
	public static final Logger logger = LoggerFactory.getLogger(SubCategoryController.class);

	@Autowired
	SubCategoryService subcategoryService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All SubCategory---------------------------------------------

	@RequestMapping(value = "/subcategory/", method = RequestMethod.GET)
	public ResponseEntity<List<Subcategory>> listAllUsers() {
		List<Subcategory> subCategory = subcategoryService.findAllSubcategorys();
		if (subCategory.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Subcategory>>(subCategory, HttpStatus.OK);
	}

	// -------------------Retrieve Single SubCategory------------------------------------------

	@RequestMapping(value = "/subcategory/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCategory(@PathVariable("id") long id) {
		logger.info("Fetching Subcategory with id {}", id);
		Subcategory category = subcategoryService.findById(id);
		if (category == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("subCategory with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Subcategory>(category, HttpStatus.OK);
	}

	// -------------------Create a SubCategory-------------------------------------------

	@RequestMapping(value = "/subcategory/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Subcategory subcategory, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Subcategory : {}", subcategory);

		if (subcategoryService.isSubcategoryExist(subcategory)) {
			logger.error("Unable to create. A Subcategory with name {} already exist", subcategory.getSubCategoryName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Subcategory with name " + 
					subcategory.getSubCategoryName() + " already exist."),HttpStatus.CONFLICT);
		}
		subcategoryService.saveSubcategory(subcategory);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/subcategory/{id}").buildAndExpand(subcategory.getSubCategoryId()).toUri());// need to change
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a SubCategory ------------------------------------------------

	@RequestMapping(value = "/subcategory/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCategory(@PathVariable("id") long id, @RequestBody Subcategory subcategory) {
		logger.info("Updating User with id {}", id);

		Subcategory currentSubcategory = subcategoryService.findById(id);

		if (currentSubcategory == null) {
			logger.error("Unable to update. subcategory with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. subcategory with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentSubcategory.setSubCategoryName(subcategory.getSubCategoryName());
		currentSubcategory.setSubCategoryDesc(subcategory.getSubCategoryDesc());
		currentSubcategory.setSubCategoryCode(subcategory.getSubCategoryCode());

		subcategoryService.updateSubcategory(currentSubcategory);
		return new ResponseEntity<Subcategory>(currentSubcategory, HttpStatus.OK);
	}

	// ------------------- Delete a SubCategory-----------------------------------------

	@RequestMapping(value = "/subcategory/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCategory(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Subcategory with id {}", id);

		Subcategory subcategory = subcategoryService.findById(id);
		if (subcategory == null) {
			logger.error("Unable to delete. Subcategory with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Subcategory with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		subcategoryService.deleteSubcategoryById(id);
		return new ResponseEntity<Subcategory>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All SubCategory's-----------------------------

	@RequestMapping(value = "/subcategory/", method = RequestMethod.DELETE)
	public ResponseEntity<Subcategory> deleteAllCategorys() {
		logger.info("Deleting All Subcategorys");

		subcategoryService.deleteAllSubcategorys();
		return new ResponseEntity<Subcategory>(HttpStatus.NO_CONTENT);
	}

}
