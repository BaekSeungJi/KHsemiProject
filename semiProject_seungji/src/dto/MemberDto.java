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
	BLACKLIST NUMBER(1) NOT NULL,	// 0 == 일반, 1 == 블랙리스트
	AUTH NUMBER(1) NOT NULL			// 1 == 최고 운영자, 2 == 개별 관리자, 3 == 회원
);
*/

public class MemberDto implements Serializable {

}
