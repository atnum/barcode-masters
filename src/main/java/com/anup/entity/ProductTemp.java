package com.anup.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Product_Temp")
public class ProductTemp {

	@Id
	@GeneratedValue
	private int id;
	private String category;
	private String name;
	private long price;
	private int qty;
	private String barcode;
	private String description;

	public ProductTemp() {
	}

	@Override
	public String toString() {
		return "ProductTemp [id=" + id + ", category=" + category + ", name=" + name + ", price=" + price + ", qty="
				+ qty + ", description=" + description + "]";
	}

}
