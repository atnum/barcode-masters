package com.anup.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.anup.entity.IPAddress;
import com.anup.service.GenericTempService;

import lombok.Getter;
import lombok.Setter;

//@Scope(value = "session")
//// Spring-specific annotation
//@Component
@Named
@Getter
@Setter
@ViewScoped
public class PrinterController {

	@Inject
	private GenericTempService genericTempService;
	
	@Inject
	GenericController gc;

	private String printerName;

	private String ip;

	private int port;

	private int default_ip;

	public PrinterController() {

		port = 9100;

		default_ip = 0;
	}

	public void save() {

		System.out.println("I am CLicked:");

		IPAddress ipadd = new IPAddress();
		ipadd.setDefault_ip(default_ip);
		ipadd.setIp(ip);
		ipadd.setPort(port);
		ipadd.setPrinterName(printerName);

		genericTempService.savePrinter(ipadd);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Printer with IP Address (" + ip + ") Created Successfully!", null));
		
		gc.init();
		
		clear();

	}

	public void clear() {

		printerName = null;

		ip = null;

	}

}
