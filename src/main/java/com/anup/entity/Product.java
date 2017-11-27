package com.anup.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue
	//@Column(name="PRODUCT_ID")
	private int id;
	private String category;
	private String name;
	private long price;
	private int qty;
	private String barcode;	
	private String description;

	public Product() {
	}

	@Override
	public String toString() {
		return getName();
	}

}
