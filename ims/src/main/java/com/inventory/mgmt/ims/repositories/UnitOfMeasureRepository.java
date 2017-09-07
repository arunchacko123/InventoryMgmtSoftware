package com.inventory.mgmt.ims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.inventory.mgmt.ims.model.UnitOfMeasure;
@Repository
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long>{
	
	@Query(value="select * from unit_of_measure  where unit_of_measure_name=?1",nativeQuery=true)
	UnitOfMeasure findByName(String name);
}
