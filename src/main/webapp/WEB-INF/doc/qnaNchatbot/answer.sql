/* Table name: ANSWER */
DROP TABLE ANSWER;

CREATE TABLE ANSWER(
    ANSNO       NUMBER(10)      NOT NULL,
    QNANO       NUMBER(10)      NOT NULL,   -- FK
    ANS         VARCHAR(300)    NOT NULL,
    PRIMARY KEY (ANSNO)
);

COMMENT ON TABLE ANSWER is '질문응답';
COMMENT ON COLUMN ANSWER.ANSNO is '답변 번호';
COMMENT ON COLUMN ANSWER.QNANO is '질문 번호';
COMMENT ON COLUMN ANSWER.ANS is '답변 내용';

DROP SEQUENCE ANSWER_SEQ;

CREATE SEQUENCE ANSWER_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지


