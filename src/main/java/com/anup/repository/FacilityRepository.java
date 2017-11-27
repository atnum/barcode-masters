package com.anup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.anup.entity.Facility;
import com.anup.entity.PickDirective;

public interface FacilityRepository extends JpaRepository<Facility, Integer> {

	@Query("SELECT distinct f.facilityId from Facility f Order by 1 desc")
	List<Facility> allFacilityId();
}
