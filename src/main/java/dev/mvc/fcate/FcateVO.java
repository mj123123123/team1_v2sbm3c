package dev.mvc.fcate;

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

public class FcateVO {

	private int fcateno;
	private String name;
	private int cnt;
	private String rdate;
	private int seqno;
	private String visible;

}