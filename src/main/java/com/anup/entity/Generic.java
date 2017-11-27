package com.anup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "XX_GENERIC_LABELS")
@Getter
@Setter
public class Generic {

	@Id
	@Column(name = "PRINT_LABEL_GROUP_NBR")
    @GeneratedValue
	private long printGroupLabelNbr;

	@Column(name = "FACILITY_ID")
	@NotEmpty
	private String facilityId;

	@Column(name = "CONTAINER_ID")
	@NotEmpty(message="Container Id field cannot be null")
	private String containerId;

	public Generic() {

	}

	@Override
	public String toString() {
		return getContainerId();
	}

}
