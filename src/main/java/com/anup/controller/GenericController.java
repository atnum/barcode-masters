package com.anup.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.anup.entity.ASN;
import com.anup.entity.Facility;
import com.anup.entity.Generic;
import com.anup.entity.GenericTemp;
import com.anup.entity.IPAddress;
import com.anup.entity.PickDirective;
import com.anup.repository.AsnRepository;
import com.anup.repository.PDRepository;
import com.anup.service.BarcodeService;
import com.anup.service.GenericService;
import com.anup.service.GenericTempService;

import fr.w3blog.zpl.model.ZebraUtils;
import lombok.Getter;
import lombok.Setter;

@Scope(value = "session")
@Component
@Getter
@Setter
public class GenericController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @ManagedProperty("#{genericService}")
	@Autowired
	private GenericService genericService;

	// @ManagedProperty("#{genericTempService}")
	@Autowired
	private GenericTempService genericTempService;

	@Autowired
	private BarcodeService service;

	@Autowired
	private PDRepository repository;

	private List<PickDirective> myList;

	private String ipAddress;

	private List<Generic> generics;

	private List<Generic> results;

	private String containerId;

	private Generic generic1;

	private List<Facility> facility;

	public Generic generic = new Generic();

	public String username; // getting logged user

	private String barcodeType;

	private int containerQty;

	private String randomContainerId;

	private String newContainerId;

	List<String> distinctAsn;

	@SuppressWarnings("unused")
	private static final int MASK = (-1) >>> 1;

	private List<String> addresses;

	private IPAddress address;

	public String ip;

	public int port;

	@Autowired
	private AsnRepository asnRepository;

	private String asn;

	private List<ASN> asnList = null;

	private List<String> containerList;

	private String newASN;

	String myASN = "";

	private String uname;

	public GenericController() {
		barcodeType = "code128";
	}

	@PostConstruct
	public void init() {
		// generics = genericService.findAll();
		generics = genericService.findAllByDesc();

		facility = genericService.findAllFacility();

		addresses = genericTempService.getAllAddress();

		System.out.println("The IP Address inside init() is: " + ip);

		// asnList = asnRepository.findAllAsn(myASN); //(Slower Login Bug)

		// myList = repository.findAllByDesc();

		// System.out.println(myList);

		// GETTING THE CURRENT USERNAME
		username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

		uname = username;

		// Converting into CamelCase using custom code
		username = toCamelCase(username);

	}

	public void setPrinter(String ip) {

		this.ip = ip;

		genericTempService.updateUserForPrinter(uname.toLowerCase());

		genericTempService.setPrinterByUser(uname.toLowerCase(), barcodeType, ip);

		System.out.println(ip + "---" + uname);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"IP Address " + ip + " has been set for the Username " + uname + " with the printer Successfully!",
				null));

		addresses = genericTempService.getAllAddress();

	}

	public String deletePrinter(String ip) {

		this.ip = ip;

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"IP Address " + ip + " has been deleted Successfully!", null));

		genericTempService.deletePrinter(ip);

		addresses = genericTempService.getAllAddress();
		// added for redirect functionality
		return "settings?faces-redirect=true";

	}

	public void changed(ValueChangeEvent e) throws IOException {
		newContainerId = e.getNewValue().toString();
	}

	public void changedASN(ValueChangeEvent e) throws IOException {
		newASN = e.getNewValue().toString();
	}

	public List<Generic> search(String newContainerId) {

		try {

			String myContainer = newContainerId.toLowerCase();

			if (myContainer != null) {
				System.out.println("The value has been changed " + myContainer);

			} else {
				System.out.println("Container ID is null dude!!!");
			}

			generics = genericService.getContainerLike(myContainer);
			/*
			 * }
			 * 
			 * generics = genericService.getContainerLike(containerId);
			 */

		} catch (Exception e) {
			e.getMessage();
		}

		return generics;
	}

	public void print(Generic generic) throws IOException {

		generic1 = new Generic();

		generic1 = generic;

		FacesContext.getCurrentInstance().getExternalContext().redirect("generic_print.jsf");
		FacesContext.getCurrentInstance().responseComplete();

		// nullify container id
		containerId = null;

		/*
		 * FacesContext.getCurrentInstance().getApplication(). getNavigationHandler()
		 * .handleNavigation(FacesContext.getCurrentInstance(), null,
		 * "product_print.xhtml");
		 */

	}

	public void save() {
		try {
			ip = genericTempService.findIPByUser(uname.toLowerCase());

			port = genericTempService.findPortByUser(uname.toLowerCase(), ip);

			System.out.println("The IP for the Username " + uname + " is " + ip);

			System.out.println("The Port for the Username " + uname + " is " + port);
		} catch (Exception e) {
			System.out.println("Its null " + uname.toLowerCase());
		}
		genericTempService.deleteAll();

		System.out.println("Checking Container ID is :" + genericService.isContainerExist(generic.getContainerId()));
		// ---------------------Main
		// Logic----------------------------------------
		if (genericService.isContainerExist(generic.getContainerId()) == null) {

			String s1 = service.getLabelType("Generic", uname.toLowerCase());

			s1 = s1.replace("$$CONT", generic.getContainerId());

			System.out.println(s1);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Printing Started for Container (" + generic.getContainerId() + " !)", null));

			try {
				ZebraUtils.printZpl(s1, ip, port);
			} catch (Exception e) {
				e.printStackTrace();
			}

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Printing Finished for Container (" + generic.getContainerId() + " !)", null));

			genericService.save(generic);

			String generic2 = generic.getContainerId();

			generic = new Generic();

			// generics = genericService.findAll();

			generics = genericService.findAllByDesc();

			// generics = genericTempService.findAllByGenericTemp();

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Generic Container with Id (" + generic2 + ") Created Successfully!", null));

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					generic.getContainerId() + "" + " Already Exist!", null));
			generic.setContainerId(null);
		}

	}

	// --------------To Convert the Username into CamelCase
	static String toCamelCase(String s) {
		String[] parts = s.split(" ");
		String camelCaseString = "";
		for (String part : parts) {
			if (part != null && part.trim().length() > 0)
				camelCaseString = camelCaseString + toProperCase(part);
			else
				camelCaseString = camelCaseString + part + " ";
		}
		return camelCaseString;
	}

	static String toProperCase(String s) {
		String temp = s.trim();
		String spaces = "";
		if (temp.length() != s.length()) {
			int startCharIndex = s.charAt(temp.indexOf(0));
			spaces = s.substring(0, startCharIndex);
		}
		temp = temp.substring(0, 1).toUpperCase() + spaces + temp.substring(1).toLowerCase() + " ";
		return temp;

	}
	// --------------End of To Convert the Username into CamelCase

	// Batch Save of Containers
	public void saveBatch() {

		ip = genericTempService.findIPByUser(uname.toLowerCase());

		try {
			ip = genericTempService.findIPByUser(uname.toLowerCase());

			port = genericTempService.findPortByUser(uname.toLowerCase(), ip);

			System.out.println("The IP for the Username " + uname + " is " + ip);

			System.out.println("The Port for the Username " + uname + " is " + port);
		} catch (Exception e) {
			System.out.println("Its null " + uname.toLowerCase());
		}
		genericTempService.deleteAll();

		if (containerQty != 0)

		{

			for (int i = 0; i < containerQty; i++) {

				Generic g = new Generic();
				g.setContainerId(genericTempService.getRandomContainer());
				g.setFacilityId(generic.getFacilityId());

				GenericTemp gt = new GenericTemp();
				gt.setContainerId(g.getContainerId());
				gt.setFacilityId(g.getFacilityId());

				System.out.println("The Container id are : " + gt.getContainerId());

				String s1 = service.getLabelType("Generic", uname.toLowerCase());

				s1 = s1.replace("$$CONT", gt.getContainerId());

				System.out.println(s1);

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Printing Started for Container (" + gt.getContainerId() + " !)", null));

				try {
					ZebraUtils.printZpl(s1, ip, port);
				} catch (Exception e) {
					e.printStackTrace();
				}
				genericTempService.save(gt);

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Printing Finished for Container (" + gt.getContainerId() + " !)", null));

			}

			generic = new Generic();

			generics = genericTempService.findAllByGenericTemp();

			System.out.println(generics);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Generic Containers with Qty " + containerQty + " Created Successfully!", null));

			clear();

		}

		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Container Qty Cannot be 0, Please try again!", null));
			clear();
		}
	}

	// Clear Container Qty
	public void clear() {
		containerQty = 0;

	}// end clear`

	// @Scheduled(initialDelay = 1000, fixedRate = 10000)
	// public void sayHello() {
	// System.out.println("Hello World From Component ");
	// }

	public List<ASN> searchASN(String newASN) {

		try {

			myASN = newASN.toLowerCase();

			if (myASN != null) {
				System.out.println("The value has been changed " + myASN);

			} else {
				System.out.println("Container ID is null dude!!!");
			}

			asnList = asnRepository.findAllAsn(myASN);

		} catch (Exception e) {
			e.getMessage();
		}

		return asnList;
	}

	// Remove Duplicate suggestions from Search
	public List<String> distinctASN(String asn) {
		return asnRepository.allDistinctASN(asn);
	}

	public void printASN() {

		try {
			ip = genericTempService.findIPByUser(uname.toLowerCase());

			port = genericTempService.findPortByUser(uname.toLowerCase(), ip);

			System.out.println("The IP for the Username " + uname + " is " + ip);

			System.out.println("The Port for the Username " + uname + " is " + port);
		} catch (Exception e) {
			System.out.println("Its null " + uname.toLowerCase());
		}

		for (ASN n : asnList) {

			String s1 = service.getLabelType("ASN", uname.toLowerCase());

			s1 = s1.replace("$$CONT", n.getContainer_id());

			s1 = s1.replace("$$ASN", n.getAsn_nbr());

			s1 = s1.replace("$$PO", n.getPo_nbr());

			s1 = s1.replace("$$APPT", n.getAppt_nbr());

			System.out.println(s1);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Printing Started for ASN (" + n.getAppt_nbr() + " !)", null));

			try {
				ZebraUtils.printZpl(s1, ip, port);
			} catch (Exception e) {
				e.printStackTrace();
			}

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Printing Finished for ASN (" + n.getAppt_nbr() + " !)", null));

			System.out.println("The ASN LIST IS :" + n.getContainer_id() + " ---- " + n.getAsn_nbr());
		}

	}

	public void printContainer(Generic generic) {

		System.out.println("I am clicked bro...!!!");

		try {
			ip = genericTempService.findIPByUser(uname.toLowerCase());

			port = genericTempService.findPortByUser(uname.toLowerCase(), ip);

			System.out.println("The IP for the Username " + uname + " is " + ip);

			System.out.println("The Port for the Username " + uname + " is " + port);
		} catch (Exception e) {
			System.out.println("Its null " + uname.toLowerCase());
		}
		genericTempService.deleteAll();

		System.out.println("Checking Container ID is :" + genericService.isContainerExist(generic.getContainerId()));
		// ---------------------Main
		// Logic----------------------------------------
		if (genericService.isContainerExist(generic.getContainerId()) != null) {

			String s1 = service.getLabelType("Generic", uname.toLowerCase());

			s1 = s1.replace("$$CONT", generic.getContainerId());

			System.out.println(s1);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Printing Started for Container (" + generic.getContainerId() + " !)", null));

			try {
				ZebraUtils.printZpl(s1, ip, port);
			} catch (Exception e) {
				e.printStackTrace();
			}

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Printing Finished for Container (" + generic.getContainerId() + " !)", null));

		}

	}

	public void printASN(String asn) {

		try {
			ip = genericTempService.findIPByUser(uname.toLowerCase());

			port = genericTempService.findPortByUser(uname.toLowerCase(), ip);

			System.out.println("The IP for the Username " + uname + " is " + ip);

			System.out.println("The Port for the Username " + uname + " is " + port);
		} catch (Exception e) {
			System.out.println("Its null " + uname.toLowerCase());
		}

		asnList = asnRepository.findAllAsn(asn);

		String s1 = service.getLabelType("ASN", uname.toLowerCase());

		for (ASN n : asnList) {

			s1 = s1.replace("$$CONT", n.getContainer_id());

			s1 = s1.replace("$$ASN", n.getAsn_nbr());

			s1 = s1.replace("$$PO", n.getPo_nbr());

			s1 = s1.replace("$$APPT", n.getAppt_nbr());

			System.out.println(s1);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Printing Started for ASN (" + n.getAppt_nbr() + " !)", null));

			try {
				ZebraUtils.printZpl(s1, ip, port);
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("The ASN LIST IS :" + n.getContainer_id() + " ---- " + n.getAsn_nbr());

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Printing Finished for ASN (" + n.getAppt_nbr() + " !)", null));

			init();

		}

	}

}