package com.anup.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.anup.entity.Product;
import com.anup.entity.ProductTemp;
import com.anup.service.ProductService;
import com.anup.service.ProductTempService;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named
@Getter
@Setter
public class ProductController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ProductService productService;

	@Inject
	private ProductTempService productTempService;

	private List<Product> products;

	private String name;

	private Product product1;

	private String newName;

	private Product selectedProduct;

	private int productId;

	private int noOfQty;

	private static final int MASK = (-1) >>> 1;

	@PostConstruct
	public void init() {
		products = productService.findAll();

	}

	public void changed(ValueChangeEvent e) throws IOException {
		newName = e.getNewValue().toString();
	}

	public List<Product> search(String someName) {

		try {

			name = newName.toLowerCase();

			if (name != null) {
				System.out.println("The value has been changed " + name);

			} else {
				System.out.println("Item Name or Id or Description is null dude!!!");
			}

			products = productService.getNameLike(name);

		} catch (Exception e) {
			e.getMessage();
		}

		return products;

	}

	public void print(Product product) throws IOException {

		product1 = new Product();

		product1 = product;

		FacesContext.getCurrentInstance().getExternalContext().redirect("product_print.jsf");
		FacesContext.getCurrentInstance().responseComplete();

		/*
		 * FacesContext.getCurrentInstance().getApplication(). getNavigationHandler()
		 * .handleNavigation(FacesContext.getCurrentInstance(), null,
		 * "product_print.xhtml");
		 */

	}

	public void printByItem(int id) {

		productTempService.deleteAll();

		if (noOfQty != 0) {

			if (productId != 0) {

				for (int i = 0; i < noOfQty; i++) {

					Product p = productService.findById(productId);

					System.out.println(p);

					ProductTemp pt = new ProductTemp();
					Random rand = new Random();
					pt.setId(rand.nextInt() & MASK);
					pt.setBarcode(p.getBarcode());
					pt.setCategory(p.getCategory());
					pt.setDescription(p.getDescription());
					pt.setName(p.getName());
					pt.setPrice(p.getPrice());
					pt.setQty(p.getQty());

					productTempService.save(pt);

					System.out.println("I am Called " + i);
				}

				products = productTempService.findAllByProductTemp();

			}

			else {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item ID cannot be 0!", null));

			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Number Of Qty cannot be 0!", null));

		}
	}
}