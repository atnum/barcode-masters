package com.anup.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "PICK_DIRECTIVE")
@IdClass(PdCompositeKey.class)
public class PickDirective {

	@Id
	private String FACILITY_ID;
	@Id
	private String WAVE_NBR;
	@Id
	private String DISTRO_NBR;
	@Id
	private String PICK_FROM_CONTAINER_ID;
	@Id
	private String DEST_ID;
	@Id
	private String PICK_TO_CONTAINER_ID;
	@Id
	private String ITEM_ID;
	@Id
	private String LOT_NBR;
	@Id
	private int CASE_PACK_SIZE;
	private String PICK_TYPE;
	private String ZONE;
	private int PICK_CONTAINER_QTY;
	private int UNIT_QTY;
	private String CUBE;
	private String LABELS_PRINTED_FLAG;
	private String PICK_ORDER;
	private String BREAK_BY_DISTRO;
	private Date DISTRO_TS;
	private String WIP_CODE;
	private String USER_ID;
	private String PICK_IN_PROGRESS;
	@Id
	private Date PROCESS_DATE;
	private String RELEASED;
	private String LOGICAL_CHUTE;
	private String PACK_WAVE;
	private String ON_HOLD;
	private String SEQ_NBR;
	private String PALLET_GROUP_ID;
	private String WORK_DIRECTIVE_ID;
	private String LET_DOWN_QTY;

	@Override
	public String toString() {
		return "PickDirective [PICK_TO_CONTAINER_ID=" + PICK_TO_CONTAINER_ID + "]";
	}

}