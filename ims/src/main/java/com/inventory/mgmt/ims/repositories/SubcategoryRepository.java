package com.inventory.mgmt.ims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.inventory.mgmt.ims.model.Subcategory;
@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long>{
	
	@Query(value="select * from subcategory where subcategory_name=?1",nativeQuery=true)
	Subcategory findByName(String name);
}
