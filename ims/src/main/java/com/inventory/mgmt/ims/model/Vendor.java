package com.inventory.mgmt.ims.model;

import java.util.Date;
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
@Table(name="vendor")
public class Vendor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vendor_id")
	private Long vendorId;
	@NotEmpty
	@Column(name = "vendor_code")
	private String vendorCode;
	@NotEmpty
	@Column(name="vendor_name", nullable=false)
	private String vendorName;
	@NotEmpty
	@Column(name="vendor_address", nullable=false)
	private String vendorAddress;
	@Column(name="isactive", nullable=false)
	private boolean active;
	@NotEmpty
	@Column(name="vendor_created_date", nullable=false)
	private Date vendorCreatedDate;
	@Column(name="vendor_tax_number")
	private String vendorTaxNumber;
	
	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
	private Set<PurchaseOrder> purchaseOrder;
	
	
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getVendorCreatedDate() {
		return vendorCreatedDate;
	}
	public void setVendorCreatedDate(Date vendorCreatedDate) {
		this.vendorCreatedDate = vendorCreatedDate;
	}
	public String getVendorTaxNumber() {
		return vendorTaxNumber;
	}
	public void setVendorTaxNumber(String vendorTaxNumber) {
		this.vendorTaxNumber = vendorTaxNumber;
	}
	public Set<PurchaseOrder> getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setPurchaseOrder(Set<PurchaseOrder> purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	
	
}
