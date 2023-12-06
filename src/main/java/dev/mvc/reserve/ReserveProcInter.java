package dev.mvc.reserve;

import java.util.ArrayList;

public interface ReserveProcInter {
  /**
   * 
   * @param reserveVO
   * @return
   */
  public int create(ReserveVO reserveVO);
  
  /**
   * 전체 목록
   * @return
   */
  public ArrayList<ReserveVO> list_all();
  
  /**
   * 조회
   * @param reserveno
   * @return
   */ 
  public ReserveVO read(int reserveno);
  
  /**
   * 수정
   * @param reserveVO
   * @return 수정된 레코드 갯수
   */
  public int update(ReserveVO reserveVO);
  
  /**
   * 삭제
   * @param reserveno 삭제할 레코드 PK 번호
   * @return 삭제된 레코드 갯수
   */
  public int delete(int reserveno);
  
}
