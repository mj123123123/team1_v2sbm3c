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

	/**
	 * 조회
	 * 
	 * @param festivalcateno
	 * @return
	 */
	public FestivalcateVO read(int festivalcateno);

	/**
	 * 수정
	 * 
	 * @param festivalcateVO
	 * @return 수정된 레코드 갯수
	 */
	public int update(FestivalcateVO festivalcateVO);

	/**
	 * 삭제
	 * 
	 * @param festivalcateno 삭제할 레코드 PK 번호
	 * @return 삭제된 레코드 갯수
	 */
	public int delete(int festivalcateno);
	
	/**
	 * 우선 순위 높임, 10 등 -> 1 등
	 * 
	 * @param festivalcateno
	 * @return 수정된 레코드 갯수
	 */
	public int update_seqno_forward(int festivalcateno);

	/**
	 * 우선 순위 낮춤, 1 등 -> 10 등
	 * 
	 * @param festivalcateno
	 * @return 수정된 레코드 갯수
	 */
	public int update_seqno_backward(int festivalcateno);

	/**
	 * 카테고리 공개 설정
	 * 
	 * @param festivalcateno
	 * @return
	 */
	public int update_visible_y(int festivalcateno);

	/**
	 * 카테고리 비공개 설정
	 * 
	 * @param festivalcateno
	 * @return
	 */
	public int update_visible_n(int festivalcateno);

	/**
	 * 비회원/회원 SELECT LIST
	 * 
	 * @return
	 */
	public ArrayList<FestivalcateVO> list_all_y();

}