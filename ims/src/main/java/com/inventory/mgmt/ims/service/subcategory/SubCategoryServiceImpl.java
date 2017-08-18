package com.inventory.mgmt.ims.service.subcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.mgmt.ims.model.Subcategory;
import com.inventory.mgmt.ims.repositories.SubcategoryRepository;

@Service("subcategoryService")
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService{
	@Autowired
	
	private SubcategoryRepository subcategoryRepository;

	public Subcategory findById(Long id) {
		return subcategoryRepository.findOne(id);
	}
	public Subcategory findByName(String name) {
		return subcategoryRepository.findByName(name);
	}

	public void saveSubcategory(Subcategory subcategory) {
		subcategoryRepository.save(subcategory);
	}

	public void updateSubcategory(Subcategory subcategory){
		saveSubcategory(subcategory);
	}

	public void deleteSubcategoryById(Long id){
		subcategoryRepository.delete(id);
	}

	public void deleteAllSubcategorys(){
		subcategoryRepository.deleteAll();
	}

	public List<Subcategory> findAllSubcategorys(){
		return subcategoryRepository.findAll();
	}

	public boolean isSubcategoryExist(Subcategory category) {
		return findByName(category.getSubCategoryName()) != null;
	}


}
