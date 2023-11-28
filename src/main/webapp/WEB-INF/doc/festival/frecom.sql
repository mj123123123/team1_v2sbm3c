/**********************************/
/* Table Name: 축제/행사 추천 */
/**********************************/
CREATE TABLE frecom(
		frecomno NUMBER(10) NOT NULL PRIMARY KEY,
		fcontentsno NUMBER(10),
		name VARCHAR2(50) NOT NULL,
  FOREIGN KEY (fcontentsno) REFERENCES FESTIVAL (fcontentsno)
);

--COMMENT ON TABLE FESTIVAL is '축제/행사 게시물';
COMMENT ON COLUMN FESTIVALRECOM.festivalrecomno IS '축제/행사 추천 번호';
COMMENT ON COLUMN FESTIVALRECOM.fcontentsno IS '축제/행사 게시물 번호';
COMMENT ON COLUMN FESTIVALRECOM.name IS '축제/행사 추천 이름';

DROP SEQUENCE festivalrecom_seq;

CREATE SEQUENCE festivalrecom_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지