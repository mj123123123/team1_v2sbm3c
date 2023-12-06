package dev.mvc.reserve;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.reserve.ReserveProc")
public class ReserveProc implements ReserveProcInter {
  @Autowired
  private ReserveDAOInter reserveDAO;

  @Override
  public int create(ReserveVO reserveVO) {
    int cnt = this.reserveDAO.create(reserveVO);
    return cnt;
  }

  @Override
  public ArrayList<ReserveVO> list_all() {
    ArrayList<ReserveVO> list = this.reserveDAO.list_all();
    return list;
  }

  @Override
  public ReserveVO read(int reserveno) {
    ReserveVO reserveVO =  this.reserveDAO.read(reserveno);
    return reserveVO;
  }

  @Override
  public int update(ReserveVO reserveVO) {
    int cnt = this.reserveDAO.update(reserveVO);    
    return cnt;    
  }

  @Override
  public int delete(int reserveno) {
    int cnt = this.reserveDAO.delete(reserveno);
    return cnt;
  }

}
