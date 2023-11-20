package dev.mvc.festivalcate;

import java.util.ArrayList;

public interface FestivalcateProcInter {

	/**
	 * 등록, 추상 메소드 -> Spring Boot이 구현함.
	 * 
	 * @param feativalcateVO 객체
	 * @return 등록된 레코드 갯수
	 */

	public int create(FestivalcateVO feativalcateVO);

	/**
	 * 전체 목록
	 * 
	 * @return
	 */
	public ArrayList<FestivalcateVO> list_all();

}
