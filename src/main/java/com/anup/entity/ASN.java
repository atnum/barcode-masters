package com.anup.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ASN_VIEW")
public class ASN {

	@Id
	private String asn_nbr;

	private String container_id;

	private String po_nbr;
	
	private String appt_nbr;

	private int dest_id;

	private String item_id;

	private int unit_qty;

	private int rcvd_unit_qty;

	@Override
	public String toString() {
		return getAsn_nbr();
	}

}
