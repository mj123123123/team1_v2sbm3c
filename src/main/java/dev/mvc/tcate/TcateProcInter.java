package dev.mvc.tcate;

import java.util.ArrayList;

public interface TcateProcInter {
  /**
   * 
   * @param tcateVO
   * @return
   */
  public int create(TcateVO tcateVO);
  
  /**
   * 전체 목록
   * @return
   */
  public ArrayList<TcateVO> list_all();
  
  /**
   * 조회
   * @param tcateno
   * @return
   */ 
  public TcateVO read(int tcateno);
  
  /**
   * 수정
   * @param tcateVO
   * @return 수정된 레코드 갯수
   */
  public int update(TcateVO tcateVO);
  
  /**
   * 삭제
   * @param tcateno 삭제할 레코드 PK 번호
   * @return 삭제된 레코드 갯수
   */
  public int delete(int tcateno);
  
}
