package dto;

import java.io.Serializable;

/*
DROP TABLE REVIEW
CASCADE CONSTRAINTS;
DROP SEQUENCE SEQ_REVIEW;
CREATE TABLE REVIEW(
	NUM NUMBER(8) NOT NULL,
	
	ID VARCHAR2(50) NOT NULL,
	HOTELNAME VARCHAR2(50) NOT NULL,
	TITLE VARCHAR2(50) NOT NULL,
	CONTENT VARCHAR2(300) NOT NULL,
	
	SCORE NUMBER(10) NOT NULL,
	
	DEL NUMBER(1) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL,
	REGDATE DATE NOT NULL
);
ALTER TABLE REVIEW
ADD CONSTRAINT FK_REVIEW_NUM FOREIGN KEY(NUM)
REFERENCES RESERVE(SEQ);

ALTER TABLE REVIEW 
MODIFY(TITLE VARCHAR2(100));


ALTER TABLE RESERVE
ADD NUM NUMBER(8);

ALTER TABLE RESERVE
ADD CONSTRAINT FK_RESERVE_NUM FOREIGN KEY(NUM)
REFERENCES HOTEL(SEQ);

*/

public class ReviewDto implements Serializable {
	
	private String id;
	private String hotelname;
	private String title;
	private String content;
	private int score;
	
	private int del;
	private int readcount;
	private String regdate;
	
	private int num;
	
	public ReviewDto() {
	}

	public ReviewDto(String id, String hotelname, String title, String content, int score, int del, int readcount,
			String regdate, int num) {
		super();
		this.id = id;
		this.hotelname = hotelname;
		this.title = title;
		this.content = content;
		this.score = score;
		this.del = del;
		this.readcount = readcount;
		this.regdate = regdate;
		this.num = num;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	
	

}