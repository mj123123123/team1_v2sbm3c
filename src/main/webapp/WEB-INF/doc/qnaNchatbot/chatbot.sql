/* Table name: CHATBOT */
DROP TABLE CHATBOT;

CREATE TABLE CHATBOT(
    CHATBOTNO       NUMBER(10)      NOT NULL,
    MEMBERNO        NUMBER(10)      NOT NULL,   -- FK
    PRIMARY KEY (CHATBOTNO)
);

COMMENT ON TABLE CHATBOT is '응답';
COMMENT ON COLUMN CHATBOT.CHATBOTNO is '챗봇 번호';
COMMENT ON COLUMN CHATBOT.MEMBERNO is '회원 번호';

DROP SEQUENCE CHATBOT_SEQ;

CREATE SEQUENCE CHATBOT_SEQ
  START WITH 1              -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 99999            -- 최대값: 99999 --> NUMBER(5) 대응
  CACHE 2                   -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지
