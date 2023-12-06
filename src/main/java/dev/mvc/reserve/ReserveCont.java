package dev.mvc.reserve;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReserveCont {
  @Autowired
  @Qualifier("dev.mvc.reserve.ReserveProc")
  private ReserveProcInter reserveProc;
  
  public ReserveCont() {
    System.out.println("-> ReserveCont created.");
  }
  
  //FORM 출력, http://localhost:9093/reserve/create.do
  @RequestMapping(value="/reserve/create.do", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reserve/create"); 
    
    return mav;
  }
  
  //FORM 데이터 처리, http://localhost:9093/reserve/create.do
  @RequestMapping(value="/reserve/create.do", method = RequestMethod.POST)
  public ModelAndView create(ReserveVO reserveVO) { 
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.reserveProc.create(reserveVO);
    System.out.println("-> cnt: " + cnt);
    
    if(cnt ==1 ) {
      mav.setViewName("redirect:/reserve/list_all.do"); // 해당 주소로 자동 이동
    } else {
      mav.addObject("code", "create_fail");
      mav.setViewName("/reserve/msg"); 
    }
    
    mav.addObject("cnt", cnt);// request.setAttribute("cnt",cnt);
    
    return mav;
  }
  

  /**
   * 전체 목록 http://localhost:9093/reserve/list_all.do
   * 
   * @return
   */
  @RequestMapping(value = "/reserve/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reserve/list_all"); 

    ArrayList<ReserveVO> list = this.reserveProc.list_all();
    mav.addObject("list", list);

    return mav;
  }
  
  /**
   * 조회
   * // http://localhost:9093/reserve/read.do?reserveno=1
   * @return
   */
  @RequestMapping(value="/reserve/read.do", method = RequestMethod.GET)
  public ModelAndView read(int reserveno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reserve/read"); 
    
    ReserveVO reserveVO = this.reserveProc.read(reserveno);
    mav.addObject("reserveVO", reserveVO);
    
    return mav;
  }

  /**
   * 수정폼 http://localhost:9093/reserve/update.do?reserveno=1
   * 
   * @return
   */
  @RequestMapping(value = "/reserve/update.do", method = RequestMethod.GET)
  public ModelAndView update(int reserveno) { 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reserve/update"); 

    ReserveVO reserveVO = this.reserveProc.read(reserveno);
    mav.addObject("reserveVO", reserveVO);

    return mav;
  }
  
  /**
   * 수정 처리, http://localhost:9093/reserve/update.do
   * @param reserveVO 수정할 내용
   * @return 수정된 레코드 갯수
   */
  @RequestMapping(value="/reserve/update.do", method = RequestMethod.POST)
  public ModelAndView update(ReserveVO reserveVO) { 
    ModelAndView mav = new ModelAndView();
    
    int cnt = this.reserveProc.update(reserveVO); // 수정처리
    System.out.println("-> cnt: " + cnt);
    
    if(cnt ==1 ) {
      mav.setViewName("redirect:/reserve/list_all.do"); 
    } else {
      mav.addObject("code", "update_fail");
      mav.setViewName("/reserve/msg"); // /WEB-INF/views/reserve/msg.jsp
      
    }
    mav.addObject("cnt", cnt);// request.setAttribute("cnt",cnt);
    
    return mav;
  }

  
  /**
  * 삭제폼 http://localhost:9093/reserve/delete.do?reserveno=5
  * 
  * @return
  */
  @RequestMapping(value = "/reserve/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(int reserveno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reserve/delete"); 

    ReserveVO reserveVO = this.reserveProc.read(reserveno);
    mav.addObject("reserveVO", reserveVO);

    return mav;
  }

  /**
  * 삭제 처리, http://localhost:9093/reserve/delete.do?reserveno=1
  * 
  * @param reserveno 삭제할 레코드 번호
  * @return
  */
  @RequestMapping(value = "/reserve/delete.do", method = RequestMethod.POST)
  public ModelAndView delete_proc(int reserveno) { 
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/reserve/msg"); 

    ReserveVO reserveVO = this.reserveProc.read(reserveno); // 삭제할 레코드 정보 읽기

    int cnt = this.reserveProc.delete(reserveno); // 삭제 처리
    System.out.println("-> cnt: " + cnt);

    if (cnt == 1) {
      mav.addObject("code", "delete_success"); // 키, 값
      mav.addObject("name", reserveVO.getName()); // 카테고리 이름 jsp로 전송
    } else {
      mav.addObject("code", "delete_fail");
    }

    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//      mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);

    return mav;
  }
  
  
}
