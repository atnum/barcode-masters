package com.anup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.anup.entity.IPAddress;

public interface IPAddressRepository extends JpaRepository<IPAddress, Integer> {
	
	@Query("SELECT distinct f.ip from IPAddress f Order by 1 asc")
	List<IPAddress> allIP();
	
	@Query(value = "SELECT distinct f.ip from IPAddress f Where f.default_Ip = '1' ", nativeQuery=true)
	String myBaseIp();
	
	@Modifying
	@Query(value = "update IPAddress i set i.USER_FLAG = ?1  where i.ip = ?2", nativeQuery=true)
	void setPrinterByUser(String user, String ip);
	
	@Query(value = "select ip from ipaddress where USER_FLAG = ?1  Order by 1 asc", nativeQuery=true)
	String ipByUser(String user);
	
	@Query(value = "SELECT distinct f.port from IPAddress f where f.user_flag = ?1 and f.ip = ?2 Order by 1 asc", nativeQuery=true)
	int portByUser(String user, String ip);

	
}
