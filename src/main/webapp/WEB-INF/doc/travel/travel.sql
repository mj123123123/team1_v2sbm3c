/* Table name: TRAVEL */
DROP TABLE TRAVEL;

CREATE TABLE TRAVEL(
    TRAVELNO       NUMBER(10)      NOT NULL,
    CATENO    NUMBER(10)      NOT NULL, -- FK
    REGION      VARCHAR2(30)      NOT NULL,   
    CONTENT    CLOB    NOT NULL,
    REPLY    VARCHAR2(100)    NOT NULL,
    RECOM    NUMBER(7)    DEFAULT 0    NOT NULL,
    MAP    VARCHAR2(1000)    NULL,
    YOUTUBE    VARCHAR2(500)    NULL,
    FILE1    VARCHAR2(50)    NULL,
    FILE1_SAVED    VARCHAR2(50)    NULL,
    THUMB1    VARCHAR2(50)    NULL,
    
    FOREIGN KEY (CATENO) REFERENCES TRAVELCATE (CATENO),
    PRIMARY KEY (TRAVELNO)
);

COMMENT ON TABLE TRAVEL is '여행지';
COMMENT ON COLUMN TRAVEL.TRAVELNO is '여행지 번호';
COMMENT ON COLUMN TRAVEL.CATENO is '여행 카테고리 번호';
COMMENT ON COLUMN TRAVEL.REGION is '여행지 이름';
COMMENT ON COLUMN TRAVEL.CONTENT is '여행지 내용';
COMMENT ON COLUMN TRAVEL.REPLY is '한줄평/댓글';
COMMENT ON COLUMN TRAVEL.RECOM is '좋아요/추천수';
COMMENT ON COLUMN TRAVEL.MAP is '지도';
COMMENT ON COLUMN TRAVEL.YOUTUBE is '유튜브 영상';
COMMENT ON COLUMN TRAVEL.FILE1 is '메인이미지';
COMMENT ON COLUMN TRAVEL.FILE1_SAVED is '실제 저장된 이미지';
COMMENT ON COLUMN TRAVEL.THUMB1 is '메인이미지 thumb';


DROP SEQUENCE TRAVEL_SEQ;

CREATE SEQUENCE TRAVEL_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지