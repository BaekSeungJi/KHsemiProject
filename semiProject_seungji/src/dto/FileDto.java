package dto;

import java.io.Serializable;

/*
DROP TABLE FILE_DB
CASCADE CONSTRAINT;

CREATE TABLE FILE_DB(
	NUM NUMBER(8) NOT NULL,
	FILENAME VARCHAR2(50) NOT NULL,	// 중복을 거르기 위한 이름(시간으로 파일이름 설정하는거)
	REALNAME VARCHAR2(50) NOT NULL	// 'test.jsp' 등... 실제 출력할 파일 이름
);

ALTER TABLE FILE_DB
ADD CONSTRAINT FK_FILE_NUM FOREIGN KEY(NUM)
REFERENCES PDS(SEQ);

*/

public class FileDto implements Serializable {

}
