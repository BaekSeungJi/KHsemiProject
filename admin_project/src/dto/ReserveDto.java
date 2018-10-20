package dto;

import java.io.Serializable;

/*
DROP TABLE RESERVE
CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_RESERVE;
CREATE TABLE RESERVE(
	SEQ NUMBER(8) PRIMARY KEY,
	
	ID VARCHAR2(50) NOT NULL,
	HOTELNAME VARCHAR2(50) NOT NULL,
	REQUEST VARCHAR2(200),
	CHECKIN VARCHAR2(50) NOT NULL,	// 실제 숙박일(체크인)
	CHECKOUT VARCHAR2(50) NOT NULL,	// 실제 숙박일(체크아웃)
	
	REGDATE DATE NOT NULL,	// 예약을 한 날짜. 등록일
	DEL NUMBER(1) NOT NULL
);
CREATE SEQUENCE SEQ_RESERVE
START WITH 1
INCREMENT BY 1;
ALTER TABLE RESERVE
ADD CONSTRAINT FK_RESERVE_ID FOREIGN KEY(ID)
REFERENCES MEMBER(ID);
*/

public class ReserveDto implements Serializable {
	
	private int seq;
	
	private String id;
	private String hotelname;
	private String request;

	private String checkin;
	private String checkout;
	
	private String regdate;
	private int del;
	
	public ReserveDto() {
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

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public ReserveDto(int seq, String id, String hotelname, String request, String checkin, String checkout,
			String regdate, int del) {
		super();
		this.seq = seq;
		this.id = id;
		this.hotelname = hotelname;
		this.request = request;
		this.checkin = checkin;
		this.checkout = checkout;
		this.regdate = regdate;
		this.del = del;
	}

	public ReserveDto(String id, String hotelname, String request, String checkin, String checkout) {
		super();
		this.id = id;
		this.hotelname = hotelname;
		this.request = request;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	@Override
	public String toString() {
		return "ReserveDto [seq=" + seq + ", id=" + id + ", hotelname=" + hotelname + ", request=" + request
				+ ", checkin=" + checkin + ", checkout=" + checkout + ", regdate=" + regdate + ", del=" + del + "]";
	}
	
	
	
}