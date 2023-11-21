package dev.mvc.qna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class QnACont {
  @Autowired
  @Qualifier("dev.mvc.qna.QnAProc")
  private QnAProcInter qnaProc;
  
  public QnACont () {
    System.out.println("-> QnACont created.");
  }

}
