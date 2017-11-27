package com.anup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.anup.entity.ProductTemp;

public interface ProductTempRepository extends JpaRepository<ProductTemp, Integer> {
	
	@Query(value = "SELECT * FROM PRODUCT WHERE ID LIKE %?1%", nativeQuery = true)
	ProductTemp findById(int id);
}
