package dev.mvc.trecom;

/*CREATE TABLE TRECOM(
    TRECOMNO       NUMBER(8)      NOT NULL,
    TCATENO    NUMBER(10)      NULL, -- FK
    MEMBERNO      NUMBER(10)      NULL, --FK  
    SEQ     NUMBER(2)   DEFAULT 1   NOT NULL,
    RDATE   DATE    NOT NULL,

    FOREIGN KEY (TCATENO) REFERENCES TCATE (TCATENO),
    FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
    PRIMARY KEY (TRECOMNO)
);*/

public class TrecomVO {
  private int trecomno;
  private int tcateno;
  private int memberno;
  private int seq;
  private String rdate;
  public int getTrecomno() {
    return trecomno;
  }
  public void setTrecomno(int trecomno) {
    this.trecomno = trecomno;
  }
  public int getTcateno() {
    return tcateno;
  }
  public void setTcateno(int tcateno) {
    this.tcateno = tcateno;
  }
  public int getMemberno() {
    return memberno;
  }
  public void setMemberno(int memberno) {
    this.memberno = memberno;
  }
  public int getSeq() {
    return seq;
  }
  public void setSeq(int seq) {
    this.seq = seq;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
  @Override
  public String toString() {
    return "TrecomVO [trecomno=" + trecomno + ", tcateno=" + tcateno + ", memberno=" + memberno + ", seq=" + seq
        + ", rdate=" + rdate + "]";
  }
}
