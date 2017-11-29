package com.anup.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.anup.entity.IPAddress;
import com.anup.service.GenericTempService;

import lombok.Getter;
import lombok.Setter;

@Scope(value = "request")
// Spring-specific annotation
@Component
@Getter
@Setter
public class PrinterController {

	@Autowired
	private GenericTempService genericTempService;
	
	@Autowired
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
