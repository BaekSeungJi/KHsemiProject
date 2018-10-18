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
	DESCRIPTION VARCHAR2(4000) NOT NULL,
	REGION VARCHAR2(50) NOT NULL,
	
	MAXPEOPLE NUMBER(10) NOT NULL,
	PRICE NUMBER(12) NOT NULL,
	HOTELPHONE NUMBER(12) NOT NULL,
	
	DEL NUMBER(1) NOT NULL,			-- 0 == 유지, 1 == 삭제
	READCOUNT NUMBER(8) NOT NULL,
	REGDATE DATE NOT NULL
);
CREATE SEQUENCE SEQ_HOTEL
START WITH 1
INCREMENT BY 1;

ALTER TABLE HOTEL
ADD CONSTRAINT FK_HOTEL_ID FOREIGN KEY(ID)
REFERENCES MEMBER(ID);

ALTER TABLE HOTEL
ADD IMAGE VARCHAR2(50);

*/


	public class HotelDto implements Serializable {
		
		private int seq;
		private String id;
		private String hotelname;
		private String description;
		private String region;
		
		private int maxpeople;
		private int price;
		private int hotelphone;
		
		private int del;
		private int readcount;
		private String regdate;
		
		public HotelDto() {
		}
		
		public HotelDto(int seq, String id, String hotelname, String description, String region, int maxpeople, int price,
				int hotelphone, int del, int readcount, String regdate) {
			super();
			this.seq = seq;
			this.id = id;
			this.hotelname = hotelname;
			this.description = description;
			this.region = region;
			this.maxpeople = maxpeople;
			this.price = price;
			this.hotelphone = hotelphone;
			this.del = del;
			this.readcount = readcount;
			this.regdate = regdate;
		}
		
		// 입력받는것만 있는 생성자(호텔 추가할때)
		public HotelDto(String id, String hotelname, String description, String region, int maxpeople, int price,
				int hotelphone) {
			super();
			this.id = id;
			this.hotelname = hotelname;
			this.description = description;
			this.region = region;
			this.maxpeople = maxpeople;
			this.price = price;
			this.hotelphone = hotelphone;
		}

		public int getSeq() {
			return seq;
		}
		public void setSeq(int seq) {
			this.seq = seq;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getHotelname() {
			return hotelname;
		}
		public void setHotelname(String hotelname) {
			this.hotelname = hotelname;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public int getMaxpeople() {
			return maxpeople;
		}
		public void setMaxpeople(int maxpeople) {
			this.maxpeople = maxpeople;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public int getHotelphone() {
			return hotelphone;
		}
		public void setHotelphone(int hotelphone) {
			this.hotelphone = hotelphone;
		}
		public int getDel() {
			return del;
		}
		public void setDel(int del) {
			this.del = del;
		}
		public int getReadcount() {
			return readcount;
		}
		public void setReadcount(int readcount) {
			this.readcount = readcount;
		}
		public String getRegdate() {
			return regdate;
		}
		public void setRegdate(String regdate) {
			this.regdate = regdate;
		}
		
		

	}
	

