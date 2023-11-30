/* Table name: TCATE */
DROP TABLE TCATE;

CREATE TABLE TCATE(
    TCATENO       NUMBER(10)      NOT NULL,
    NAME    VARCHAR2(30)      NOT NULL,   
    SEQNO      NUMBER(7)      NOT NULL,   
    VISIBLE    CHAR(1)    NOT NULL,
    PRIMARY KEY (TCATENO)
);

COMMENT ON TABLE TCATE is '여행 카테고리';
COMMENT ON COLUMN TCATE.TCATENO is '여행 카테고리 번호';
COMMENT ON COLUMN TCATE.NAME is '카테고리 이름';
COMMENT ON COLUMN TCATE.SEQNO is '출력 순서';
COMMENT ON COLUMN TCATE.VISIBLE is '출력 모드';

DROP SEQUENCE TCATE_SEQ;

CREATE SEQUENCE TCATE_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지


