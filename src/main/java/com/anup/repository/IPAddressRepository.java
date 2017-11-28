package com.anup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.anup.entity.IPAddress;

public interface IPAddressRepository extends JpaRepository<IPAddress, Integer> {
	
	@Query("SELECT distinct f.ip from IPAddress f Order by 1 asc")
	List<IPAddress> allIP();
	
	@Query("SELECT distinct f.ip from IPAddress f Where f.defaultIp = '1' ")
	String myBaseIp();
	
}
