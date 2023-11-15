/**********************************/
/* Table Name: 축제/행사 리뷰 */
/**********************************/
CREATE TABLE REVIEW(
		reviewno NUMBER(10) NOT NULL PRIMARY KEY,
		festivalno NUMBER(10),
		MEMBERNO NUMERIC(10),
		reviewtitle VARCHAR2(100) NOT NULL,
		reviewcontent CLOB NOT NULL,
		reviewcnt NUMBER(10),
		reviewreplycnt NUMBER(10),
		reviewpwd VARCHAR(15) NOT NULL,
		reviewword VARCHAR2(200),
		reviewrdate DATE NOT NULL,
		reviewudate DATE NOT NULL,
		reviewfile1 VARCHAR2(200),
		reviewfile1saved VARCHAR2(400),
		reviewthumb1 VARCHAR2(200),
		reviewsize1 NUMBER(10),
		reviewmap INTEGER(10),
  FOREIGN KEY (festivalno) REFERENCES FESTIVAL (festivalno),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

-- COMMENT ON TABLE REVIEW IS '축제/행사 리뷰';
COMMENT ON COLUMN REVIEW.reviewno IS '리뷰 번호';
COMMENT ON COLUMN REVIEW.festivalno IS '축제 번호';
COMMENT ON COLUMN REVIEW.MEMBERNO IS '회원 번호';
COMMENT ON COLUMN REVIEW.reviewtitle IS '리뷰 제목';
COMMENT ON COLUMN REVIEW.reviewcontent IS '리뷰 내용';
COMMENT ON COLUMN REVIEW.reviewcnt IS '리뷰 조회수';
COMMENT ON COLUMN REVIEW.reviewreplycnt IS '리뷰 댓글 수';
COMMENT ON COLUMN REVIEW.reviewpwd IS '리뷰 비밀번호';
COMMENT ON COLUMN REVIEW.reviewword IS '리뷰 태그';
COMMENT ON COLUMN REVIEW.reviewrdate IS '리뷰 등록일';
COMMENT ON COLUMN REVIEW.reviewudate IS '리뷰 수정일';
COMMENT ON COLUMN REVIEW.reviewfile1 IS '리뷰 첨부 파일명';
COMMENT ON COLUMN REVIEW.reviewfile1saved IS '리뷰 첨부 파일 저장명';
COMMENT ON COLUMN REVIEW.reviewthumb1 IS '리뷰 첨부 파일 썸네일';
COMMENT ON COLUMN REVIEW.reviewsize1 IS '리뷰 첨부 파일 크기';
COMMENT ON COLUMN REVIEW.reviewmap IS '리뷰 지도';

DROP SEQUENCE review_seq;

CREATE SEQUENCE review_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지
  
  
INSERT INTO review (
    reviewno, festivalno, MEMBERNO, reviewtitle, reviewcontent, reviewpwd, reviewword,
    reviewfile1, reviewfile1saved, reviewthumb1, reviewsize1, reviewmap
) VALUES (
    1, 1001, 101, '좋은 축제', '축제가 정말 좋았어요!', '1234', '축제,좋아요',
    'festival_image.jpg', 'saved_image.jpg', 'thumbnail.jpg', 5000, 1
);  

UPDATE review
SET reviewtitle = '수정된 제목', reviewcontent = '축제 리뷰 내용 수정'
WHERE reviewno = 1;
  
DELETE FROM review
WHERE reviewno = 1;  


