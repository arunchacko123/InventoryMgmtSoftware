package com.inventory.mgmt.ims.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="unit_of_measure")
public class UnitOfMeasure implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "unit_of_measure_id")
	private Long UnitOfMeasureId;
	@Column(name = "unit_of_measure_code")
	private String UnitOfMeasureCode;
	@NotEmpty
	@Column(name="unit_of_measure_name", nullable=false)
	private String UnitOfMeasureName;
	@NotEmpty
	@Column(name="unit_of_measure_description", nullable=false)
	
	private String UnitOfMeasureNameDesc;
	
	public Long getUnitOfMeasureId() {
		return UnitOfMeasureId;
	}
	public void setUnitOfMeasureId(Long unitOfMeasureId) {
		UnitOfMeasureId = unitOfMeasureId;
	}
	public String getUnitOfMeasureCode() {
		return UnitOfMeasureCode;
	}
	public void setUnitOfMeasureCode(String unitOfMeasureCode) {
		UnitOfMeasureCode = unitOfMeasureCode;
	}
	public String getUnitOfMeasureName() {
		return UnitOfMeasureName;
	}
	public void setUnitOfMeasureName(String unitOfMeasureName) {
		UnitOfMeasureName = unitOfMeasureName;
	}
	public String getUnitOfMeasureNameDesc() {
		return UnitOfMeasureNameDesc;
	}
	public void setUnitOfMeasureNameDesc(String unitOfMeasureNameDesc) {
		UnitOfMeasureNameDesc = unitOfMeasureNameDesc;
	}

	
	
}
