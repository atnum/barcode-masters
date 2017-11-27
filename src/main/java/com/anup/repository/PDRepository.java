package com.anup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.anup.entity.PickDirective;

public interface PDRepository extends JpaRepository<PickDirective, String>{
	
	@Query("SELECT p from PickDirective p WHERE p.LABELS_PRINTED_FLAG = 'N'")
	List<PickDirective> findAllByDesc();
	
//	@Query(value = "SELECT DISTINCT PICK_TO_CONTAINER_ID from PICK_DIRECTIVE WHERE LABELS_PRINTED_FLAG = 'N'", nativeQuery = true)
//	List<String> findAllCont();
//	
	@Modifying
	@Query("update PickDirective u set u.LABELS_PRINTED_FLAG = 'Y' where u.PICK_TO_CONTAINER_ID = ?1")
	void setPickDirectiveByContainer(String containerId);

}
