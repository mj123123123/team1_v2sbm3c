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
    int cnt = this.qnaDAO.create(qnaVO);
    return cnt;
  }

  @Override
  public ArrayList<QnAVO> list_all() {
    ArrayList<QnAVO> list = this.qnaDAO.list_all();
    return list;
  }

  @Override
  public ArrayList<QnAVO> list_by_categoryno(int categoryno) {
    ArrayList<QnAVO> list = this.qnaDAO.list_by_categoryno(categoryno);
    return list;
  }

  @Override
  public ArrayList<QnAVO> list_by_memberno(int memberno) {
    ArrayList<QnAVO> list = this.qnaDAO.list_by_memberno(memberno);
    return list;
  }

  @Override
  public QnAVO read(int qnano) {
    QnAVO qnaVO = this.qnaDAO.read(qnano);
    return qnaVO;
  }

  @Override
  public int update_quest(QnAVO qnaVO) {
    int cnt = this.qnaDAO.update_quest(qnaVO);
    return cnt;
  }

  @Override
  public int count_by_categoryno(int categoryno) {
    int cnt = this.qnaDAO.count_by_categoryno(categoryno);
    return cnt;
  }

  @Override
  public int delete_by_categoryno(int categoryno) {
    int cnt = this.qnaDAO.delete_by_categoryno(categoryno);
    return cnt;
  }

  @Override
  public int count_by_memberno(int memberno) {
    int cnt = this.qnaDAO.count_by_memberno(memberno);
    return cnt;
  }

  @Override
  public int delete_by_memberno(int memberno) {
    int cnt = this.qnaDAO.delete_by_memberno(memberno);
    return cnt;
  }

}
