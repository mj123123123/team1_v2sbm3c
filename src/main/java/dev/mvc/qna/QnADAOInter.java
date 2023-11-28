package dev.mvc.qna;

import java.util.ArrayList;

public interface QnADAOInter {
  
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
  public ArrayList<QnAVO> list_by_categoryno(int categoryno);
  
  
  /**
   * 특정 회원의 등록된 질문 목록
   * @param memberno
   * @return
   */
  public ArrayList<QnAVO> list_by_memberno(int memberno);
  
  /**
   * 조회
   * @param qnano
   * @return
   */
  public QnAVO read(int qnano);
  
  /**
   * 글 수정
   * @param qnaVO
   * @return
   */
  public int update_quest(QnAVO qnaVO);
  
  /**
   * 특정 카테고리에 속한 레코드 갯수를 리턴
   * @param categoryno
   * @return
   */
  public int count_by_categoryno(int categoryno);
  
  /**
   * 특정 카테고리에 속한 모든 레코드 삭제
   * @param categoryno
   * @return
   */
  public int delete_by_categoryno(int categoryno);
  
  /**
   * 특정 회원이 작성한 레코드 갯수를 리턴
   * @param memberno
   * @return
   */
  public int count_by_memberno(int memberno);
  
  /**
   * 특정 회원이 작성한 모든 레코드 삭제
   * @param memberno
   * @return
   */
  public int delete_by_memberno(int memberno);
}
