package com.inventory.mgmt.ims.service.purchaseorder;

import java.util.List;

import com.inventory.mgmt.ims.model.PurchaseOrder;

public interface POService {
	
	
	PurchaseOrder findById(Long id);

	PurchaseOrder findByPONumber(Long poNumber);

	void savePurchaseOrder(PurchaseOrder purchaseOrder);

	void updatePurchaseOrder(PurchaseOrder purchaseOrder);

	void deletePurchaseOrderById(Long id);

	void deleteAllPurchaseOrders();

	List<PurchaseOrder> findAllPurchaseOrders();

	boolean isPurchaseOrderExist(PurchaseOrder purchaseOrder);
}

