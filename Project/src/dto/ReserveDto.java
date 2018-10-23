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

ALTER TABLE RESERVE
ADD NUM NUMBER(8);

ALTER TABLE RESERVE
ADD CONSTRAINT FK_RESERVE_NUM FOREIGN KEY(NUM)
REFERENCES HOTEL(SEQ);

*/

public class ReserveDto implements Serializable {
	
	private int seq;
	
	private String id;
	private String hotelname;
	private String request;
	private String realdate;
	
	private String regdate;
	private int del;
	private String checkin;
	private String checkout;
	private int num;
	
	public ReserveDto() {
	}
		


	public ReserveDto(int seq, String id, String hotelname, String request, String realdate, String regdate, int del,
			String checkin, String checkout, int num) {
		super();
		this.seq = seq;
		this.id = id;
		this.hotelname = hotelname;
		this.request = request;
		this.realdate = realdate;
		this.regdate = regdate;
		this.del = del;
		this.checkin = checkin;
		this.checkout = checkout;
		this.num = num;
	}



	// 입력받는것만 있는 생성자(예약내역 추가할때)
	public ReserveDto(String id, String hotelname, String request, String realdate) {
		super();
		this.id = id;
		this.hotelname = hotelname;
		this.request = request;
		this.realdate = realdate;
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

	public String getRealdate() {
		return realdate;
	}

	public void setRealdate(String realdate) {
		this.realdate = realdate;
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



	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}
	
	

}