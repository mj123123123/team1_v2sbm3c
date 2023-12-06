package dev.mvc.fcontents;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//import dev.mvc.admin.AdminProcInter;
import dev.mvc.fcate.FcateProcInter;
import dev.mvc.fcate.FcateVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;

@Controller
public class ContentsCont {
//  @Autowired
//  @Qualifier("dev.mvc.admin.AdminProc") // @Component("dev.mvc.admin.AdminProc")
//  private AdminProcInter adminProc;
  
  @Autowired
  @Qualifier("dev.mvc.fcate.FcateProc")  // @Component("dev.mvc.fcate.FcateProc")
  private FcateProcInter fcateProc;
  
  @Autowired
  @Qualifier("dev.mvc.contents.ContentsProc") // @Component("dev.mvc.contents.ContentsProc")
  private ContentsProcInter contentsProc;
  
  public ContentsCont () {
    System.out.println("-> ContentsCont created.");
  }
  
  /**
   * POST 요청시 JSP 페이지에서 JSTL 호출 기능 지원, 새로고침 방지, EL에서 param으로 접근
   * POST → url → GET → 데이터 전송
   * @return
   */
  @RequestMapping(value="/fcontents/msg.do", method=RequestMethod.GET)
  public ModelAndView msg(String url){
    ModelAndView mav = new ModelAndView();

    mav.setViewName(url); // forward
    
    return mav; // forward
  }
  
  // 등록 폼, contents 테이블은 FK로 fcateno를 사용함.
  // http://localhost:9093/fcontents/create.do  X
  // http://localhost:9093/fcontents/create.do?fcateno=1  // fcateno 변수값을 보내는 목적
  // http://localhost:9093/fcontents/create.do?fcateno=2
  // http://localhost:9093/fcontents/create.do?fcateno=3
  @RequestMapping(value="/fcontents/create.do", method = RequestMethod.GET)
  public ModelAndView create(int fcateno) {
//  public ModelAndView create(HttpServletRequest request,  int fcateno) {
    ModelAndView mav = new ModelAndView();

    FcateVO fcateVO = this.fcateProc.read(fcateno); // create.jsp에 카테고리 정보를 출력하기위한 목적
    mav.addObject("fcateVO", fcateVO);
//    request.setAttribute("fcateVO", fcateVO);
    
    mav.setViewName("/fcontents/create"); // /webapp/WEB-INF/views/fcontents/create.jsp
    
    return mav;
  }
  
}