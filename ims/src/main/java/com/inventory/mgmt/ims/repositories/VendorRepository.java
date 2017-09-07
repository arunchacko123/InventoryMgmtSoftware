package com.inventory.mgmt.ims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inventory.mgmt.ims.model.Vendor;


@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>{
	
	@Query(value="select * from vendor where vendor_name=?1",nativeQuery=true)
	Vendor findByName(String name);
}

