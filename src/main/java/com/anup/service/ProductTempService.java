package com.anup.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anup.entity.Product;
import com.anup.entity.ProductTemp;
import com.anup.repository.ProductRepository;
import com.anup.repository.ProductTempRepository;

@Service
@Transactional
public class ProductTempService {

	@Autowired
	private ProductTempRepository productTempRepository;
	
	@Autowired
	private EntityManager em;

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAllByProductTemp() {
		return productRepository.findAllProductTempByDesc();

	}

	public void deleteAll() {
		productTempRepository.deleteAllInBatch();
	}

//	@Transactional
//	public void save(Integer id) {
//		
//		Product p = new Product();
//		
//		p = em.find(Product.class, id);
//		
//		System.out.println(p);
//		
//		em.persist(p);
//	}

	public void save(ProductTemp p) {
		productTempRepository.save(p);
	}

	public ProductTemp findById(int id) {
		return productTempRepository.findById(id);
	}
}
