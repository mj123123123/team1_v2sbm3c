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
//  관리지 이후 수정
//  /**
//   * 수정폼
//   * // http://localhost:9093/tcate/update.do?tcateno=2
//   * @return
//   */
//  @RequestMapping(value="/tcate/update.do", method = RequestMethod.GET)
//  public ModelAndView update(HttpSession session, int tcateno) {
//    ModelAndView mav = new ModelAndView();
//    
//    if(this.adminProc.isAdmin(session) == true) {
//    //mav.setViewName("/tcate/update"); 
//      mav.setViewName("/tcate/list_all_update"); 
//      
//      TcateVO tcateVO = this.tcateProc.read(tcateno); // 그대로 유지
//      mav.addObject("tcateVO", tcateVO);
//      
//      ArrayList<TcateVO> list = this.tcateProc.list_all();
//      mav.addObject("list",list);
//
//    } else {
//      mav.setViewName("/admin/login_need"); 
//    }
//    return mav;
//}
  /**
   * 수정폼 http://localhost:9093/tcate/update.do?tcateno=1
   * 
   * @return
   */
  @RequestMapping(value = "/tcate/update.do", method = RequestMethod.GET)
  public ModelAndView update(int tcateno) { 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/tcate/update"); 

    TcateVO tcateVO = this.tcateProc.read(tcateno);
    mav.addObject("tcateVO", tcateVO);

    return mav;
  }
  
  /**
   * 수정 처리, http://localhost:9093/tcate/update.do
   * @param tcateVO 수정할 내용
   * @return 수정된 레코드 갯수
   */
  @RequestMapping(value="/tcate/update.do", method = RequestMethod.POST)
  public ModelAndView update(TcateVO tcateVO) { 
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.tcateProc.update(tcateVO); // 수정처리
    System.out.println("-> cnt: " + cnt);
    
    if(cnt ==1 ) {
      mav.setViewName("redirect:/tcate/list_all.do"); 
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/tcate/msg"); // /WEB-INF/views/tcate/msg.jsp
      
    }
    mav.addObject("cnt", cnt);// request.setAttribute("cnt",cnt);
    
    return mav;
  }
//  관리자 및 여행지 이후 변경
//  /**
//   * 삭제폼
//   * // http://localhost:9093/tcate/delete.do?tcateno=5
//   * @return
//   */
//  @RequestMapping(value="/tcate/delete.do", method = RequestMethod.GET)
//  public ModelAndView delete(HttpSession session, int tcateno) { 
//    ModelAndView mav = new ModelAndView();
//    
//    if(this.adminProc.isAdmin(session) == true) {
//      mav.setViewName("/tcate/list_all_delete"); 
//      
//      TcateVO tcateVO = this.tcateProc.read(tcateno); // 그대로 유지 - 삭제할 내용을 읽어와야 하니까
//      mav.addObject("tcateVO", tcateVO);
//      
//      ArrayList<TcateVO> list = this.tcateProc.list_all();
//      mav.addObject("list",list);
//      
//   // 특정 카테고리에 속한 레코드 갯수를 리턴
//      int count_by_tcateno = this.contentsProc.count_by_tcateno(tcateno);
//      mav.addObject("count_by_tcateno", count_by_tcateno);
//      
//    } else {
//      mav.setViewName("/admin/login_need"); 
//    }
//    return mav;
//  }
  
  /**
  * 삭제폼 http://localhost:9093/tcate/delete.do?tcateno=5
  * 
  * @return
  */
  @RequestMapping(value = "/tcate/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int tcateno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/tcate/delete"); 

    TcateVO tcateVO = this.tcateProc.read(tcateno);
    mav.addObject("tcateVO", tcateVO);

    return mav;
  }

  /**
  * 삭제 처리, http://localhost:9093/tcate/delete.do?tcateno=1
  * 
  * @param tcateno 삭제할 레코드 번호
  * @return
  */
  @RequestMapping(value = "/tcate/delete.do", method = RequestMethod.POST)
  public ModelAndView delete_proc(int tcateno) { 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/tcate/msg"); 

    TcateVO tcateVO = this.tcateProc.read(tcateno); // 삭제할 레코드 정보 읽기

    int cnt = this.tcateProc.delete(tcateno); // 삭제 처리
    System.out.println("-> cnt: " + cnt);

    if (cnt == 1) {
      mav.addObject("code", "delete_success"); // 키, 값
      mav.addObject("name", tcateVO.getName()); // 카테고리 이름 jsp로 전송
    } else {
      mav.addObject("code", "delete_fail");
    }

    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//      mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);

    return mav;
  }
  
  
}
