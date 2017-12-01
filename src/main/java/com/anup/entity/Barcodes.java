package com.anup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Barcodes {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "Barcode_Type")
	@NotNull
	@NotBlank
	String barcodeType;
	
	@Column(name = "Barcode_Name")
	@NotNull
	@NotBlank
	private String barcodeName;

	@NotNull
	@NotBlank
	@Column(name = "barcode_value")
	private String barcodeValue;

}
