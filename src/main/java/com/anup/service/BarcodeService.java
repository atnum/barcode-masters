package com.anup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anup.entity.Barcodes;
import com.anup.repository.BarcodesRepository;

@Service
public class BarcodeService {

	@Autowired
	private BarcodesRepository repository;

	public String getLabelType(String name, String type) {
		return repository.getLabelValue(name, type);
	}

	@Transactional
	public void updateBarcodeName(String value, String barcodeName, String barcodeType) {
		repository.updateBarcodeValue(value, barcodeName, barcodeType);
	}

	public void delete(Barcodes b) {
		repository.delete(b);
	}

	public boolean isBarcodeAvailable(String barcodeName, String barcodeType) {

		try {

			String a = repository.getBarcode(barcodeName, barcodeType);

			if (a.equals("1")) {

				System.out.println(true);

				return true;

			}

			else {

				return false;
			}
			
		} catch (NullPointerException e) {
			return false;
		}
	}
}