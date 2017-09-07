package com.inventory.mgmt.ims.service.unitofmeasure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.mgmt.ims.model.UnitOfMeasure;
import com.inventory.mgmt.ims.repositories.UnitOfMeasureRepository;

@Service("unitOfmeasureservice")
@Transactional
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{
	@Autowired
	
	private UnitOfMeasureRepository unitofmeasureRepository;

	public UnitOfMeasure findById(Long id) {
		return unitofmeasureRepository.findOne(id);
	}
	public UnitOfMeasure findByName(String name) {
		return unitofmeasureRepository.findByName(name);
	}

	public void saveUnitOfMeasure(UnitOfMeasure unitofmeasure) {
		unitofmeasureRepository.save(unitofmeasure);
	}

	public void updateUnitOfMeasure(UnitOfMeasure unitofmeasure){
		saveUnitOfMeasure(unitofmeasure);
	}

	public void deleteUnitOfMeasureById(Long id){
		unitofmeasureRepository.delete(id);
	}

	public void deleteAllUnitOfMeasure(){
		unitofmeasureRepository.deleteAll();
	}

	public List<UnitOfMeasure> findAllUnitOfMeasure(){
		return unitofmeasureRepository.findAll();
	}

	public boolean isUnitOfMeasureExist(UnitOfMeasure unitofmeasure) {
		return findByName(unitofmeasure.getUnitOfMeasureName()) != null;
	}


}
