package com.anup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.anup.entity.Barcodes;

public interface BarcodesRepository extends JpaRepository<Barcodes, Integer> {

	@Query(value = "SELECT BARCODE_VALUE FROM BARCODES WHERE BARCODE_NAME = ?1", nativeQuery = true)
	String getLabelValue(String barcodeName);

	@Transactional
	@Modifying
	@Query(value = "update BARCODES b set b.BARCODE_VALUE = ?1 where b.BARCODE_NAME = ?2 and b.barcode_type = ?3", nativeQuery = true)
	void updateBarcodeValue(String value, String barcodeName, String barcodeType);

	@Query(value = "SELECT BARCODE_NAME FROM BARCODES WHERE BARCODE_NAME = ?1", nativeQuery = true)
	String getBarcodeName(String barcodeName);
}
