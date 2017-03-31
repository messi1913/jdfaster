package com.jdfaster.jdfsample.pm.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_order")
public class ProductOrder {
	@Id
	@Column (name = "id", nullable = false, length = 100)
	private String id;
	@Column (name = "quantity", length = 200)
	private String quantity;
	@Column (name = "name", length = 300)
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ProductOrder [id=" + id + ", quantity=" + quantity + ", name=" + name + "]";
	}
	
	
	
	
}
