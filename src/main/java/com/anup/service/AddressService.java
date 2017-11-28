package com.anup.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.anup.entity.IPAddress;
import com.anup.repository.IPAddressRepository;

@Service
public class AddressService {

	private IPAddressRepository addressRepository;

	public void save(IPAddress address) {
		addressRepository.save(address);
	}

	public void deleteIP(int id) {
		addressRepository.delete(id);
	}
	
	public List<String> getAllAddress() {
		return addressRepository.allIP();
	}

}
