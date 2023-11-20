package dev.mvc.festivalcate;

/*
 * CREATE TABLE festivalcate(
		festivalcateno    NUMBER(10)    NOT NULL   PRIMARY KEY,
		festivalcatename  VARCHAR2(25)  NOT NULL,
		festivalcatecnt   NUMBER(10)    NOT NULL,
		festivalcaterdate DATE          NOT NULL,
        SEQNO                 NUMBER(5),
        VISIBLE               CHAR(1)
	);
 */

public class FestivalcateVO {
	
	private int festivalcateno;
	private String festivalcatename;
	private int festivalcatecnt;
	private String festivalcaterdate;
	private int seqno;
	private String visible;
	
	public int getFestivalcateno() {
		return festivalcateno;
	}
	public void setFestivalcateno(int festivalcateno) {
		this.festivalcateno = festivalcateno;
	}
	public String getFestivalcatename() {
		return festivalcatename;
	}
	public void setFestivalcatename(String festivalcatename) {
		this.festivalcatename = festivalcatename;
	}
	public int getFestivalcatecnt() {
		return festivalcatecnt;
	}
	public void setFestivalcatecnt(int festivalcatecnt) {
		this.festivalcatecnt = festivalcatecnt;
	}
	public String getFestivalcaterdate() {
		return festivalcaterdate;
	}
	public void setFestivalcaterdate(String festivalcaterdate) {
		this.festivalcaterdate = festivalcaterdate;
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
		return "FeativalcateVO [festivalcateno=" + festivalcateno + ", festivalcatename="
				+ festivalcatename + ", festivalcatecnt=" + festivalcatecnt + ", festivalcaterdate="
				+ festivalcaterdate + ", seqno=" + seqno + ", visible=" + visible + "]";
	}
	
	
	
}
