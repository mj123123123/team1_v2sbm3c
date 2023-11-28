/* Table name: QNA */
DROP TABLE QNA;

CREATE TABLE QNA(
    QNANO       NUMBER(10)      NOT NULL,
    MEMBERNO    NUMBER(10)      NOT NULL,   -- FK
    CATEGORYNO  NUMBER(10)      NOT NULL,   -- FK
    TITLE       VARCHAR(50)     NOT NULL,
    QUEST       VARCHAR(300)    NOT NULL,
    RDATE       DATE            NOT NULL,
    PRIMARY KEY (QNANO)
);

COMMENT ON TABLE QNA is '질문';
COMMENT ON COLUMN QNA.QNANO is '질문 번호';
COMMENT ON COLUMN QNA.MEMBERNO is '회원 번호';
COMMENT ON COLUMN QNA.CATEGORYNO is '여행 카테고리 번호';
COMMENT ON COLUMN QNA.TITLE is '제목';
COMMENT ON COLUMN QNA.QUEST is '질문내용';
COMMENT ON COLUMN QNA.RDATE is '등록일';

DROP SEQUENCE QNA_SEQ;

CREATE SEQUENCE QNA_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지


commit;


-- INSERT
INSERT INTO qna(qnano, memberno, categoryno, title, quest, rdate) VALUES (qna_seq.nextval, 1, 1, '제목', '내용', sysdate);


-- SELECT
SELECT qnano, memberno, categoryno, title, quest, rdate FROM qna ORDER BY qnano DESC;
SELECT qnano, memberno, categoryno, title, quest, rdate FROM qna WHERE categoryno = 2 ORDER BY qnano DESC;
SELECT qnano, memberno, categoryno, title, quest, rdate FROM qna WHERE categoryno = 2 AND quest LIKE '%결제%' ORDER BY qnano DESC;

-- DELETE
-- DELETE FROM qna WHERE qnano = 1;
-- DELETE FROM qna;
-- commit;


-- COUNT
SELECT COUNT(*) as cnt FROM qna WHERE categoryno = 1;
SELECT COUNT(*) as cnt FROM qna WHERE categoryno = 1 AND quest LIKE '%결제%';


-- UPDATE
-- UPDATE qna SET title = '결제 방법', quest = '결제는 어떻게 하나요' WHERE qnano = 1;


-- commit;
















