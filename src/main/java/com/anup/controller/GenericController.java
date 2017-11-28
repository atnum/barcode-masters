package com.anup.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.anup.entity.Facility;
import com.anup.entity.Generic;
import com.anup.entity.GenericTemp;
import com.anup.entity.IPAddress;
import com.anup.entity.PickDirective;
import com.anup.repository.PDRepository;
import com.anup.service.GenericService;
import com.anup.service.GenericTempService;

import fr.w3blog.zpl.model.ZebraUtils;
import lombok.Getter;
import lombok.Setter;

//@ManagedBean
//@SessionScoped
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
// Spring-specific annotation
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
	private PDRepository repository;

	private List<PickDirective> myList;

	private String ipAddress;

	private List<Generic> generics;

	private List<Generic> results;

	private String containerId;

	private Generic generic1;

	private List<Facility> facility;

	public Generic generic = new Generic();

	private String username; // getting logged user

	private String barcodeType;

	private int containerQty;

	private String randomContainerId;

	private String newContainerId;

	private static final int MASK = (-1) >>> 1;

	private List<IPAddress> addresses;

	private IPAddress address;

	public String ip;

	public static String myIP;

	public GenericController() {
		barcodeType = "code128";
		
		myIP = ip;
	}

	@PostConstruct
	public void init() {
		// generics = genericService.findAll();
		generics = genericService.findAllByDesc();

		facility = genericService.findAllFacility();

		addresses = genericTempService.getAllAddress();

		Iterator iter = addresses.iterator();

		Object first = iter.next();

		ip = (String) first;

		myIP = ip;

		System.out.println("The IP Address is: " + ip);

		// myList = repository.findAllByDesc();

		// System.out.println(myList);

		// GETTING THE CURRENT USERNAME
		username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

		// Converting into CamelCase using custom code
		username = toCamelCase(username);

	}

	public void changed(ValueChangeEvent e) throws IOException {
		newContainerId = e.getNewValue().toString();
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

		genericTempService.deleteAll();

		System.out.println("Checking Container ID is :" + genericService.isContainerExist(generic.getContainerId()));
		// ---------------------Main
		// Logic----------------------------------------
		if (genericService.isContainerExist(generic.getContainerId()) == null) {

			// String zpl4 = "^XA^LH30,30^FO20,10^AF^FV";
			// String zpl5 = "^FS";
			// String zpl6 = "^FO20,60^BC,,100^FD";
			// String zpl7 = "^FS^MCN^XZ";
			// String zpl1 = "^XA^FO100,40^BY3^BC,,30^FD";
			// String zpl2 = "^XZ";
			// String zpl3 = zpl1 + generic.getContainerId() + zpl2;
			// String zpl3 = zpl4 + "GENERIC LABELS" + zpl5 + zpl6 +
			// generic.getContainerId() + zpl7;
			// System.out.println(zpl3);

			String s1 = "^XA~TA000~JSN^LT0^MNW^MTT^PON^PMN^LH0,0^JMA^PR6,6~SD15^JUS^LRN^CI0^XZ\r\n" + "^XA\r\n"
					+ "^MMT\r\n" + "^PW609\r\n" + "^LL0406\r\n" + "^LS0\r\n"
					+ "^FO192,32^GFA,02688,02688,00028,:Z64:\r\n"
					+ "eJztkLFOwzAQQC81ijNUzsDiIWr6CZVYbkoHJL4jLJ0vYiBDpFhCKkyw8gV8AxsplejIL3jratSlQ0VxqqRNUT8AJD/Jsn32O98ZwOFwOP433A75O9gEEjuovbdpFhGAZ9fCTqa9nnS8fHcGoyZEg05em8zv5Ez9zhkeeV51duzxjqeA6VTH7BGZsV4iEqb3HvSzlbn5/C6iHMhb+GWfGs9TEOAknd29ICIgm4aLBzx4UhbZbP4RjaT1OL9/lo3HFIzXmG7nSg5tnT0Rsrf13isJKKt4FVFdYMm5aev0KoFA14opTOv+poK9HvqLsfaYiigAOuc+w31/WmAwTPWlkigBL4ToYdD+p0kGI8q+5lVU2Idv4yvf7tsvnISaqLTvjTXky+VTP9R5cxa/89mmSEJTxsrbrhahsXs4xRCCk3GHw/FX+AFeo17t:2521\r\n"
					+ "^BY3,3,136^FT87,284^BCN,,Y,N\r\n" + "^FD>:%%CONT^FS\r\n" + "^PQ1,0,1,Y^XZ";

			s1 = s1.replaceAll("%%CONT", generic.getContainerId());

			System.out.println(s1);

			try {
				ZebraUtils.printZpl(s1, ip, 9100);
			} catch (Exception e) {
				e.printStackTrace();
			}

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

		genericTempService.deleteAll();

		if (containerQty != 0)

		{

			for (int i = 0; i < containerQty; i++) {

				Generic g = new Generic();
				g.setContainerId(genericTempService.getRandomContainer());
				g.setFacilityId(generic.getFacilityId());

				// genericService.save(g);

				GenericTemp gt = new GenericTemp();
				// Random rand = new Random();
				// gt.setContainerId(rand.nextInt() & MASK);
				gt.setContainerId(g.getContainerId());
				gt.setFacilityId(g.getFacilityId());
				//
				// String zpl1 = "^XA^FO100,40^BY3^BC,,30^FD";
				// String zpl2 = "^XZ";
				// String zpl3 = zpl1 + g.getContainerId() + zpl2;

				System.out.println("The Container id are : " + gt.getContainerId());

				String s1 = "^XA~TA000~JSN^LT0^MNW^MTT^PON^PMN^LH0,0^JMA^PR6,6~SD15^JUS^LRN^CI0^XZ\r\n" + "^XA\r\n"
						+ "^MMT\r\n" + "^PW609\r\n" + "^LL0406\r\n" + "^LS0\r\n"
						+ "^FO192,32^GFA,02688,02688,00028,:Z64:\r\n"
						+ "eJztkLFOwzAQQC81ijNUzsDiIWr6CZVYbkoHJL4jLJ0vYiBDpFhCKkyw8gV8AxsplejIL3jratSlQ0VxqqRNUT8AJD/Jsn32O98ZwOFwOP433A75O9gEEjuovbdpFhGAZ9fCTqa9nnS8fHcGoyZEg05em8zv5Ez9zhkeeV51duzxjqeA6VTH7BGZsV4iEqb3HvSzlbn5/C6iHMhb+GWfGs9TEOAknd29ICIgm4aLBzx4UhbZbP4RjaT1OL9/lo3HFIzXmG7nSg5tnT0Rsrf13isJKKt4FVFdYMm5aev0KoFA14opTOv+poK9HvqLsfaYiigAOuc+w31/WmAwTPWlkigBL4ToYdD+p0kGI8q+5lVU2Idv4yvf7tsvnISaqLTvjTXky+VTP9R5cxa/89mmSEJTxsrbrhahsXs4xRCCk3GHw/FX+AFeo17t:2521\r\n"
						+ "^BY3,3,136^FT87,284^BCN,,Y,N\r\n" + "^FD>:%%CONT^FS\r\n" + "^PQ1,0,1,Y^XZ";

				s1 = s1.replaceAll("%%CONT", gt.getContainerId());

				System.out.println(s1);

				try {
					ZebraUtils.printZpl(s1, ip, 9100);
				} catch (Exception e) {
					e.printStackTrace();
				}
				genericTempService.save(gt);
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

}
