package com.anup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anup.entity.Product;
import com.anup.entity.ProductTemp;
import com.anup.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAllByDesc();

	}

	public List<Product> getNameLike(String containerId) {
		return productRepository.findByIdORNameORDescription(containerId);
	}
	
	public Product findById(int id) {
		return productRepository.findOne(id);
	}
}
