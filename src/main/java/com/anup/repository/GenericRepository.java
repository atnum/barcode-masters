package com.anup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.anup.entity.Generic;

public interface GenericRepository extends JpaRepository<Generic, Long> {

	//List<Generic> findByContainerIdContainingIgnoreCase(String containerId);
	
	@Query(value = "SELECT * from XX_GENERIC_LABELS c where UPPER(c.container_id) LIKE %?1% OR LOWER(c.container_id) LIKE %?1% ORDER BY 1 DESC", nativeQuery = true)
	List<Generic> findByContainerIdContainingIgnoreCase(String containerId);

	@Query(value = "SELECT c.container_id from XX_GENERIC_LABELS c where c.container_id = ?1", nativeQuery = true)
	String isContainerIdExist(String containerId);

    @Query(value = "select '10000000' || RETURNS_CONT_SEQ.nextval from dual", nativeQuery = true) 
	//@Query(value = "select 1", nativeQuery = true) 
	String randomContainer();

	@Query(value = "SELECT * from XX_GENERIC_LABELS ORDER BY 1 DESC", nativeQuery = true)
	List<Generic> findAllByDesc();

	@Query(value = "SELECT * from XX_GENERIC_LABELS_TEMP ORDER BY 1 DESC", nativeQuery = true)
	List<Generic> findAllGenericTempByDesc();
}
