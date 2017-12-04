package com.anup.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import com.anup.entity.Barcodes;
import com.anup.repository.BarcodesRepository;
import com.anup.service.BarcodeService;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named
@Getter
@Setter
public class BarcodeController {

	public String type;

	public String value;

	public String barcodeName;

	@Inject
	private BarcodesRepository barcodesRepository;
	
	@Inject
	private BarcodeService service;

	public void save() {

		System.out.println("I am Clicked!!!!");

		Barcodes b = new Barcodes();

		b.setBarcodeType(type);
		b.setBarcodeName(barcodeName);
		b.setBarcodeValue(value);

		if (!service.isBarcodeAvailable(b.getBarcodeName(), b.getBarcodeType())) {
			
			System.out.println("Inside True Part");
			
			barcodesRepository.save(b);
			
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Barcode Details Saved Successfully!", null));
			
		} else {
			
			System.out.println("Inside False Part");
			
			barcodesRepository.updateBarcodeValue(b.getBarcodeValue(), b.getBarcodeName(), b.getBarcodeType());
		
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Barcode Details Updated Successfully!", null));
			
		};
		

	}

	public void delete() {
		Barcodes b = barcodesRepository.getBarcodeValues(barcodeName, type);
		service.delete(b);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Barcode Details Deleted Successfully!", null));
	}

}
