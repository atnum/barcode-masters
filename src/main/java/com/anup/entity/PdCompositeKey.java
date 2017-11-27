package com.anup.entity;

import java.io.Serializable;
import java.util.Date;

public class PdCompositeKey implements Serializable {

	private String FACILITY_ID;
	private String WAVE_NBR;
	private String DISTRO_NBR;
	private String PICK_FROM_CONTAINER_ID;
	private String DEST_ID;
	private String PICK_TO_CONTAINER_ID;
	private String ITEM_ID;
	private String LOT_NBR;
	private int CASE_PACK_SIZE;
	private Date PROCESS_DATE;

}
