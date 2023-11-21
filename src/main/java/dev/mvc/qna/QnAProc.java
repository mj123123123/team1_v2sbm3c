package dev.mvc.qna;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.qna.QnAProc")
public class QnAProc implements QnAProcInter {
  @Autowired
  private QnADAOInter qnaDAO;
  
  @Override
  public int create(QnAVO qnaVO) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public ArrayList<QnAVO> list_all() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<QnAVO> list_by_categoryno(int categoryno) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ArrayList<QnAVO> list_by_memberno(int memberno) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public QnAVO read(int qnano) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int update_quest(QnAVO qnaVO) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int count_by_categoryno(int categoryno) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int delete_by_categoryno(int categoryno) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int count_by_memberno(int memberno) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int delete_by_memberno(int memberno) {
    // TODO Auto-generated method stub
    return 0;
  }

}
