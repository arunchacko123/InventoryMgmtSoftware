package com.inventory.mgmt.ims.service.purchaseorder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.mgmt.ims.model.PurchaseOrder;
import com.inventory.mgmt.ims.repositories.PORepository;

@Service("poService")
@Transactional
public class POServiceImpl implements POService{
	@Autowired
	
	private PORepository poRepository;

	public PurchaseOrder findById(Long id) {
		return poRepository.findOne(id);
	}
	
	public PurchaseOrder findByPONumber(Long poNumebr) {
		return poRepository.findByPONumber(poNumebr);
		
	}

	public void savePurchaseOrder(PurchaseOrder purchaseOrder) {
		poRepository.save(purchaseOrder);
	}

	public void updatePurchaseOrder(PurchaseOrder purchaseOrder){
		savePurchaseOrder(purchaseOrder);
	}

	public void deletePurchaseOrderById(Long id){
		poRepository.delete(id);
	}

	public void deleteAllPurchaseOrders(){
		poRepository.deleteAll();
	}

	public List<PurchaseOrder> findAllPurchaseOrders(){
		return poRepository.findAll();
	}

	public boolean isPurchaseOrderExist(PurchaseOrder purchaseOrder) {
		return findByPONumber(purchaseOrder.getPoNumber()) != null;
	}

	

}
