package com.inventory.mgmt.ims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inventory.mgmt.ims.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	@Query(value="select * from category where category_name=?1",nativeQuery=true)
	Category findByName(String name);
}
