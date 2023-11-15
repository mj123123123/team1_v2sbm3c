/**********************************/
/* Table Name: 축제/행사 카테고리 */
/**********************************/
DROP TABLE FESTIVALCATEGORY;

CREATE TABLE FESTIVALCATEGORY(
		festivalcategoryno    NUMBER(10)    NOT NULL   PRIMARY KEY,
		festivalcategoryname  VARCHAR2(25)  NOT NULL,
		festivalcategorycnt   NUMBER(10)    NOT NULL,
		festivalcategoryrdate DATE          NOT NULL,
        SEQNO                 NUMBER(5),
        VISIBLE               CHAR(1)
);

--COMMENT ON TABLE CATE is '축제/행사 카테고리';
COMMENT ON COLUMN FESTIVALCATEGORY.festivalcategoryno is '축제 카테고리 번호';
COMMENT ON COLUMN FESTIVALCATEGORY.festivalcategoryname is '축제 카테고리 이름';
COMMENT ON COLUMN FESTIVALCATEGORY.festivalcategorycnt is '축제 관련 자료수';
COMMENT ON COLUMN FESTIVALCATEGORY.festivalcategoryrdate is '축제 카테고리 등록일';
COMMENT ON COLUMN FESTIVALCATEGORY.SEQNO is '출력 순서';
COMMENT ON COLUMN FESTIVALCATEGORY.VISIBLE is '출력 모드';

DROP SEQUENCE FESTIVALCATEGORY_SEQ;

CREATE SEQUENCE FESTIVALCATEGORY_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지

-- CREATE
INSERT INTO festivalcategory(festivalcategoryno, festivalcategoryname, festivalcategorycnt, festivalcategoryrdate) 
VALUES (festivalcategory_seq.nextval, '월별', 0, sysdate);
INSERT INTO festivalcategory(festivalcategoryno, festivalcategoryname, festivalcategorycnt, festivalcategoryrdate) 
VALUES (festivalcategory_seq.nextval, '수정 테스트', 0, sysdate);
INSERT INTO festivalcategory(festivalcategoryno, festivalcategoryname, festivalcategorycnt, festivalcategoryrdate) 
VALUES (festivalcategory_seq.nextval, '테마별', 0, sysdate);
INSERT INTO festivalcategory(festivalcategoryno, festivalcategoryname, festivalcategorycnt, festivalcategoryrdate) 
VALUES (festivalcategory_seq.nextval, '삭제 테스트', 0, sysdate);
commit;

-- READ: List
SELECT * FROM festivalcategory;

-- READ   
SELECT festivalcategoryno, festivalcategoryname, festivalcategorycnt, festivalcategoryrdate
FROM festivalcategory
WHERE festivalcategoryno=1;

-- UPDATE
UPDATE festivalcategory
SET festivalcategoryname='지역별', festivalcategorycnt='0' 
WHERE festivalcategoryno=2;

COMMIT;

-- DELETE
DELETE FROM festivalcategory WHERE festivalcategoryno=4;

COMMIT;

-- COUNT
SELECT COUNT(*) as cnt FROM festivalcategory;

-- 우선 순위 높임, 10 등 -> 1 등
UPDATE festivalcategory SET seqno = seqno - 1 WHERE festivalcategoryno=1;
SELECT festivalcategoryno, festivalcategoryname, festivalcategorycnt, festivalcategoryrdate, seqno 
FROM festivalcategory 
ORDER BY festivalcategoryno ASC;

-- 우선 순위 낮춤, 1 등 -> 10 등
UPDATE festivalcategory SET seqno = seqno + 1 WHERE festivalcategoryno=1;
SELECT festivalcategoryno, festivalcategoryname, festivalcategorycnt, festivalcategoryrdate, seqno 
FROM festivalcategory 
ORDER BY festivalcategoryno ASC;

-- READ: LIST
SELECT festivalcategoryno, festivalcategoryname, festivalcategorycnt, festivalcategoryrdate, seqno 
FROM festivalcategory ORDER BY seqno ASC;

COMMIT;

-- 카테고리 공개 설정
UPDATE festivalcategory SET visible='Y' WHERE festivalcategoryno=1;
SELECT festivalcategoryno, festivalcategoryname, festivalcategorycnt, festivalcategoryrdate, seqno, visible 
FROM festivalcategory ORDER BY festivalcategoryno ASC;

-- 카테고리 비공개 설정
UPDATE festivalcategory SET visible='N' WHERE festivalcategoryno=1;
SELECT festivalcategoryno, festivalcategoryname, festivalcategorycnt, festivalcategoryrdate, seqno, visible 
FROM festivalcategory ORDER BY festivalcategoryno ASC;

COMMIT;

-- 비회원/회원 SELECT LIST, id: list_all_y
SELECT festivalcategoryno, festivalcategoryname, festivalcategorycnt, festivalcategoryrdate, seqno, visible
FROM festivalcategory 
WHERE visible='N'
ORDER BY seqno ASC;
