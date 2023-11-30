package dev.mvc.tcate;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TcateCont {
  @Autowired
  @Qualifier("dev.mvc.tcate.TcateProc")
  private TcateProcInter tcateProc;
  
  public TcateCont() {
    System.out.println("-> TcateCont created.");
  }
  
  //FORM 출력, http://localhost:9093/tcate/create.do
  @RequestMapping(value="/tcate/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/tcate/create"); 
    
    return mav;
  }
  
  //FORM 데이터 처리, http://localhost:9093/tcate/create.do
  @RequestMapping(value="/tcate/create.do", method = RequestMethod.POST)
  public ModelAndView create(TcateVO tcateVO) { 
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.tcateProc.create(tcateVO);
    System.out.println("-> cnt: " + cnt);
    
    if(cnt ==1 ) {
      mav.setViewName("redirect:/tcate/list_all.do"); // 해당 주소로 자동 이동
    } else {
      mav.addObject("code", "create_fail");
      mav.setViewName("/tcate/msg"); 
    }
    
    mav.addObject("cnt", cnt);// request.setAttribute("cnt",cnt);
    
    return mav;
  }
  
//  관리자 완성되면 수정하기
//  /** 
//   * 전체 목록
//   * // http://localhost:9093/tcate/list_all.do
//   * @return
//   */
//  @RequestMapping(value="/tcate/list_all.do", method = RequestMethod.GET)
//  public ModelAndView list_all(HttpSession session) {
//    ModelAndView mav = new ModelAndView();
//    
//    if(this.adminProc.isAdmin(session) == true) {
//      mav.setViewName("/tcate/list_all"); 
//      ArrayList<TcateVO> list = this.tcateProc.list_all();
//      mav.addObject("list", list);
//    } else {
//      mav.setViewName("/admin/login_need"); // /WEB-INF/views/admin/login_need.jsp
//    }
//    return mav;
//  }
  /**
   * 전체 목록 http://localhost:9093/tcate/list_all.do
   * 
   * @return
   */
  @RequestMapping(value = "/tcate/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/tcate/list_all"); 

    ArrayList<TcateVO> list = this.tcateProc.list_all();
    mav.addObject("list", list);

    return mav;
  }
  
  /**
  * 조회
  * // http://localhost:9093/tcate/read.do?tcateno=1
  * @return
  */
  @RequestMapping(value="/tcate/read.do", method = RequestMethod.GET)
  public ModelAndView read(int tcateno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/tcate/read"); 
    
    TcateVO tcateVO = this.tcateProc.read(tcateno);
    mav.addObject("tcateVO", tcateVO);
    
    return mav;
  }
  
}
