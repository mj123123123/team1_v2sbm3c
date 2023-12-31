/**********************************/
/* Table Name: 축제/행사 리뷰 */
/**********************************/
CREATE TABLE FREVIEW(
		reviewno NUMBER(10) NOT NULL PRIMARY KEY,
		contentsno NUMBER(10),
		MEMBERNO NUMERIC(10),
		title VARCHAR2(100) NOT NULL,
		content CLOB NOT NULL,
		cnt NUMBER(10),
        pwd VARCHAR2(10),
		rdate DATE NOT NULL,
		file1 VARCHAR2(200),
		file1saved VARCHAR2(400),
		thumb1 VARCHAR2(200),
		size1 NUMBER(10),
		map VARCHAR2(1000),
  FOREIGN KEY (contentsno) REFERENCES FESTIVAL (contentsno),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

DROP SEQUENCE freview_seq;

CREATE SEQUENCE freview_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지
