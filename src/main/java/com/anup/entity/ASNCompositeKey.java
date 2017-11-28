package com.anup.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ASNCompositeKey implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String asn_nbr;


	private String container_id;


	private String po_nbr;


	private String appt_nbr;


	private int dest_id;


	private String item_id;


	private int unit_qty;


	private int rcvd_unit_qty;

}
