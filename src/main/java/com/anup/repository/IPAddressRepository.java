package com.anup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.anup.entity.IPAddress;

public interface IPAddressRepository extends JpaRepository<IPAddress, Integer> {

	@Query(value = "SELECT distinct i.ip from IPAddress i Order by i.ip asc", nativeQuery = true)
	List<String> allIP();

	@Query(value = "SELECT distinct f.ip from IPAddress f Where f.default_Ip = '1' ", nativeQuery = true)
	String myBaseIp();

	@Modifying
	@Query(value = "update IPAddress i set i.USER_FLAG = ?1, i.barcode_type = ?2 where i.ip = ?3", nativeQuery = true)
	void setPrinterByUser(String user, String barType, String ip);

	@Query(value = "select i.ip from ipaddress i where i.USER_FLAG = ?1  Order by 1 asc", nativeQuery = true)
	String ipByUser(String user);

	@Query(value = "SELECT distinct f.port from IPAddress f where f.user_flag = ?1 and f.ip = ?2 Order by 1 asc", nativeQuery = true)
	int portByUser(String user, String ip);
	
	@Modifying
	@Query(value = "update IPAddress i set i.USER_FLAG = null where i.USER_FLAG = ?1", nativeQuery = true)
	void setPrinterBeforeUse(String user);
	
	@Query(value = "SELECT distinct f.id from IPAddress f Where f.ip = ?1", nativeQuery = true)
	int myId(String ip);

}
