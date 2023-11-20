package dev.mvc.festivalcate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FestivalcateCont {
	// FestivalcateProcInter interface 구현한 객체를 만들어 자동으로 할당해라.
	@Autowired
	@Qualifier("dev.mvc.festivalcate.FestivalcateProc")
	private FestivalcateProcInter festivalcateProc;

	public FestivalcateCont() {
		System.out.println("-> FestivalcateCont created.");
	}

//	// FORM 출력, http://localhost:9093/festivalcate/create.do
//	@RequestMapping(value = "/festivalcate/create.do", method = RequestMethod.GET)
//	@ResponseBody // 단순 문자열로 출력, jsp 파일명 조합이 발생하지 않음.
//	public String create() {
//		return "GET 방식 FORM 출력";
//	}

	// FORM 출력, http://localhost:9093/festival/create.do
	@RequestMapping(value = "/festivalcate/create.do", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/festivalcate/create"); // /WEB-INF/views/festivalcate/create.jsp

		return mav;
	}

	// FORM 데이터 처리, http://localhost:9093/festivalcate/create.do
	  @RequestMapping(value="/festivalcate/create.do", method = RequestMethod.POST)
	  public ModelAndView create(FestivalcateVO festivalcateVO) { // 자동으로 festivalcateVO 객체가 생성되고 폼의 값이 할당됨
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/festivalcate/msg"); // /WEB-INF/views/festivalcate/msg.jsp
	    
	    int cnt = this.festivalcateProc.create(festivalcateVO);
	    System.out.println("-> cnt: " + cnt);
	    
	    if (cnt == 1) {
	      mav.addObject("code", "create_success"); // 키, 값
	      mav.addObject("name", festivalcateVO.getFestivalcatename()); // 카테고리 이름 jsp로 전송
	    } else {
	      mav.addObject("code", "create_fail");
	    }
	    
	    mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//	    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);
	    
	    return mav;
	  }
	  
	  /**
	   * 전체 목록
	   * http://localhost:9093/festivalcate/list_all.do
	   * @return
	   */
	  @RequestMapping(value="/festivalcate/list_all.do", method = RequestMethod.GET)
	  public ModelAndView list_all() {
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/festivalcate/list_all"); // /WEB-INF/views/festivalcate/list_all.jsp
	    
	    ArrayList<FestivalcateVO> list = this.festivalcateProc.list_all();
	    mav.addObject("list", list);
	    
	    return mav;
	  }
}
