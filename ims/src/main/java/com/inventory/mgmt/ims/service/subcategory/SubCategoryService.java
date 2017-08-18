package com.inventory.mgmt.ims.service.subcategory;

import java.util.List;

import com.inventory.mgmt.ims.model.Subcategory;

public interface SubCategoryService {
	
	
	Subcategory findById(Long id);

	Subcategory findByName(String name);

	void saveSubcategory(Subcategory subcategory);

	void updateSubcategory(Subcategory category);

	void deleteSubcategoryById(Long id);

	void deleteAllSubcategorys();

	List<Subcategory> findAllSubcategorys();

	boolean isSubcategoryExist(Subcategory category);
}

