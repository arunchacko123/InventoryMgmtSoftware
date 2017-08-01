package com.inventory.mgmt.ims.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="sub_category")
public class Subcategory implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sub_category_id ")
	private Long subCategoryId;
	@NotEmpty
	@Column(name = "sub_category_code")
	private Long subCategoryCode;
	@NotEmpty
	@Column(name="sub_category_name ", nullable=false)
	private String subCategoryName;
	@NotEmpty
	@Column(name="sub_category_description", nullable=false)
	private String subCategoryDesc;
	@NotEmpty
	@Column(name="isactive ", nullable=false)
	private boolean isActive;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	
	public Long getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public Long getSubCategoryCode() {
		return subCategoryCode;
	}
	public void setSubCategoryCode(Long subCategoryCode) {
		this.subCategoryCode = subCategoryCode;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public String getSubCategoryDesc() {
		return subCategoryDesc;
	}
	public void setSubCategoryDesc(String subCategoryDesc) {
		this.subCategoryDesc = subCategoryDesc;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
   
}
