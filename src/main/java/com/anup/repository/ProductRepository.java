package com.anup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.anup.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query(value = "SELECT * FROM PRODUCT WHERE ID LIKE %?1% or UPPER(NAME) LIKE UPPER(%?1%) OR UPPER(DESCRIPTION) LIKE UPPER(%?1%)", nativeQuery = true)
	List<Product> findByIdORNameORDescription(String name);

	@Query(value = "SELECT * FROM PRODUCT ORDER BY 1 DESC", nativeQuery = true)
	List<Product> findAllByDesc();
	
	
	@Query(value = "SELECT * FROM PRODUCT_TEMP ORDER BY 1 DESC", nativeQuery = true)
	List<Product> findAllProductTempByDesc();
}
