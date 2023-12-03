package dev.mvc.question;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.mvc.tool.Tool;

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
  public ArrayList<QnAVO> list_by_tcateno_search(HashMap<String, Object> hashMap) {
    ArrayList<QnAVO> list = this.qnaDAO.list_by_tcateno_search(hashMap);
    return list;
  }

  @Override
  public int search_count(HashMap<String, Object> hashMap) {
    int cnt = this.qnaDAO.search_count(hashMap);
    return cnt;
  }

  @Override
  public ArrayList<QnAVO> list_by_tcateno_search_paging(QnAVO qnaVO) { // - Contents 오류
    /*
    예) 페이지당 10개의 레코드 출력
    1 page: WHERE r >= 1 AND r <= 10
    2 page: WHERE r >= 11 AND r <= 20
    3 page: WHERE r >= 21 AND r <= 30
      
    페이지에서 출력할 시작 레코드 번호 계산 기준값, nowPage는 1부터 시작
    1 페이지 시작 rownum: now_page = 1, (1 - 1) * 10 --> 0 
    2 페이지 시작 rownum: now_page = 2, (2 - 1) * 10 --> 10
    3 페이지 시작 rownum: now_page = 3, (3 - 1) * 10 --> 20
    */
    //int begin_of_page = (qnaVO.getNow_page() - 1) * Contents.RECORD_PER_PAGE;
   
    // 시작 rownum 결정
    // 1 페이지 = 0 + 1: 1
    // 2 페이지 = 10 + 1: 11
    // 3 페이지 = 20 + 1: 21 
    //int start_num = begin_of_page + 1;
    
    //  종료 rownum
    // 1 페이지 = 0 + 10: 10
    // 2 페이지 = 10 + 10: 20
    // 3 페이지 = 20 + 10: 30
    //int end_num = begin_of_page + Contents.RECORD_PER_PAGE;   
    /*
    1 페이지: WHERE r >= 1 AND r <= 10
    2 페이지: WHERE r >= 11 AND r <= 20
    3 페이지: WHERE r >= 21 AND r <= 30
    */
    
    // System.out.println("begin_of_page: " + begin_of_page);
    // System.out.println("WHERE r >= "+start_num+" AND r <= " + end_num);
    
    //qnaVO.setStart_num(start_num);
    //qnaVO.setEnd_num(end_num);
    
    ArrayList<QnAVO> list = this.qnaDAO.list_by_tcateno_search_paging(qnaVO);
    
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
  public int delete(int questno) {
    int cnt = this.qnaDAO.delete(questno);
    return cnt;
  }

}
