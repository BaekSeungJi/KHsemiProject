package dto;

import java.io.Serializable;

public class PdsfileDto implements Serializable {

	private int seq;
	
	private String id;
	private String title;
	private String content;
	private String filename;
	private String realname;
	
	
	private int readcount;
	private int downcount;
	
	private int ref;
	private int step;
	private int depth;
	
	private int del;
	private String regdate;
	
	public PdsfileDto() {
		// TODO Auto-generated constructor stub
	}

	public PdsfileDto(int seq, String id, String title, String content, String filename, String realname, int readcount,
			int downcount, int ref, int step, int depth, int del, String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.realname = realname;
		this.readcount = readcount;
		this.downcount = downcount;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.del = del;
		this.regdate = regdate;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
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
