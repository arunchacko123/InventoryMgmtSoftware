package com.inventory.mgmt.ims.service.unitofmeasure;

import java.util.List;

import com.inventory.mgmt.ims.model.Subcategory;
import com.inventory.mgmt.ims.model.UnitOfMeasure;

public interface UnitOfMeasureService {
	
	
	UnitOfMeasure findById(Long id);

	UnitOfMeasure findByName(String name);

	void saveUnitOfMeasure(UnitOfMeasure unitofmeasure);

	void updateUnitOfMeasure(UnitOfMeasure unitofmeasure);

	void deleteUnitOfMeasureById(Long id);

	void deleteAllUnitOfMeasure();

	List<UnitOfMeasure> findAllUnitOfMeasure();

	boolean isUnitOfMeasureExist(UnitOfMeasure unitofmeasure);
}

