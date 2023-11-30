package dev.mvc.tcate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.tcate.TcateProc")
public class TcateProc implements TcateProcInter {
  @Autowired
  private TcateDAOInter tcateDAO;

  @Override
  public int create(TcateVO tcateVO) {
    int cnt = this.tcateDAO.create(tcateVO);
    return cnt;
  }

  @Override
  public ArrayList<TcateVO> list_all() {
    ArrayList<TcateVO> list = this.tcateDAO.list_all();
    return list;
  }

  @Override
  public TcateVO read(int tcateno) {
    TcateVO tcateVO =  this.tcateDAO.read(tcateno);
    return tcateVO;
  }

}
