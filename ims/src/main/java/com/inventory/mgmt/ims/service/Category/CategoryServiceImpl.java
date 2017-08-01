package com.inventory.mgmt.ims.service.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.mgmt.ims.model.Category;
import com.inventory.mgmt.ims.repositories.CategoryRepository;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	
	private CategoryRepository categoryRepository;

	public Category findById(Long id) {
		return categoryRepository.findOne(id);
	}
	public Category findByName(String name) {
		return categoryRepository.findByName(name);
	}

	public void saveCategory(Category category) {
		categoryRepository.save(category);
	}

	public void updateCategory(Category category){
		saveCategory(category);
	}

	public void deleteCategoryById(Long id){
		categoryRepository.delete(id);
	}

	public void deleteAllCategorys(){
		categoryRepository.deleteAll();
	}

	public List<Category> findAllCategorys(){
		return categoryRepository.findAll();
	}

	public boolean isCategoryExist(Category category) {
		return findByName(category.getCategoryName()) != null;
	}


}
