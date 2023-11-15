/**********************************/
/* Table Name: 축제/행사 데이터 */
/**********************************/
DROP TABLE FESTIVAL CASCADE CONSTRAINTS; -- 자식 무시하고 삭제 가능

CREATE TABLE FESTIVAL(
		festivalno NUMBER(10) NOT NULL PRIMARY KEY,
		festivalcategoryno NUMBER(10),
		festivaltitle VARCHAR2(100) NOT NULL,
		festivalcontent CLOB NOT NULL,
		festivallikecnt INTEGER,
		festivalcnt NUMBER(30) NOT NULL,
		pwd VARCHAR(15) NOT NULL,
		festivalword VARCHAR2(200),
		festivalrdate DATE NOT NULL,
		festivaludate DATE NOT NULL,
		festivalfile1 VARCHAR2(200),
		festivalfile1saved VARCHAR2(400),
		festivalthumb1 VARCHAR2(200),
		festivalsize1 NUMBER(10),
		festivalmap VARCHAR2(1000),
		festivalyoutube VARCHAR2(1000),
  FOREIGN KEY (festivalcategoryno) REFERENCES FESTIVALCATEGORY (festivalcategoryno)
);

--COMMENT ON TABLE FESTIVAL is '축제/행사 게시물';
COMMENT ON COLUMN FESTIVAL.festivalno IS '축제 게시물 번호';
COMMENT ON COLUMN FESTIVAL.festivalcategoryno IS '축제 카테고리 번호';
COMMENT ON COLUMN FESTIVAL.festivaltitle IS '축제 게시물 제목';
COMMENT ON COLUMN FESTIVAL.festivalcontent IS '축제 게시물 내용';
COMMENT ON COLUMN FESTIVAL.festivallikecnt IS '좋아요 수';
COMMENT ON COLUMN FESTIVAL.festivalcnt IS '축제 게시물 조회수';
COMMENT ON COLUMN FESTIVAL.pwd IS '패스워드';
COMMENT ON COLUMN FESTIVAL.festivalword IS '축제 게시물 검색어';
COMMENT ON COLUMN FESTIVAL.festivalrdate IS '축제 게시물 등록일';
COMMENT ON COLUMN FESTIVAL.festivaludate IS '축제 게시물 수정일';
COMMENT ON COLUMN FESTIVAL.festivalfile1 IS '메인 이미지';
COMMENT ON COLUMN FESTIVAL.festivalfile1saved IS '저장된 메인 이미지';
COMMENT ON COLUMN FESTIVAL.festivalthumb1 IS '메인 이미지 미리보기';
COMMENT ON COLUMN FESTIVAL.festivalsize1 IS '이미지 크기';
COMMENT ON COLUMN FESTIVAL.festivalmap IS '지도';
COMMENT ON COLUMN FESTIVAL.festivalyoutube IS '유튜브';

DROP SEQUENCE festival_seq;

CREATE SEQUENCE festival_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지
  
-- 등록
INSERT INTO festival (
    festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
    festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
    festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1
)
VALUES (
    festival_seq.nextval, 1, '석촌호수의 가을과 겨울, 그리고 루미나리에', '석촌호수의 가을과 겨울, 그리고 루미나리에', 0, 0, '123', '서울, 빛',
    sysdate, sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000
);
INSERT INTO festival (
    festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
    festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
    festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1
)
VALUES (
    festival_seq.nextval, 2, '벽초지수목원 가을꽃 국화축제', '벽초지수목원 가을꽃 국화축제', 0, 0, '123', '파주, 꽃',
    sysdate, sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000
);
INSERT INTO festival (
    festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
    festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
    festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1
)
VALUES (
    festival_seq.nextval, 3, '휴애리 동백 축제', '휴애리 동백 축제', 0, 0, '123', '제주, 제주도, 꽃',
    sysdate, sysdate, 'space.jpg', 'space_1.jpg', 'space_t.jpg', 1000
);
COMMIT;

-- 조회
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent,
       festivallikecnt, festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
       festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube
FROM festival
WHERE festivalcategoryno = 1
ORDER BY festivalno DESC;

-- 일부 삭제
DELETE FROM festival
WHERE festivalno <= 12;
COMMIT;

