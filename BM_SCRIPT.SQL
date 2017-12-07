
DROP SEQUENCE RANDOM_CONTAINER_SEQ;

CREATE SEQUENCE RANDOM_CONTAINER_SEQ INCREMENT BY 1 START WITH 1 MAXVALUE 99999999999999999 NOCACHE ORDER;

DROP SEQUENCE hibernate_sequence;

CREATE SEQUENCE hibernate_sequence INCREMENT BY 1 START WITH 1 MAXVALUE 99999999999999999 NOCACHE ORDER;

DROP SEQUENCE a1_seq;

CREATE SEQUENCE a1_seq INCREMENT BY 1 START WITH 1 MAXVALUE 99999999999999999 NOCACHE ORDER;

DROP TABLE XX_GENERIC_LABELS;

CREATE TABLE XX_GENERIC_LABELS
   (	"PRINT_LABEL_GROUP_NBR" NUMBER(19,0) NOT NULL ENABLE, 
	"CONTAINER_ID" VARCHAR2(255 CHAR) NOT NULL ENABLE, 
	"FACILITY_ID" VARCHAR2(255 CHAR) NOT NULL ENABLE, 
	 PRIMARY KEY ("PRINT_LABEL_GROUP_NBR"));

DROP TABLE XX_GENERIC_LABELS_TEMP;
	 
CREATE TABLE XX_GENERIC_LABELS_TEMP
   (	"PRINT_LABEL_GROUP_NBR" NUMBER(19,0) NOT NULL ENABLE, 
	"CONTAINER_ID" NUMBER(19,0), 
	"FACILITY_ID" VARCHAR2(255 CHAR) NOT NULL ENABLE, 
	 PRIMARY KEY ("PRINT_LABEL_GROUP_NBR"));
	 

DROP TABLE IPADDRESS;

CREATE TABLE IPADDRESS 
   ("ID" NUMBER(10,0) NOT NULL ENABLE, 
	"IP" VARCHAR2(255 CHAR), 
	"PORT" NUMBER(10,0) NOT NULL ENABLE, 
	"PRINTER_NAME" VARCHAR2(255 CHAR),
	"DEFAULT_IP" NUMBER(1) DEFAULT 1,
	"USER_FLAG" VARCHAR2(20),
	"BARCODE_TYPE" VARCHAR2(30),
	 PRIMARY KEY ("ID"));

DROP TABLE PRODUCT; 

CREATE TABLE PRODUCT
   ("ID" NUMBER(10,0) NOT NULL ENABLE, 
	"BARCODE" VARCHAR2(255 CHAR), 
	"CATEGORY" VARCHAR2(255 CHAR), 
	"DESCRIPTION" VARCHAR2(255 CHAR), 
	"NAME" VARCHAR2(255 CHAR), 
	"PRICE" NUMBER(19,0) NOT NULL ENABLE, 
	"QTY" NUMBER(10,0) NOT NULL ENABLE, 
	 PRIMARY KEY ("ID"));
	 

DROP TABLE PRODUCT_TEMP;
	 
CREATE TABLE PRODUCT_TEMP
   ("ID" NUMBER(10,0) NOT NULL ENABLE, 
	"BARCODE" VARCHAR2(255 CHAR), 
	"CATEGORY" VARCHAR2(255 CHAR), 
	"DESCRIPTION" VARCHAR2(255 CHAR), 
	"NAME" VARCHAR2(255 CHAR), 
	"PRICE" NUMBER(19,0) NOT NULL ENABLE, 
	"QTY" NUMBER(10,0) NOT NULL ENABLE, 
	 PRIMARY KEY ("ID"));

DROP TABLE ROLE;

CREATE TABLE ROLE 
   ("ROLE_ID" NUMBER(10,0) NOT NULL ENABLE, 
	"ROLE" VARCHAR2(45 CHAR) NOT NULL ENABLE, 
	"USER_ID" NUMBER(10,0) NOT NULL ENABLE, 
	 PRIMARY KEY ("ROLE_ID"));

DROP TABLE USERS;
	 
CREATE TABLE USERS 
   ("USER_ID" NUMBER(10,0) NOT NULL ENABLE, 
	"EMAIL" VARCHAR2(50 CHAR) NOT NULL ENABLE, 
	"ENABLED" NUMBER(1,0) NOT NULL ENABLE, 
	"FIRST_NAME" VARCHAR2(50 CHAR) NOT NULL ENABLE, 
	"LAST_NAME" VARCHAR2(50 CHAR) NOT NULL ENABLE, 
	"PASSWORD" VARCHAR2(255 CHAR) NOT NULL ENABLE, 
	"USER_NAME" VARCHAR2(50 CHAR) NOT NULL ENABLE, 
	 PRIMARY KEY ("USER_ID"));

--create or replace view asn_view as
--SELECT
--    a.asn_nbr,
--    c.container_id,
--    a.po_nbr,
--    a.appt_nbr,
--    a.dest_id,
--    a.item_id,
--    a.unit_qty,
--    a.rcvd_unit_qty
--FROM
--    container c,
--    asn_item a,
--    container_item ci
--WHERE
--    c.po_nbr IS NOT NULL
--    AND   c.location_id IS NULL
--    AND   c.container_status = 'A'
--    AND   c.container_id = ci.container_id
--    AND   c.facility_id = ci.facility_id
--    AND   ci.item_id = a.item_id
--    AND   ci.distro_nbr = a.distro_nbr
--    AND   ci.facility_id = a.facility_id
--    AND   a.po_nbr = c.po_nbr
--    AND   a.facility_id = c.facility_id
--    AND   a.appt_nbr = nvl(c.appt_nbr,0)
--    AND   a.dest_id = c.dest_id;

create or replace view asn_view as
SELECT
    a.asn_nbr,
    c.container_id,
    a.po_nbr,
    a.appt_nbr,
    a.dest_id,
    a.item_id,
    a.unit_qty,
    a.rcvd_unit_qty
FROM
    container c,
    asn_item a,
    container_item ci
WHERE
    c.po_nbr IS NOT NULL
    AND   c.location_id IS NULL
    AND   c.container_status = 'A'
    AND   c.container_id = ci.container_id
    AND   c.facility_id = ci.facility_id
    AND   ci.item_id = a.item_id
    AND   ci.distro_nbr = a.distro_nbr
    AND   ci.facility_id = a.facility_id
    AND   a.po_nbr = c.po_nbr
    AND   a.facility_id = c.facility_id
    AND   a.appt_nbr <> 0 
    AND   a.rcvd_unit_qty = 0
    AND   a.dest_id = c.dest_id;


DROP TABLE BARCODES;

CREATE TABLE BARCODES
   ("ID" NUMBER(10,0) NOT NULL ENABLE, 
	"BARCODE_TYPE" VARCHAR2(45 CHAR) NOT NULL ENABLE, 
	"BARCODE_NAME" VARCHAR2(45 CHAR) NOT NULL ENABLE, 
	"BARCODE_VALUE" VARCHAR2(3000 CHAR) NOT NULL ENABLE, 
	 PRIMARY KEY ("ID"));
	 