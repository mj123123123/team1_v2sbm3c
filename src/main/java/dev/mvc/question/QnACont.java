package dev.mvc.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dev.mvc.tcate.TcateProcInter;
import dev.mvc.tcate.TcateVO;

@Controller
public class QnACont {
  @Autowired
  @Qualifier("dev.mvc.question.QnAProc")
  private QnAProcInter qnaProc;
  
  @Autowired
  @Qualifier("dev.mvc.tcate.TcateProc")
  private TcateProcInter tcateProc;
  
  public QnACont () {
    System.out.println("-> QnACont created.");
  }
  
  /**
   * POST 요청시 JSP 페이지에서 JSTL 호출 기능 지원, 새로고침 방지, EL에서 param으로 접근
   * POST → url → GET → 데이터 전송
   * @return
   */
  @RequestMapping(value="/question/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  // http://localhost:9093/question/create.do?tcateno=1
  @RequestMapping(value="/question/create.do", method = RequestMethod.GET)
  public ModelAndView create(int tcateno) {
    ModelAndView mav = new ModelAndView();

    TcateVO tcateVO = this.tcateProc.read(tcateno);
    mav.addObject("tcateVO", tcateVO);
    
    mav.setViewName("/question/create"); // /webapp/WEB-INF/views/question/create.jsp
    
    return mav;
  }

}
