package com.inventory.mgmt.ims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inventory.mgmt.ims.model.PurchaseOrder;
@Repository
public interface PORepository extends JpaRepository<PurchaseOrder, Long>{
	
	@Query(value="select * from purchaseorder where po_number=?1",nativeQuery=true)
	PurchaseOrder findByPONumber(Long poNumber);
}
