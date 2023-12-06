package dev.mvc.question;

import java.util.ArrayList;
import java.util.HashMap;

public interface QnAProcInter {
  /**
   * 질문 등록
   * @param qnaVO
   * @return 등록된 레코드
   */
  public int create(QnAVO qnaVO);
  
  /**
   * 모든 질문 목록
   * @return
   */
  public ArrayList<QnAVO> list_all();
  
  /**
   * 특정 카테고리의 등록된 질문 목록
   * @param categoryno
   * @return
   */
  public ArrayList<QnAVO> list_by_tcateno(int tcateno);
  
  
  /**
   * 특정 회원의 등록된 질문 목록
   * @param memberno
   * @return
   */
  public ArrayList<QnAVO> list_by_memberno(int memberno);
  
  /**
   * 카테고리별 검색 목록
   * @param map
   * @return
   */
  public ArrayList<QnAVO> list_by_tcateno_search(HashMap<String, Object> hashMap);
  
  /**
   * 카테고리별 검색된 레코드 갯수
   * @param map
   * @return
   */
  public int search_count(HashMap<String, Object> hashMap);
  
  /**
   * 카테고리별 검색 목록 + 페이징
   * @param contentsVO
   * @return
   */
  public ArrayList<QnAVO> list_by_tcateno_search_paging(QnAVO qnaVO);
  
  /**
   * 조회
   * @param qnano
   * @return
   */
  public QnAVO read(int questno);
  
  /**
   * 삭제
   * @param questno
   * @return
   */
  public int delete(int questno);
  
  /**
   * 글 수정
   * @param qnaVO
   * @return
   */
  public int update_quest(QnAVO qnaVO);
}