-- 모든 레코드 삭제
DELETE FROM festival;
COMMIT;

-- 삭제
DELETE FROM festival
WHERE festivalno = 25;
COMMIT;

DELETE FROM festival
WHERE festivalcategoryno = 12 AND festivalno <= 41;
COMMIT;


-- ----------------------------------------------------------------------------------------------------
-- 검색, cateno별 검색 목록
-- ----------------------------------------------------------------------------------------------------
-- 모든글
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
       festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube
FROM FESTIVAL
ORDER BY festivalno ASC;

-- 카테고리별 목록
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
       festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube
FROM FESTIVAL
WHERE festivalcategoryno = 2
ORDER BY festivalno ASC;

-- 1) 검색
-- ① cateno별 검색 목록
-- word 컬럼의 존재 이유: 검색 정확도를 높이기 위하여 중요 단어를 명시
-- 글에 'swiss'라는 단어만 등장하면 한글로 '스위스'는 검색 안됨.
-- 이런 문제를 방지하기위해 'swiss,스위스,스의스,수의스,유럽' 검색어가 들어간 word 컬럼을 추가함.
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
       festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube
FROM FESTIVAL
WHERE festivalcategoryno = 2 AND festivalword LIKE '%꽃%'
ORDER BY festivalno DESC;

-- title, content, word column search
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
       festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube
FROM FESTIVAL
WHERE festivalcategoryno = 2 AND (festivaltitle LIKE '%꽃%' OR festivalcontent LIKE '%꽃%' OR festivalword LIKE '%꽃%')
ORDER BY festivalno DESC;

-- ② 검색 레코드 갯수
-- 전체 레코드 갯수, 집계 함수
SELECT COUNT(*)
FROM FESTIVAL
WHERE festivalcategoryno = 2;

SELECT COUNT(*) as cnt -- 함수 사용시는 컬럼 별명을 선언하는 것을 권장
FROM FESTIVAL
WHERE festivalcategoryno = 2;

-- cateno 별 검색된 레코드 갯수
SELECT COUNT(*) as cnt
FROM FESTIVAL
WHERE festivalcategoryno = 2 AND festivalword LIKE '%꽃%';

SELECT COUNT(*) as cnt
FROM FESTIVAL
WHERE festivalcategoryno = 2 AND (festivaltitle LIKE '%꽃%' OR festivalcontent LIKE '%꽃%' OR festivalword LIKE '%꽃%');

-- SUBSTR(컬럼명, 시작 index(1부터 시작), 길이), 부분 문자열 추출
SELECT festivalno, SUBSTR(festivaltitle, 1, 4) as festivaltitle
FROM FESTIVAL
WHERE festivalcategoryno = 2 AND (festivalcontent LIKE '%꽃%');

-- SQL은 대소문자를 구분하지 않으나 WHERE문에 명시하는 값은 대소문자를 구분하여 검색
SELECT festivalno, festivaltitle, festivalword
FROM FESTIVAL
WHERE festivalcategoryno = 1 AND (festivalword LIKE '%FOOD%');

SELECT festivalno, festivaltitle, festivalword
FROM FESTIVAL
WHERE festivalcategoryno = 1 AND (festivalword LIKE '%food%'); 

SELECT festivalno, festivaltitle, festivalword
FROM FESTIVAL
WHERE festivalcategoryno = 1 AND (LOWER(festivalword) LIKE '%food%'); -- 대소문자를 일치 시켜서 검색

-- ||: 문자열 연결
-- LIKE '%' || UPPER('FOOD') || '%' -> LIKE '%FOOD%'
SELECT festivalno, festivaltitle, festivalword
FROM FESTIVAL
WHERE festivalcategoryno = 1 AND (UPPER(festivalword) LIKE '%' || UPPER('FOOD') || '%'); -- 대소문자를 일치 시켜서 검색 ★

SELECT festivalno, festivaltitle, festivalword
FROM FESTIVAL
WHERE festivalcategoryno = 1 AND (LOWER(festivalword) LIKE '%' || LOWER('Food') || '%'); -- 대소문자를 일치 시켜서 검색

SELECT festivalno || '. ' || festivaltitle || ' 태그: ' || festivalword as festivaltitle -- 컬럼의 결합, ||
FROM FESTIVAL
WHERE festivalcategoryno = 1 AND (LOWER(festivalword) LIKE '%' || LOWER('Food') || '%'); -- 대소문자를 일치 시켜서 검색

