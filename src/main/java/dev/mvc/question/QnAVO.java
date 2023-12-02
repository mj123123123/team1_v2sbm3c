package dev.mvc.question;

/*
 * CREATE TABLE QUESTION(
    QUESTNO     NUMBER(10)      NOT NULL,
    MEMBERNO    NUMBER(10)      NOT NULL,   -- FK
    TCATENO     NUMBER(10)      NOT NULL,   -- FK
    TITLE       VARCHAR(50)     NOT NULL,
    QUEST       VARCHAR(300)    NOT NULL,
    RDATE       DATE            NOT NULL,
    PRIMARY KEY (QUESTNO)
);
 */

public class QnAVO {
  private int questno;
  private int memberno;
  private int tcateno;
  private String title = "";
  private String quest = "";
  private String rdate = "";
  
  public int getQuestno() {
    return questno;
  }
  public void setQuestno(int questno) {
    this.questno = questno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getTcateno() {
    return tcateno;
  }
  public void setTcateno(int tcateno) {
    this.tcateno = tcateno;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getQuest() {
    return quest;
  }
  public void setQuest(String quest) {
    this.quest = quest;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  
  @Override
  public String toString() {
    return "QnAVO [questno=" + questno + ", memberno=" + memberno + ", tcateno=" + tcateno + ", title=" + title
        + ", quest=" + quest + ", rdate=" + rdate + "]";
  }
}
