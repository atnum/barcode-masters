package com.anup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "ip" }) })
public class IPAddress {

	@Id
	@GeneratedValue
	private int id;

	@Column(name = "Printer_Name")
	@NotNull
	private String printerName;

	@NotNull
	public String ip;
	
	@Column(name = "USER_FLAG")
	public String USER_FLAG;

	@NotNull
	private int port;

	@Column(name = "DEFAULT_IP")
	private int default_ip;
	
	@Column(name = "BARCODE_TYPE")
	private String barcodeType;

	@Override
	public String toString() {
		return ip;
	}

}
