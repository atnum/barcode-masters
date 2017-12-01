package com.anup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anup.repository.BarcodesRepository;

@Service
public class BarcodeService {

	@Autowired
	private BarcodesRepository repository;

	public String getLabelType(String type) {
		return repository.getLabelValue(type);
	}

	@Transactional
	public void updateBarcodeName(String value, String barcodeName, String barcodeType) {
		repository.updateBarcodeValue(value, barcodeName, barcodeType);
	}

	public String getBarcodeName(String name) {
		return repository.getBarcodeName(name);
	}

}
