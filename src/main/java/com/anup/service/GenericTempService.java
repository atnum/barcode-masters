package com.anup.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anup.entity.Generic;
import com.anup.entity.GenericTemp;
import com.anup.entity.IPAddress;
import com.anup.repository.GenericRepository;
import com.anup.repository.GenericTempRepository;
import com.anup.repository.IPAddressRepository;

@Service
public class GenericTempService {

	@Autowired
	private GenericTempRepository genericTempRepository;
	
	@Autowired
	private GenericRepository genericRepository;
	
	@Autowired
	private IPAddressRepository addressRepository;
	
	public List<IPAddress> getAllAddress() {
		return addressRepository.allIP();
	}
	
	public String getAllDefIp() {
		return addressRepository.myBaseIp();
	}

	public List<Generic> findAllByGenericTemp() {
		return genericRepository.findAllGenericTempByDesc();

	}

	public void deleteAll() {
		genericTempRepository.deleteAllInBatch();
	}
	
	public String getRandomContainer() {
		return genericRepository.randomContainer();
	}
	
	@Transactional
	public void save(GenericTemp g) {
		genericTempRepository.save(g);
	}
}
