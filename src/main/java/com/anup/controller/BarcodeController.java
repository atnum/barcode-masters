package com.anup.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.anup.entity.Barcodes;
import com.anup.repository.BarcodesRepository;

import lombok.Getter;
import lombok.Setter;

@Scope(value = "view")
// Spring-specific annotation
@Component
@Getter
@Setter
public class BarcodeController {

	public String type;

	public String value;

	public String barcodeName;

	@Autowired
	private BarcodesRepository barcodesRepository;

	public void save() {

		System.out.println("I am Clicked!!!!");

		Barcodes b = new Barcodes();

		b.setBarcodeType(type);
		b.setBarcodeName(barcodeName);
		b.setBarcodeValue(value);

	if (barcodesRepository.getBarcodeName(barcodeName).isEmpty()) {	
		barcodesRepository.save(b);
	} else {
		barcodesRepository.updateBarcodeValue(b.getBarcodeValue(), barcodeName, type);
	}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Barcode Details Saved Successfully!", null));

	}

}
