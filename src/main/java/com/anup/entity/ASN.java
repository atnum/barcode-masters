package com.anup.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ASN_VIEW")
@IdClass(ASNCompositeKey.class)
public class ASN {

	@Id
	private String asn_nbr;
	@Id
	private String container_id;
	@Id
	private String po_nbr;
	@Id
	private String appt_nbr;
	@Id
	private int dest_id;
	@Id
	private String item_id;
	@Id
	private int unit_qty;
	@Id
	private int rcvd_unit_qty;

	@Override
	public String toString() {
		return getAsn_nbr();
	}

}
