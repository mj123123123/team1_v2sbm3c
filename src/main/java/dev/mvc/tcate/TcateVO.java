package dev.mvc.tcate;

/*CREATE TABLE TCATE(
    TCATENO       NUMBER(10)      NOT NULL,
    NAME    VARCHAR2(30)      NOT NULL,   
    SEQNO      NUMBER(7)      NOT NULL,   
    VISIBLE    CHAR(1)    NOT NULL,
    PRIMARY KEY (TCATENO)
);*/

public class TcateVO {
  private int tcateno;
  private String name;
  private int seqno;
  private String visible;
  
  public int getTcateno() {
    return tcateno;
  }
  public void setTcateno(int tcateno) {
    this.tcateno = tcateno;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getSeqno() {
    return seqno;
  }
  public void setSeqno(int seqno) {
    this.seqno = seqno;
  }
  public String getVisible() {
    return visible;
  }
  public void setVisible(String visible) {
    this.visible = visible;
  }
  @Override
  public String toString() {
    return "TcateVO [tcateno=" + tcateno + ", name=" + name + ", seqno=" + seqno + ", visible=" + visible + "]";
  }
  
  
}
