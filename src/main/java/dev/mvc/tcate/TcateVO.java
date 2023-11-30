package dev.mvc.tcate;

/*CREATE TABLE TCATE(
    CATENO       NUMBER(10)      NOT NULL,
    NAME    VARCHAR2(30)      NOT NULL,   
    SEQNO      NUMBER(7)      NOT NULL,   
    VISIBLE    CHAR(1)    NOT NULL,
    PRIMARY KEY (CATENO)
);*/

public class TcateVO {
  private int cateno;
  private String name;
  private int seqno;
  private String visible;
  public int getCateno() {
    return cateno;
  }
  public void setCateno(int cateno) {
    this.cateno = cateno;
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
    return "TcateVO [cateno=" + cateno + ", name=" + name + ", seqno=" + seqno + ", visible=" + visible + "]";
  }
  
  
}
