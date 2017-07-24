package com.inventory.mgmt.ims.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="category")
public class Category implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private Long categoryId;
	@NotEmpty
	@Column(name = "category_code")
	private Long categoryCode;
	@NotEmpty
	@Column(name="category_name", nullable=false)
	private String categoryName;
	@NotEmpty
	@Column(name="category_description", nullable=false)
	private String categoryDesc;
	@NotEmpty
	@Column(name="isactive ", nullable=false)
	private boolean isActive;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<Subcategory> subCategory;
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(Long categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Set<Subcategory> getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(Set<Subcategory> subCategory) {
		this.subCategory = subCategory;
	}
	
	
	
	
	
}
