package com.anup.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anup.entity.Barcodes;
import com.anup.entity.IPAddress;
import com.anup.entity.Product;
import com.anup.entity.Role;
import com.anup.entity.User;
import com.anup.repository.BarcodesRepository;
import com.anup.repository.IPAddressRepository;
import com.anup.repository.ProductRepository;
import com.anup.repository.RoleRepository;
import com.anup.repository.UserRepository;

@Service
public class InitDbService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	UserRepository user;

	@Autowired
	RoleRepository role;

	@Autowired
	GenericTempService ts;

	@Autowired
	IPAddressRepository ipr;

	@Autowired
	BarcodesRepository br;

	@PostConstruct
	public void init() {

		productRepository.deleteAllInBatch();
		role.deleteAllInBatch();
		user.deleteAllInBatch();
		ipr.deleteAllInBatch();
		br.deleteAllInBatch();

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

		IPAddress ip = new IPAddress();
		ip.setIp("192.168.120.23");
		ip.setDefault_ip(1);
		ip.setPort(9100);
		ip.setPrinterName("Home");
		ip.setBarcodeType("code128");
		ip.setUSER_FLAG("anup");

		ts.savePrinter(ip);

		Barcodes b = new Barcodes();
		b.setBarcodeName("Picking");
		b.setBarcodeType("code128");
		b.setBarcodeValue("^XA~TA000~JSN^LT0^MNW^MTT^PON^PMN^LH0,0^JMA^PR6,6~SD15^JUS^LRN^CI0^XZ\r\n" + "^XA\r\n"
				+ "^MMT\r\n" + "^PW609\r\n" + "^LL0406\r\n" + "^LS0\r\n" + "^FO160,0^GFA,02304,02304,00036,:Z64:\r\n"
				+ "eJztks2O0zAUha+dMDGSoV4gESHTWvACZjVejRKVB/GOrUcaiUGKWvdHHXaw5HFSKmA7Ei8QmBdIxaaLDOCfLCq1G1j3SM61nOPPJzcGOOmkk/5f6cEkCrnBwsxCDjIu5kB8Eb1n4Ebjxp8fNVS9Z1IB99X4x6iGxKG8R2nrNoedYkX2OGcAuJ9Lt4lHdJnxvTAZeE5gSgVxs1vCZM+Do8dzjItC4+oY0UOO9hxhL8hEiGYIiSWKKCMare2F4wwWj5Mm5Dmf3XB1uW3HbVnz6vrd/a/t9q7+VmfwajNKjc+jwGIi5Asxs7YlhDzjQgm9XjiOWLx8n3vOFK0QLdiVWVuoKOdP6DXXukxcnnJT3rKQB+a+P0pYa4eUkJxKIvQYO06ysB92IQ9awgiYvFwjyDjP81RyY8obx3m+QbexP7CCh1ZK8dl9K/Ec4TjlMuSBT7E/6Ct6VDNjKpQmlOYsNUwXxQ4yXG7Qx7TPg0kjhCCAseMwIkBo9wbjmUtKYp6bJTdMGo6yxOdhkpmiyCF78OU74iz0By12VCkpB/NkSUnu2gO60UM4o7u7+aDxZ3VZ006HVVed16idjiZP67ez+7Z9U486s+mydbd/Me3P/ir6BMjWcCgEV71Hhp9eHPHg/hZHTnrUk8HrUFnobArHzkqsDVUFDk6OWAB+x1Ih/yWoO+o56aR/1l//soYs:0DC6\r\n"
				+ "^BY2,3,101^FT23,253^B3N,N,,Y,N\r\n" + "^FD$$CONT.^FS\r\n"
				+ "^FT25,113^A0N,23,24^FH\\^FDWave number: $$Wave^FS\r\n"
				+ "^FT324,112^A0N,23,24^FH\\^FDDistro number: $$Distro^FS\r\n"
				+ "^FT20,334^A0N,23,24^FH\\^FDLocation Id: $$Location^FS\r\n"
				+ "^FT404,333^A0N,23,24^FH\\^FDPick Type: $$PT^FS\r\n"
				+ "^FT19,385^A0N,23,24^FH\\^FDDest Id: $$DEST^FS\r\n"
				+ "^FT404,386^A0N,23,24^FH\\^FDZone: $$ZONE^FS\r\n" + "^PQ1,0,1,Y^XZ");
		br.save(b);
		
		
		Barcodes b1 = new Barcodes();
		b1.setBarcodeName("Generic");
		b1.setBarcodeType("code128");
		b1.setBarcodeValue("^XA~TA000~JSN^LT0^MNW^MTT^PON^PMN^LH0,0^JMA^PR6,6~SD15^JUS^LRN^CI0^XZ\r\n" + 
				"^XA\r\n" + 
				"^MMT\r\n" + 
				"^PW609\r\n" + 
				"^LL0406\r\n" + 
				"^LS0\r\n" + 
				"^FO192,32^GFA,02688,02688,00028,:Z64:\r\n" + 
				"eJztkLFOwzAQQC81ijNUzsDiIWr6CZVYbkoHJL4jLJ0vYiBDpFhCKkyw8gV8AxsplejIL3jratSlQ0VxqqRNUT8AJD/Jsn32O98ZwOFwOP433A75O9gEEjuovbdpFhGAZ9fCTqa9nnS8fHcGoyZEg05em8zv5Ez9zhkeeV51duzxjqeA6VTH7BGZsV4iEqb3HvSzlbn5/C6iHMhb+GWfGs9TEOAknd29ICIgm4aLBzx4UhbZbP4RjaT1OL9/lo3HFIzXmG7nSg5tnT0Rsrf13isJKKt4FVFdYMm5aev0KoFA14opTOv+poK9HvqLsfaYiigAOuc+w31/WmAwTPWlkigBL4ToYdD+p0kGI8q+5lVU2Idv4yvf7tsvnISaqLTvjTXky+VTP9R5cxa/89mmSEJTxsrbrhahsXs4xRCCk3GHw/FX+AFeo17t:2521\r\n" + 
				"^BY3,3,136^FT87,284^BCN,,Y,N\r\n" + 
				"^FD>:$$CONT^FS\r\n" + 
				"^PQ1,0,1,Y^XZ");
		br.save(b1);
		
		Barcodes b2 = new Barcodes();
		b2.setBarcodeName("Generic");
		b2.setBarcodeType("code128");
		b2.setBarcodeValue("^XA~TA000~JSN^LT0^MNW^MTT^PON^PMN^LH0,0^JMA^PR6,6~SD15^JUS^LRN^CI0^XZ\r\n" + 
				"^XA\r\n" + 
				"^MMT\r\n" + 
				"^PW609\r\n" + 
				"^LL0406\r\n" + 
				"^LS0\r\n" + 
				"^FO224,0^GFA,01280,01280,00020,:Z64:\r\n" + 
				"eJztkTFOAzEQRf94ImxEtISKRXLIHsEgii0QbBUuQe6RIoqGKEoJV3KqXIJiJS5gOirCJhoj0VEilF+M5Kev57EMHPLvQoJBN38wBhJQ6GmA+S0nCwTgUpnDqkBrgAowyjzZc4q071llNZsTEd6z3HtkUNTekbKCBa0YfIT2LvfGFJEaYn+VHrJvheSCsHHX9dNCmcUM04Z6/mZI2We6Tctu1rXbao+2OC19Q/Qaht/7sbArdr5wLPleWnJpd75wgXHWLYxjMa6sIOrr0YZ9997+JJyt1Vfgbekh/PJcV5+6y2j9nuaIdtO/j7NR/NWnHPIX8wUgajEC:FDC9\r\n" + 
				"^BY3,3,102^FT74,249^BCN,,Y,N\r\n" + 
				"^FD>:$$CONT^FS\r\n" + 
				"^FT16,98^A0N,23,24^FH\\^FDASN number: $$ASN^FS\r\n" + 
				"^FT376,98^A0N,23,24^FH\\^FDAPPT number: $$APPT^FS\r\n" + 
				"^FT16,347^A0N,23,24^FH\\^FDPO number: $$PO^FS\r\n" + 
				"^PQ1,0,1,Y^XZ");
		br.save(b2);

	}

	// @Scheduled(initialDelay = 1000, fixedRate = 10000)
	// public void sayHello() {
	// System.out.println("Hello World ");
	// }
}
