package dev.mvc.qna;

/*
 * CREATE TABLE QNA(
    QNANO       NUMBER(10)      NOT NULL,
    MEMBERNO    NUMBER(10)      NOT NULL,   -- FK
    CATEGORYNO  NUMBER(10)      NOT NULL,   -- FK
    TITLE       VARCHAR(50)     NOT NULL,
    QUEST       VARCHAR(300)    NOT NULL,
    RDATE       DATE            NOT NULL,
    PRIMARY KEY (QNANO)
    );
 */

public class QnAVO {
  private int qnano;
  private int memberno;
  private int categoryno;
  private String title = "";
  private String quest = "";
  private String rdate = "";
  
  public int getQnano() {
    return qnano;
  }
  public void setQnano(int qnano) {
    this.qnano = qnano;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getCategoryno() {
    return categoryno;
  }
  public void setCategoryno(int categoryno) {
    this.categoryno = categoryno;
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
    return "QnAVO [qnano=" + qnano + ", memberno=" + memberno + ", categoryno=" + categoryno + ", title=" + title
        + ", quest=" + quest + ", rdate=" + rdate + "]";
  }
}