-- ----------------------------------------------------------------------------------------------------
-- 검색 + 페이징 + 메인 이미지
-- ----------------------------------------------------------------------------------------------------
-- step 1
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
       festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube
FROM FESTIVAL
WHERE festivalcategoryno = 2 AND (festivaltitle LIKE '%꽃%' OR festivalcontent LIKE '%꽃%' OR festivalword LIKE '%꽃%')
ORDER BY festivalno DESC;

-- step 2
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
       festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube, rownum as r
FROM (
          SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
                 festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
                 festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube
          FROM FESTIVAL
          WHERE festivalcategoryno = 2 AND (festivaltitle LIKE '%꽃%' OR festivalcontent LIKE '%꽃%' OR festivalword LIKE '%꽃%')
          ORDER BY festivalno DESC
);

-- step 3, 1 page
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
       festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube, r
FROM (
           SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
                  festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
                  festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube, rownum as r
           FROM (
                     SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
                            festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
                            festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube
                     FROM FESTIVAL
                     WHERE festivalcategoryno = 2 AND (UPPER(festivaltitle) LIKE '%' || UPPER('꽃') || '%' 
                                                      OR UPPER(festivalcontent) LIKE '%' || UPPER('꽃') || '%' 
                                                      OR UPPER(festivalword) LIKE '%' || UPPER('꽃') || '%')
                     ORDER BY festivalno DESC
           )          
)
WHERE r >= 1 AND r <= 3;

-- step 3, 2 page
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
       festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube, r
FROM (
           SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
                  festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
                  festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube, rownum as r
           FROM (
                     SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
                            festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
                            festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube
                     FROM FESTIVAL
                     WHERE festivalcategoryno = 2 AND (UPPER(festivaltitle) LIKE '%' || UPPER('꽃') || '%' 
                                                      OR UPPER(festivalcontent) LIKE '%' || UPPER('꽃') || '%' 
                                                      OR UPPER(festivalword) LIKE '%' || UPPER('꽃') || '%')
                     ORDER BY festivalno DESC
           )          
)
WHERE r >= 4 AND r <= 6;

-- 대소문자를 처리하는 페이징 쿼리
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
       festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube, r
FROM (
           SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
                  festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
                  festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube, rownum as r
           FROM (
                     SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
                            festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
                            festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube
                     FROM FESTIVAL
                     WHERE festivalcategoryno = 2 AND (UPPER(festivaltitle) LIKE '%' || UPPER('꽃') || '%' 
                                                      OR UPPER(festivalcontent) LIKE '%' || UPPER('꽃') || '%' 
                                                      OR UPPER(festivalword) LIKE '%' || UPPER('꽃') || '%')
                     ORDER BY festivalno DESC
           )          
)
WHERE r >= 7 AND r <= 9;

-- ----------------------------------------------------------------------------
-- 조회
-- ----------------------------------------------------------------------------
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt,
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate,
       festivalfile1, festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube
FROM FESTIVAL
WHERE festivalno = 1;

-- ----------------------------------------------------------------------------
-- 다음 지도, MAP, 먼저 레코드가 등록되어 있어야 함.
-- map VARCHAR2(1000) NULL ,
-- ----------------------------------------------------------------------------
-- MAP 등록/수정
UPDATE FESTIVAL SET festivalmap='행사 주소 스크립트' WHERE festivalno=1;

-- MAP 삭제
UPDATE FESTIVAL SET festivalmap='' WHERE festivalno=1;

commit;

-- ----------------------------------------------------------------------------
-- Youtube, 먼저 레코드가 등록되어 있어야 함.
-- youtube VARCHAR2(1000) NULL ,
-- ----------------------------------------------------------------------------
-- youtube 등록/수정
UPDATE FESTIVAL SET festivalyoutube='Youtube 스크립트' WHERE festivalno=1;

-- youtube 삭제
UPDATE FESTIVAL SET festivalyoutube='' WHERE festivalno=1;

commit;

