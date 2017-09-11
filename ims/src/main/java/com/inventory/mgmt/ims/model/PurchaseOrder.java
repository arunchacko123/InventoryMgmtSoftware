package com.inventory.mgmt.ims.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name="purchaseorder")
public class PurchaseOrder {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "po_id")
	private Long poId;
	
	@NotEmpty
	@Column(name = "po_number", nullable=false)
	private Long poNumber;
	
	
	@NotEmpty
	@Column(name="description", nullable=false)
	private String description;
	
	@NotEmpty
	@Column(name="total_amount", nullable=false)
	private Long totalAmount;
	
	@NotEmpty
	@Column(name="po_created_date", nullable=false)
	private Date poCreatedDate;
	
	@Column(name = "vendor")
	private Long vendor;

	public Long getPoId() {
		return poId;
	}

	public void setPoId(Long poId) {
		this.poId = poId;
	}

	public Long getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(Long poNumber) {
		this.poNumber = poNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getPoCreatedDate() {
		return poCreatedDate;
	}

	public void setPoCreatedDate(Date poCreatedDate) {
		this.poCreatedDate = poCreatedDate;
	}

	public Long getVendor() {
		return vendor;
	}

	public void setVendor(Long vendor) {
		this.vendor = vendor;
	}
	
	
	

}
