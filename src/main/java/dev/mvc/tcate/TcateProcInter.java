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
  
}
