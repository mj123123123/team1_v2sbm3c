package dev.mvc.answer;

import java.util.ArrayList;

public interface AnswerDAOinter {
  /**
   * 답변 등록
   * @param answerVO
   * @return 등록된 레코드
   */
  public int create(AnswerVO answerVO);
  
  /**
   * 모든 답변 목록
   * @return
   */
  public ArrayList<AnswerVO> list_all();
  
  /**
   * 특정 관리자가 작성한 답글
   * @param categoryno
   * @return
   */
  public ArrayList<AnswerVO> list_by_adminno(int adminno);
  
  /**
   * 조회
   * @param ansno
   * @return
   */
  public AnswerVO read(int ansno);
  
  /**
   * 글 수정
   * @param answerVO
   * @return
   */
  public int update_answer(AnswerVO answerVO);
}
