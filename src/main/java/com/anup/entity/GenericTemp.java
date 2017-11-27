package com.anup.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "XX_GENERIC_LABELS_TEMP")
@Getter
@Setter
public class GenericTemp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PRINT_LABEL_GROUP_NBR")
	// @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
	// @SequenceGenerator(name = "SEQ", sequenceName = "GENERIC_SEQ")
	@SequenceGenerator(name = "a1_seq", sequenceName = "a1_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "a1_seq")
	private long printGroupLabelNbr;

	@Column(name = "FACILITY_ID")
	@NotEmpty
	private String facilityId;

	@Column(name = "CONTAINER_ID")
	private String containerId;

	public GenericTemp() {

	}

	@Override
	public String toString() {
		return "Printing GenericTemp < [ printGroupLabelNbr=" + printGroupLabelNbr + ", facilityId=" + facilityId
				+ ", containerId=" + containerId + "] >";
	}

}
