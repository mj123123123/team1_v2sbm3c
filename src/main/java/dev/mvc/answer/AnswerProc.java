package dev.mvc.answer;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.qna.QnAVO;

@Component("dev.mvc.answer.AnswerProc")
public class AnswerProc implements AnswerProcInter {
  @Autowired
  private AnswerDAOinter answerDAO;
  
  @Override
  public int create(AnswerVO answerVO) {
    int cnt = this.answerDAO.create(answerVO);
    return cnt;
  }

  @Override
  public ArrayList<AnswerVO> list_all() {
    ArrayList<AnswerVO> list = this.answerDAO.list_all();
    return list;
  }

  @Override
  public ArrayList<AnswerVO> list_by_adminno(int adminno) {
    ArrayList<AnswerVO> list = this.answerDAO.list_by_adminno(adminno);
    return list;
  }

  @Override
  public AnswerVO read(int ansno) {
    AnswerVO answerVO = this.answerDAO.read(ansno);
    return answerVO;
  }

  @Override
  public int update_answer(AnswerVO answerVO) {
    int cnt = this.answerDAO.update_answer(answerVO);
    return cnt;
  }

}
