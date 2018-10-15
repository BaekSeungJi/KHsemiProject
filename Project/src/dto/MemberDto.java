package dto;

import java.io.Serializable;

/*
DROP TABLE MEMBER
CASCADE CONSTRAINTS;
CREATE TABLE MEMBER(
	ID VARCHAR2(50) PRIMARY KEY,
	PWD VARCHAR2(50) NOT NULL,
	NAME VARCHAR2(50) NOT NULL,
	EMAIL VARCHAR2(50) UNIQUE,
	PHONE VARCHAR2(50) NOT NULL,
	BLACKLIST NUMBER(1) NOT NULL,	-- 0 == 일반, 1 == 블랙리스트
	AUTH NUMBER(1) NOT NULL			-- 1 == 최고 운영자, 2 == 개별 관리자, 3 == 회원
);
*/

public class MemberDto implements Serializable {
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String phone;
	private int blacklist;	//0==일반 1==블랙리스트
	private int auth;	//사용자 : 3 관리자 : 1
	
	public MemberDto() {}
	
	public MemberDto(String id, String pwd, String name, String email, String phone, int blacklist, int auth) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.blacklist = blacklist;
		this.auth = auth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getBlacklist() {
		return blacklist;
	}

	public void setBlacklist(int blacklist) {
		this.blacklist = blacklist;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", blacklist=" + blacklist + ", auth=" + auth + "]";
	}
	
	

}