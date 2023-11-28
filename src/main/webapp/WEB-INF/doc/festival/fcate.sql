/**********************************/
/* Table Name: 축제/행사 카테고리 */
/**********************************/
DROP TABLE festivalcate;

CREATE TABLE festivalcate(
		festivalcateno    NUMBER(10)    NOT NULL   PRIMARY KEY,
		festivalcatename  VARCHAR2(25)  NOT NULL,
		festivalcatecnt   NUMBER(10)    NOT NULL,
		festivalcaterdate DATE          NOT NULL,
        SEQNO                 NUMBER(5),
        VISIBLE               CHAR(1)
);

--COMMENT ON TABLE CATE is '축제/행사 카테고리';
COMMENT ON COLUMN festivalcate.festivalcateno is '축제 카테고리 번호';
COMMENT ON COLUMN festivalcate.festivalcatename is '축제 카테고리 이름';
COMMENT ON COLUMN festivalcate.festivalcatecnt is '축제 관련 자료수';
COMMENT ON COLUMN festivalcate.festivalcaterdate is '축제 카테고리 등록일';
COMMENT ON COLUMN festivalcate.SEQNO is '출력 순서';
COMMENT ON COLUMN festivalcate.VISIBLE is '출력 모드';

DROP SEQUENCE festivalcate_SEQ;

CREATE SEQUENCE festivalcate_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지

-- CREATE
INSERT INTO festivalcate(festivalcateno, festivalcatename, festivalcatecnt, festivalcaterdate) 
VALUES (festivalcate_seq.nextval, '월별', 0, sysdate);
INSERT INTO festivalcate(festivalcateno, festivalcatename, festivalcatecnt, festivalcaterdate) 
VALUES (festivalcate_seq.nextval, '수정 테스트', 0, sysdate);
INSERT INTO festivalcate(festivalcateno, festivalcatename, festivalcatecnt, festivalcaterdate) 
VALUES (festivalcate_seq.nextval, '테마별', 0, sysdate);
INSERT INTO festivalcate(festivalcateno, festivalcatename, festivalcatecnt, festivalcaterdate) 
VALUES (festivalcate_seq.nextval, '삭제 테스트', 0, sysdate);
commit;

-- READ: List
SELECT * FROM festivalcate;

-- READ   
SELECT festivalcateno, festivalcatename, festivalcatecnt, festivalcaterdate
FROM festivalcate
WHERE festivalcateno=1;

-- UPDATE
UPDATE festivalcate
SET festivalcatename='지역별', festivalcatecnt='0' 
WHERE festivalcateno=2;

COMMIT;

-- DELETE
DELETE FROM festivalcate WHERE festivalcateno=4;

COMMIT;

-- COUNT
SELECT COUNT(*) as cnt FROM festivalcate;

-- 우선 순위 높임, 10 등 -> 1 등
UPDATE festivalcate SET seqno = seqno - 1 WHERE festivalcateno=1;
SELECT festivalcateno, festivalcatename, festivalcatecnt, festivalcaterdate, seqno 
FROM festivalcate 
ORDER BY festivalcateno ASC;

-- 우선 순위 낮춤, 1 등 -> 10 등
UPDATE festivalcate SET seqno = seqno + 1 WHERE festivalcateno=1;
SELECT festivalcateno, festivalcatename, festivalcatecnt, festivalcaterdate, seqno 
FROM festivalcate 
ORDER BY festivalcateno ASC;

-- READ: LIST
SELECT festivalcateno, festivalcatename, festivalcatecnt, festivalcaterdate, seqno 
FROM festivalcate ORDER BY seqno ASC;

COMMIT;

-- 카테고리 공개 설정
UPDATE festivalcate SET visible='Y' WHERE festivalcateno=1;
SELECT festivalcateno, festivalcatename, festivalcatecnt, festivalcaterdate, seqno, visible 
FROM festivalcate ORDER BY festivalcateno ASC;

-- 카테고리 비공개 설정
UPDATE festivalcate SET visible='N' WHERE festivalcateno=1;
SELECT festivalcateno, festivalcatename, festivalcatecnt, festivalcaterdate, seqno, visible 
FROM festivalcate ORDER BY festivalcateno ASC;

COMMIT;

-- 비회원/회원 SELECT LIST, id: list_all_y
SELECT festivalcateno, festivalcatename, festivalcatecnt, festivalcaterdate, seqno, visible
FROM festivalcate 
WHERE visible='N'
ORDER BY seqno ASC;
