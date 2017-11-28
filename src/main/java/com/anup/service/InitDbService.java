package com.anup.service;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.anup.controller.GenericController;
import com.anup.entity.Facility;
import com.anup.entity.IPAddress;
import com.anup.entity.Product;
import com.anup.entity.Role;
import com.anup.entity.User;
import com.anup.repository.FacilityRepository;
import com.anup.repository.ProductRepository;
import com.anup.repository.RoleRepository;
import com.anup.repository.UserRepository;

@Service
public class InitDbService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private FacilityRepository fr;

	@Autowired
	UserRepository user;

	@Autowired
	RoleRepository role;

	@PostConstruct
	public void init() {

		productRepository.deleteAllInBatch();
		role.deleteAllInBatch();
		user.deleteAllInBatch();

		User u = new User();
		u.setUserId(1);
		u.setUsername("anup");
		u.setEmail("anup@gmail.com");
		u.setEnabled(true);
		u.setFirstName("anup");
		u.setLastName("mridha");
		u.setPassword("$2a$06$K311JYLvpaYv55HAP76yMeViE/TfITE2QZu5ApqP/c5qEtSL99ywq");
		user.save(u);

		Role r = new Role();
		r.setRoleId(1);
		r.setUser(u);
		r.setRole("ROLE_ZEBRA");
		role.save(r);

		Product product = new Product();
		product.setName("Micromax");
		product.setCategory("Mobiles");
		product.setPrice(5000);
		product.setQty(20);
		product.setDescription("Best in Class Micromax E313");
		product.setBarcode("1000000");
		productRepository.save(product);

		Product product1 = new Product();
		product1.setName("Nokia");
		product1.setCategory("Mobiles");
		product1.setPrice(8000);
		product1.setQty(50);
		product1.setDescription("Best in Class Nokia X6");
		product1.setBarcode("1000001");
		productRepository.save(product1);

		Product product2 = new Product();
		product2.setName("MI Note 4");
		product2.setCategory("Mobiles");
		product2.setPrice(13000);
		product2.setQty(10);
		product2.setDescription("Best in Class MiUi Note 4");
		product2.setBarcode("1000002");
		productRepository.save(product2);

	}

//	@Scheduled(initialDelay = 1000, fixedRate = 10000)
//	public void sayHello() {
//		System.out.println("Hello World ");
//	}
}
