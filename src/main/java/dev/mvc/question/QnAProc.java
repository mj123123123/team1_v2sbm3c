package dev.mvc.question;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dev.mvc.question.QnAProc")
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
  public ArrayList<QnAVO> list_by_tcateno(int tcateno) {
    ArrayList<QnAVO> list = this.qnaDAO.list_by_tcateno(tcateno);
    return list;
  }

  @Override
  public ArrayList<QnAVO> list_by_memberno(int memberno) {
    ArrayList<QnAVO> list = this.qnaDAO.list_by_memberno(memberno);
    return list;
  }

  @Override
  public QnAVO read(int questno) {
    QnAVO qnaVO = this.qnaDAO.read(questno);
    return qnaVO;
  }

  @Override
  public int update_quest(QnAVO qnaVO) {
    int cnt = this.qnaDAO.update_quest(qnaVO);
    return cnt;
  }

  @Override
  public int count_by_tcateno(int tcateno) {
    int cnt = this.qnaDAO.count_by_tcateno(tcateno);
    return cnt;
  }

  @Override
  public int delete_by_tcateno(int tcateno) {
    int cnt = this.qnaDAO.delete_by_tcateno(tcateno);
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
