package dev.mvc.fcate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FcateCont {
	@Autowired // FcateProcInter interface 구현한 객체를 만들어 자동으로 할당해라.
	@Qualifier("dev.mvc.fcate.FcateProc")
	private FcateProcInter fcateProc;

	public FcateCont() {
	    System.out.println("-> FcateCont created.");  
	  }

	// FORM 출력, http://localhost:9093/fcate/create.do
	@RequestMapping(value = "/fcate/create.do", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/fcate/create"); // /WEB-INF/views/fcate/create.jsp

		return mav;
	}

	// FORM 데이터 처리, http://localhost:9093/fcate/create.do
	@RequestMapping(value = "/fcate/create.do", method = RequestMethod.POST)
	public ModelAndView create(FcateVO fcateVO) { // 자동으로 fcateVO 객체가 생성되고 폼의 값이 할당됨
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/fcate/msg"); // /WEB-INF/views/fcate/msg.jsp

		int cnt = this.fcateProc.create(fcateVO);
		System.out.println("-> cnt: " + cnt);

		if (cnt == 1) {
			mav.addObject("code", "create_success"); // 키, 값
			mav.addObject("name", fcateVO.getName()); // 카테고리 이름 jsp로 전송
		} else {
			mav.addObject("code", "create_fail");
		}

		mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//	    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);

		return mav;
	}

	/**
	 * 전체 목록 http://localhost:9093/fcate/list_all.do
	 * 
	 * @return
	 */
	@RequestMapping(value = "/fcate/list_all.do", method = RequestMethod.GET)
	public ModelAndView list_all() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/fcate/list_all"); // /WEB-INF/views/fcate/list_all.jsp

		ArrayList<FcateVO> list = this.fcateProc.list_all();
		mav.addObject("list", list);

		return mav;
	}

	/**
	 * 조회 http://localhost:9093/fcate/read.do?fcateno=1
	 * 
	 * @return
	 */
	@RequestMapping(value = "/fcate/read.do", method = RequestMethod.GET)
	public ModelAndView read(int fcateno) { // int fcateno = (int)request.getParameter("fcateno");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/fcate/read"); // /WEB-INF/views/fcate/read.jsp

		FcateVO fcateVO = this.fcateProc.read(fcateno);
		mav.addObject("fcateVO", fcateVO);

		return mav;
	}

	/**
	 * 수정폼 http://localhost:9093/fcate/update.do?fcateno=1
	 * 
	 * @return
	 */
	@RequestMapping(value = "/fcate/update.do", method = RequestMethod.GET)
	public ModelAndView update(int fcateno) { // int fcateno = (int)request.getParameter("fcateno");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/fcate/update"); // /WEB-INF/views/fcate/update.jsp

		FcateVO fcateVO = this.fcateProc.read(fcateno);
		mav.addObject("fcateVO", fcateVO);

		return mav;
	}

	/**
	 * 수정 처리, http://localhost:9093/fcate/update.do
	 * 
	 * @param fcateVO 수정할 내용
	 * @return
	 */

	@RequestMapping(value = "/fcate/update.do", method = RequestMethod.POST)
	public ModelAndView update(FcateVO fcateVO) { // 자동으로 fcateVO 객체가 생성되고 폼의 값이 할당됨
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/fcate/msg"); // /WEB-INF/views/fcate/msg.jsp

		int cnt = this.fcateProc.update(fcateVO); // 수정 처리
		System.out.println("-> cnt: " + cnt);

		if (cnt == 1) {
			mav.addObject("code", "update_success"); // 키, 값
			mav.addObject("name", fcateVO.getName()); // 카테고리 이름 jsp로 전송
		} else {
			mav.addObject("code", "update_fail");
		}

		mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//	    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);

		return mav;
	}

	/**
	 * 삭제폼 http://localhost:9093/fcate/delete.do?fcateno=1
	 * 
	 * @return
	 */
	@RequestMapping(value = "/fcate/delete.do", method = RequestMethod.GET)
	public ModelAndView delete(int fcateno) { // int fcateno = (int)request.getParameter("fcateno");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/fcate/delete"); // /WEB-INF/views/fcate/delete.jsp

		FcateVO fcateVO = this.fcateProc.read(fcateno);
		mav.addObject("fcateVO", fcateVO);

		return mav;
	}

	/**
	 * 삭제 처리, http://localhost:9093/fcate/delete.do?fcateno=1
	 * 
	 * @param fcateno 삭제할 레코드 번호
	 * @return
	 */

	@RequestMapping(value = "/fcate/delete.do", method = RequestMethod.POST)
	public ModelAndView delete_proc(int fcateno) { // 자동으로 fcateVO 객체가 생성되고 폼의 값이 할당됨
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/fcate/msg"); // /WEB-INF/views/fcate/msg.jsp

		FcateVO fcateVO = this.fcateProc.read(fcateno); // 삭제할 레코드 정보 읽기

		int cnt = this.fcateProc.delete(fcateno); // 삭제 처리
		System.out.println("-> cnt: " + cnt);

		if (cnt == 1) {
			mav.addObject("code", "delete_success"); // 키, 값
			mav.addObject("name", fcateVO.getName()); // 카테고리 이름 jsp로 전송
		} else {
			mav.addObject("code", "delete_fail");
		}

		mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt);
//	    mav.addObject("cnt", 0); // request.setAttribute("cnt", cnt);

		return mav;
	}

}
