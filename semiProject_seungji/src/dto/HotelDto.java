package dto;

import java.io.Serializable;

/*
DROP TABLE HOTEL
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_HOTEL;

CREATE TABLE HOTEL(
	SEQ NUMBER(8) PRIMARY KEY,
	
	ID VARCHAR2(50) NOT NULL,
	HOTELNAME VARCHAR2(50) NOT NULL,
	DESCRIPTION VARCHAR2(5000) NOT NULL,
	REGION VARCHAR2(50) NOT NULL,
	
	MAXPEOPLE NUMBER(10) NOT NULL,
	PRICE NUMBER(50) NOT NULL,
	HOTELPHONE NUMBER(12) NOT NULL,
	
	DEL NUMBER(1) NOT NULL,			// 0 == 유지, 1 == 삭제
	READCOUNT NUMBER(8) NOT NULL,
	REGDATE DATE NOT NULL
);

CREATE SEQUENCE SEQ_HOTEL
START WITH 1
INCREMENT BY 1;

ALTER TABLE HOTEL
ADD CONSTRAINT FK_HOTEL_ID FOREIGN KEY(ID)
REFERENCES MEMBER(ID);
*/

public class HotelDto implements Serializable {

}