-- 패스워드 검사, id="password_check"
SELECT COUNT(*) as cnt 
FROM FESTIVAL
WHERE festivalno=1 AND pwd='123';

-- ----------------------------------------------------------------------------
-- 글 수정
-- ----------------------------------------------------------------------------
-- 텍스트 수정: 예외 컬럼: 좋아요 수, 조회수, 댓글 수
UPDATE FESTIVAL
SET festivaltitle='[파주]벽초지수목원 가을꽃 국화축제', festivalcontent='경기도 파주시',  festivalword='경기도, 파주 ,꽃, 가을' 
WHERE festivalno = 2;

commit;

-- ----------------------------------------------------------------------------
-- 파일 수정
-- file1: 메인 이미지, 원본 파일명
-- file1saved: 실제 저장된 파일명
-- thumb1: 메인 이미지 Preview
-- size1: 메인 이미지 크기, 파일 크기
-- ----------------------------------------------------------------------------
UPDATE festival
SET festivalfile1='pajuflower01.jpg', festivalfile1saved='pajuflower01.jpg', festivalthumb1='pajuflower01.jpg', festivalsize1=5000
WHERE festivalno = 2;

-- 삭제
DELETE FROM festival
WHERE festivalno = 5;

commit;

DELETE FROM festival
WHERE festivalno >= 7;

commit;

-- 추천
UPDATE festival
SET festivallikecnt = festivallikecnt + 1
WHERE festivalno = 1;

-- festivalcategoryno FK 특정 그룹에 속한 레코드 갯수 산출
SELECT COUNT(*) as cnt 
FROM festival 
WHERE festivalcategoryno=7;

-- festivalcategoryno FK 특정 그룹에 속한 레코드 모두 삭제
DELETE FROM festival
WHERE festivalcategoryno=7;

commit;

-- 다수의 카테고리에 속한 레코드 갯수 산출: IN
SELECT COUNT(*) as cnt
FROM festival
WHERE festivalcategoryno IN(1,2,3);

-- 다수의 카테고리에 속한 레코드 모두 SELECT
SELECT festivalno, festivalcategoryno, festivaltitle
FROM festival
WHERE festivalcategoryno IN(1,2,3);

-- FK 컬럼별 삭제: 1번 카테고리 관련글 모두 삭제 
DELETE FROM festival
WHERE festivalcategoryno=5;

commit;

-- ----------------------------------------------------------------------------------------------------
-- cate + festival INNER JOIN
-- ----------------------------------------------------------------------------------------------------
-- 모든 글
SELECT c.catename,
       f.festivalno, f.festivalcategoryno, f.festivaltitle, f.festivalcontent, f.festivallikecnt, 
       f.festivalcnt, f.pwd, f.festivalword, f.festivalrdate, f.festivaludate, f.festivalfile1, 
       f.festivalfile1saved, f.festivalthumb1, f.festivalsize1, f.festivalmap, f.festivalyoutube
FROM festivalCATEGORY c
JOIN festival f ON c.festivalcategoryno = f.festivalcategoryno
ORDER BY f.festivalno DESC;

-- ----------------------------------------------------------------------------------------------------
-- View + paging
-- ----------------------------------------------------------------------------------------------------
CREATE OR REPLACE VIEW vfestival
AS
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalfile1, 
       festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube
FROM festival
ORDER BY festivalno DESC;

-- 1 page
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalfile1, 
       festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube, r
FROM (
     SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
            festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalfile1, 
            festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube, 
            rownum as r
     FROM vfestival -- View
     WHERE festivalcategoryno=14 AND (festivaltitle LIKE '%꽃%' OR festivalcontent LIKE '%꽃%' OR festivalword LIKE '%꽃%')
)
WHERE r >= 1 AND r <= 3;

-- 2 page
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalfile1, 
       festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube, r
FROM (
     SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
            festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalfile1, 
            festivalfile1saved, festivalthumb1, festivalsize1, festivalmap, festivalyoutube, 
            rownum as r
     FROM vfestival -- View
     WHERE festivalcategoryno=14 AND (festivaltitle LIKE '%꽃%' OR festivalcontent LIKE '%꽃%' OR festivalword LIKE '%꽃%')
)
WHERE r >= 4 AND r <= 6;

