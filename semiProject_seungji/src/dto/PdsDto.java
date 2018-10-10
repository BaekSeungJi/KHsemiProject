package dto;

import java.io.Serializable;

/*
DROP TABLE PDS
CASCADE CONSTRAINT;

DROP SEQUENCE SEQ_PDS;

CREATE TABLE PDS(
	SEQ NUMBER(8) PRIMARY KEY,
	
	ID VARCHAR2(50) NOT NULL,
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	
	READCOUNT NUMBER(8) NOT NULL,
	DOWNCOUNT NUMBER(8) NOT NULL,
	
	REF NUMBER(8) NOT NULL,
	STEP NUMBER(8) NOT NULL,
	DEPTH NUMBER(8) NOT NULL,
	
	DEL NUMBER(1) NOT NULL,
	REGDATE DATE NOT NULL
);

CREATE SEQUENCE SEQ_PDS
START WITH 1 INCREMENT BY 1;

ALTER TABLE PDS
ADD CONSTRAINT FK_PDS_ID FOREIGN KEY(ID)
REFERENCES MEMBER(ID);

*/

public class PdsDto implements Serializable {
	
	private int seq;
	
	private String id;
	private String title;
	private String content;
	
	private int readcount;
	private int downcount;
	
	private int ref;
	private int step;
	private int depth;
	
	private int del;
	private String regdate;
	
	public PdsDto() {
	}
	
	public PdsDto(int seq, String id, String title, String content, int readcount, int downcount, int ref, int step,
			int depth, int del, String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.readcount = readcount;
		this.downcount = downcount;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.del = del;
		this.regdate = regdate;
	}
	
	// 입력받는것만 있는 생성자(자료실 글 추가할때)
	public PdsDto(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
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
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getDowncount() {
		return downcount;
	}
	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getDel() {
		return del;
	}
	public void setDel(int del) {
		this.del = del;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
	

}
