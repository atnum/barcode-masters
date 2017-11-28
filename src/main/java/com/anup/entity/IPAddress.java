package com.anup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "ip" }) })
public class IPAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "Printer_Name")
	private String printerName;

	public String ip;

	private int port;

	@Column(name = "DEFAULT_IP")
	private int defaultIp;

	@Override
	public String toString() {
		return ip;
	}

}
