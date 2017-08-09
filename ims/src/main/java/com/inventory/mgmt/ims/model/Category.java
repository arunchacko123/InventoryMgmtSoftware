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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private Long categoryId;
	@Column(name = "category_code")
	private Long categoryCode;
	@NotEmpty
	@Column(name="category_name", nullable=false)
	private String categoryName;
	@NotEmpty
	@Column(name="category_description", nullable=false)
	private String categoryDesc;
	@Column(name="isactive ", nullable=false)
	private boolean active;
	
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
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Set<Subcategory> getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(Set<Subcategory> subCategory) {
		this.subCategory = subCategory;
	}
	
	
	
	
	
}