-- ----------------------------------------------------------------------------------------------------
-- 관심 카테고리의 좋아요(recom) 기준, 1번 회원이 1번 카테고리를 추천 받는 경우, 추천 상품이 7건일 경우
-- ----------------------------------------------------------------------------------------------------
SELECT festivalno, festivalcategoryno, festivaltitle, festivalthumb1, r
FROM (
           SELECT festivalno, festivalcategoryno, festivaltitle, festivalthumb1, rownum as r
           FROM (
                     SELECT festivalno, festivalcategoryno, festivaltitle, festivalthumb1
                     FROM festival
                     WHERE festivalcategoryno=1
                     ORDER BY festivallikecnt DESC
           )          
)
WHERE r >= 1 AND r <= 7;

-- ----------------------------------------------------------------------------------------------------
-- 관심 카테고리의 평점(score) 기준, 1번 회원이 1번 카테고리를 추천 받는 경우, 추천 상품이 7건일 경우
-- ----------------------------------------------------------------------------------------------------
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
       festivalmap, festivalyoutube, r
FROM (
           SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
                  festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
                  festivalmap, festivalyoutube, rownum as r
           FROM (
                     SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
                            festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
                            festivalmap, festivalyoutube
                     FROM festival
                     WHERE festivalcategoryno=1
                     ORDER BY festivalscore DESC
           )          
)
WHERE r >= 1 AND r <= 7;

-- ----------------------------------------------------------------------------------------------------
-- 관심 카테고리의 최신 상품 기준, 1번 회원이 1번 카테고리를 추천 받는 경우, 추천 상품이 7건일 경우
-- ----------------------------------------------------------------------------------------------------
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
       festivalmap, festivalyoutube, r
FROM (
           SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
                  festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
                  festivalmap, festivalyoutube, rownum as r
           FROM (
                     SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
                            festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
                            festivalmap, festivalyoutube
                     FROM FESTIVAL
                     WHERE festivalcategoryno=1
                     ORDER BY festivalrdate DESC
           )          
)
WHERE r >= 1 AND r <= 7;

-- ----------------------------------------------------------------------------------------------------
-- 관심 카테고리의 조회수 높은 상품기준, 1번 회원이 1번 카테고리를 추천 받는 경우, 추천 상품이 7건일 경우
-- ----------------------------------------------------------------------------------------------------
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
       festivalmap, festivalyoutube, r
FROM (
           SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
                  festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
                  festivalmap, festivalyoutube, rownum as r
           FROM (
                     SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
                            festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
                            festivalmap, festivalyoutube
                     FROM FESTIVAL
                     WHERE festivalcategoryno=1
                     ORDER BY festivalcnt DESC
           )          
)
WHERE r >= 1 AND r <= 7;

-- ----------------------------------------------------------------------------------------------------
-- 관심 카테고리의 낮은 가격 상품 추천, 1번 회원이 1번 카테고리를 추천 받는 경우, 추천 상품이 7건일 경우
-- ----------------------------------------------------------------------------------------------------
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
       festivalmap, festivalyoutube, r
FROM (
           SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
                  festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
                  festivalmap, festivalyoutube, rownum as r
           FROM (
                     SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
                            festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
                            festivalmap, festivalyoutube
                     FROM FESTIVAL
                     WHERE festivalcategoryno=1
                     ORDER BY festivalprice ASC
           )          
)
WHERE r >= 1 AND r <= 7;

-- ----------------------------------------------------------------------------------------------------
-- 관심 카테고리의 높은 가격 상품 추천, 1번 회원이 1번 카테고리를 추천 받는 경우, 추천 상품이 7건일 경우
-- ----------------------------------------------------------------------------------------------------
SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
       festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
       festivalmap, festivalyoutube, r
FROM (
           SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
                  festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
                  festivalmap, festivalyoutube, rownum as r
           FROM (
                     SELECT festivalno, festivalcategoryno, festivaltitle, festivalcontent, festivallikecnt, 
                            festivalcnt, pwd, festivalword, festivalrdate, festivaludate, festivalthumb1, festivalsize1, 
                            festivalmap, festivalyoutube
                     FROM FESTIVAL
                     WHERE festivalcategoryno=1
                     ORDER BY festivalprice DESC
           )          
)
WHERE r >= 1 AND r <= 7;





  
