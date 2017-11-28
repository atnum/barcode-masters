package com.anup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anup.entity.Facility;
import com.anup.entity.Generic;
import com.anup.repository.FacilityRepository;
import com.anup.repository.GenericRepository;

@Service
public class GenericService {

	@Autowired
	private GenericRepository genericRepository;

	@Autowired
	private FacilityRepository facilityRepository;

	public List<Generic> findAll() {
		return genericRepository.findAll();

	}

	public List<Generic> getContainerLike(String containerId) {
		return genericRepository.findByContainerIdContainingIgnoreCase(containerId);
	}

	public void save(Generic generic) {
		genericRepository.save(generic);
	}

	public List<Facility> findAllFacility() {
		return facilityRepository.allFacilityId();
	}

	public String isContainerExist(String containerId) {
		return genericRepository.isContainerIdExist(containerId);
	}

	public String getRandomContainer() {
		return genericRepository.randomContainer();
	}

	public List<Generic> findAllByDesc() {
		return genericRepository.findAllByDesc();
	}
}
