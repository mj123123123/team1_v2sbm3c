/* Table name: QNA */
DROP TABLE QNA;

CREATE TABLE QNA(
    QNANO       NUMBER(10)      NOT NULL,
    MEMBERNO    NUMBER(10)      NOT NULL,   -- FK
    CATENO      NUMBER(10)      NOT NULL,   -- FK
    QUESTION    VARCHAR(300)    NOT NULL,
    PRIMARY KEY (QNANO)
);

COMMENT ON TABLE QNA is '질문답변';
COMMENT ON COLUMN QNA.QNANO is '질문 번호';
COMMENT ON COLUMN QNA.MEMBERNO is '회원 번호';
COMMENT ON COLUMN QNA.CATENO is '카테고리 번호';
COMMENT ON COLUMN QNA.QUESTION is '질문내용';

DROP SEQUENCE QNA_SEQ;

CREATE SEQUENCE QNA_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지


