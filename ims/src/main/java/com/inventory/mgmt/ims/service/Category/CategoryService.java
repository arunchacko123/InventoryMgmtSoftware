package com.inventory.mgmt.ims.service.Category;

import java.util.List;

import com.inventory.mgmt.ims.model.Category;

public interface CategoryService {
	
	Category findById(Long id);

	Category findByName(String name);

	void saveCategory(Category category);

	void updateCategory(Category category);

	void deleteCategoryById(Long id);

	void deleteAllCategorys();

	List<Category> findAllCategorys();

	boolean isCategoryExist(Category category);
}

