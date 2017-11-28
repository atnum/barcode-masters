package com.anup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="facility")
public class Facility {

	@Id
	@Column(name = "Facility_Id")
	private String facilityId;

	@Column(name = "Description")
	private String description;

	@Column(name = "DELETE_ENABLE_FLAG")
	private boolean deleteEnableFlag;

	public Facility() {
	}

	public Facility(String facilityId, String description, boolean deleteEnableFlag) {
		this.facilityId = facilityId;
		this.description = description;
		this.deleteEnableFlag = deleteEnableFlag;
	}

	@Override
	public String toString() {
		return "Facility [facilityId=" + facilityId + ", description=" + description + ", deleteEnableFlag="
				+ deleteEnableFlag + "]";
	}

}
