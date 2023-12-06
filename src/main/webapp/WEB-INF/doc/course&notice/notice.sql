/**********************************/
/* Table Name: 공지사항 */
/**********************************/
DROP TABLE NOTICE;

CREATE TABLE NOTICE(
		NOTICENO                        		NUMBER(10)		 NOT NULL PRIMARY KEY,
		TITLE                          		VARCHAR2(30)	 NOT NULL,
		CONTENT                           		NUMBER(7)		 DEFAULT 0 NOT NULL,
		DATE                         		DATE		     NOT NULL,
        CHECK
        SEQNO                               NUMBER(5)        DEFAULT 1 NOT NULL,
        VISIBLE                             CHAR(1)          DEFAULT 'N' NOT NULL  
);

COMMENT ON TABLE NOTICE is '공지사항';
COMMENT ON COLUMN NOTICE.NOTICENO is '공지사항 번호';

COMMENT ON COLUMN NOTICE.CONTENT is '공지사항 내용';
COMMENT ON COLUMN NOTICE.DATE is '공지사항 등록일';
COMMENT ON COLUMN NOTICE.RDATE is '공지사항 조회수';
COMMENT ON COLUMN NOTICE.SEQNO is '관리자 번호';
COMMENT ON COLUMN NOTICE.VISIBLE is '출력 모드';

DROP SEQUENCE NOTICE_SEQ;

CREATE SEQUENCE NOTICE_SEQ
  START WITH 1         -- 시작 번호
  INCREMENT BY 1       -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2              -- 2번은 메모리에서만 계산
  NOCYCLE;             -- 다시 1부터 생성되는 것을 방지
  
-- CREATE
INSERT INTO notice(noticeno, name, cnt, rdate) VALUES(notice_seq.nextval, '경기도', 0, sysdate); 
INSERT INTO notice(noticeno, name, cnt, rdate) VALUES(notice_seq.nextval, '강원도', 0, sysdate); 
INSERT INTO notice(noticeno, name, cnt, rdate) VALUES(notice_seq.nextval, '충청남도', 0, sysdate); 

-- READ: LIST
SELECT * FROM notice;
SELECT noticeno, name, cnt, rdate, seqno, visible FROM notice ORDER BY noticeno ASC;
    NOTICENO NAME                                  CNT RDATE                    SEQNO V
---------- ------------------------------ ---------- ------------------- ---------- -
         1 경기도                                  0 2023-09-13 12:18:46          1 N
         2 강원도                                  0 2023-09-13 12:18:46          1 N
         3 충청남도                                0 2023-09-13 12:18:46          1 N

-- READ
SELECT noticeno, name, cnt, rdate FROM notice WHERE noticeno=1;
    NOTICENO NAME                                  CNT RDATE              
---------- ------------------------------ ---------- -------------------
         1 경기도                                  0 2023-09-06 12:09:45
         
-- UPDATE
UPDATE notice SET name='전라도', cnt=1 WHERE noticeno=1;
    NOTICENO NAME                                  CNT RDATE              
---------- ------------------------------ ---------- -------------------
         1 전라도                                  1 2023-09-06 12:09:45

-- DELETE
DELETE FROM notice WHERE noticeno=1;
DELETE FROM notice WHERE noticeno >= 1;

COMMIT;

-- COUNT
SELECT COUNT(*) as cnt FROM notice;
       CNT
----------
         2

-- 우선 순위 높임, 10 등 -> 1 등
UPDATE notice SET seqno = seqno - 1 WHERE noticeno=1;
SELECT noticeno, name, cnt, rdate, seqno FROM notice ORDER BY noticeno ASC;

-- 우선 순위 낮춤, 1 등 -> 10 등
UPDATE notice SET seqno = seqno + 1 WHERE noticeno=1;
SELECT noticeno, name, cnt, rdate, seqno FROM notice ORDER BY noticeno ASC;

-- READ: LIST
SELECT noticeno, name, cnt, rdate, seqno FROM notice ORDER BY seqno ASC;

COMMIT;

-- 카테고리 공개 설정
UPDATE notice SET visible='Y' WHERE noticeno=1;
SELECT noticeno, name, cnt, rdate, seqno, visible FROM notice ORDER BY noticeno ASC;

-- 카테고리 비공개 설정
UPDATE notice SET visible='N' WHERE noticeno=1;
SELECT noticeno, name, cnt, rdate, seqno, visible FROM notice ORDER BY noticeno ASC;

COMMIT;

-- 비회원/회원 SELECT LIST, id: list_all_y
SELECT noticeno, name, cnt, rdate, seqno, visible 
FROM notice 
WHERE visible='Y'
ORDER BY seqno ASC;

    NOTICENO NAME                                  CNT RDATE                    SEQNO V
---------- ------------------------------ ---------- ------------------- ---------- -
         2 강원도                                  0 2023-09-13 12:18:46          1 Y


         






