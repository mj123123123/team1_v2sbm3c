package dev.mvc.question;

import java.util.ArrayList;
import java.util.HashMap;

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
import dev.mvc.tool.Tool;

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
  public ModelAndView create(int questno) {
    ModelAndView mav = new ModelAndView();

    QnAVO qnaVO = this.qnaProc.read(questno);
    mav.addObject("qnaVO", qnaVO);
    
    mav.setViewName("/question/create"); // /webapp/WEB-INF/views/question/create.jsp
    
    return mav;
  }
  
  /**
   * 등록 처리 http://localhost:9093/question/create.do
   * 
   * @return
   */
  @RequestMapping(value="/question/create.do", method = RequestMethod.GET)
  public ModelAndView create(HttpServletRequest request, HttpSession session, QnAVO qnaVO) {
    ModelAndView mav = new ModelAndView();
  
    /*if(memberProc.isMember(session)) { //회원 로그인 - 회원 제작 시 테스트
      // Call By Reference: 메모리 공유, Hashcode 전달
      int memberno = (int)session.getAttribute("memberno"); // memberno FK
      qnaVO.setMemberno(memberno);
      int cnt = this.qnaProc.create(qnaVO); 
      
      if(cnt == 1) {
        mav.addObject("code", "create_success");
      } else {
        mav.addObject("code", "create_fail");
      }
      mav.addObject("cnt", cnt);
      mav.addObject("questno", qnaVO.getQuestno());
      
      mav.addObject("url", "/question/msg.do");
      mav.setViewName("redirect:/question/msg.do"); 
      
      
    } else {
      mav.addObject("url", "/member/login_need"); // /WEB-INF/views/member/login_need.jsp
      mav.setViewName("redirect:/question/msg.do"); 
    }*/
     
    return mav;
  }
  
  /**
   * 전체 목록 - 누구나 사용 가능
   * http://localhost:9093/question/list_all.do
   * @return
   */
  @RequestMapping(value="/question/list_all.do", method = RequestMethod.GET)
  public ModelAndView list_all(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    
    mav.setViewName("/question/list_all"); // /WEB-INF/views/question/list_all.jsp
    
    ArrayList<QnAVO> list = this.qnaProc.list_all();
   
    // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
    for (QnAVO qnaVO : list) {
      String title = qnaVO.getTitle();
      String quest = qnaVO.getQuest();
      
      title = Tool.convertChar(title);  // 특수 문자 처리
      quest = Tool.convertChar(quest); 
      
      qnaVO.setTitle(title);
      qnaVO.setQuest(quest);  
    }
    
    mav.addObject("list", list);
    
    return mav;
  }
  
  /**
   * 목록 + 검색 + 페이징 지원
   * 검색하지 않는 경우
   * http://localhost:9093/question/list_by_tcateno.do?tcateno=2&word=&now_page=1
   * 검색하는 경우
   * http://localhost:9093/question/list_by_tcateno.do?tcateno=2&word=탐험&now_page=1
   * 
   * @param questno
   * @param word
   * @param now_page
   * @return
   */
  @RequestMapping(value = "/question/list_by_tcateno.do", method = RequestMethod.GET)
  public ModelAndView list_by_tcateno(QnAVO qnaVO) {
    ModelAndView mav = new ModelAndView();
  
    // 검색 목록
    ArrayList<QnAVO> list = qnaProc.list_by_tcateno_search_paging(qnaVO);
    
    // for문을 사용하여 객체를 추출, Call By Reference 기반의 원본 객체 값 변경
    for (QnAVO vo : list) {
      String title = vo.getTitle();
      String quest = vo.getQuest();
      
      title = Tool.convertChar(title);  // 특수 문자 처리
      quest = Tool.convertChar(quest); 
      
      vo.setTitle(title);
      vo.setQuest(quest);  
  
    }
    
    mav.addObject("list", list);
  
    TcateVO tcateVO = tcateProc.read(qnaVO.getTcateno());
    mav.addObject("tcateVO", tcateVO);
  
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("tcateno", qnaVO.getTcateno());
    //hashMap.put("word", qnaVO.getWord());
    
    int search_count = this.qnaProc.search_count(hashMap);  // 검색된 레코드 갯수 ->  전체 페이지 규모 파악
    mav.addObject("search_count", search_count);
    
    /*
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17
     * 18 19 20 [다음]
     * @param cateno 카테고리번호
     * @param now_page 현재 페이지
     * @param word 검색어
     * @param list_file 목록 파일명
     * @return 페이징용으로 생성된 HTML/CSS tag 문자열
     */
    //String paging = qnaProc.pagingBox(qnaVO.getTcateno(), qnaVO.getNow_page(), qnaVO.getWord(), "list_by_tcateno.do", search_count);
    //mav.addObject("paging", paging);
  
    // mav.addObject("now_page", now_page);
    
    mav.setViewName("/question/list_by_tcateno");  // /question/list_by_tcateno.jsp
  
    return mav;
  }

  /**
   * 조회
   * http://localhost:9093/question/read.do?questno=1
   * @return
   */
  @RequestMapping(value="/question/read.do", method = RequestMethod.GET)
  public ModelAndView read(int questno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/question/read");
    
    QnAVO qnaVO = this.qnaProc.read(questno);
    
    String title = qnaVO.getTitle();
    String quest = qnaVO.getQuest();
    
    title = Tool.convertChar(title);  // 특수 문자 처리
    quest = Tool.convertChar(quest); 
    
    qnaVO.setTitle(title);
    qnaVO.setQuest(quest);  
    
    mav.addObject("qnaVO", qnaVO);
    
    TcateVO tcateVO = this.tcateProc.read(qnaVO.getTcateno());
    mav.addObject("tcateVO", tcateVO);
    
    return mav;
  }
  
  /**
   * 수정 폼
   * http://localhost:9093/question/update_quest.do?questno=1
   * 
   * @return
   */
  @RequestMapping(value = "/question/update_quest.do", method = RequestMethod.GET)
  public ModelAndView update_quest(HttpSession session, int questno) {
    ModelAndView mav = new ModelAndView();
    
    /*if (memberProc.isMember(session)) { // 멤버로 로그인한경우
      QnAVO qnaVO = this.qnaProc.read(questno);
      mav.addObject("qnaVO", qnaVO);
      
      TcateVO tcateVO = this.tcateProc.read(qnaVO.getTcateno());
      mav.addObject("tcateVO", tcateVO);
      
      mav.setViewName("/question/update_quest"); // /WEB-INF/views/question/update_quest.jsp

    } else {
      mav.addObject("url", "/member/login_need"); // /WEB-INF/views/member/login_need.jsp
      mav.setViewName("redirect:/question/msg.do"); 
    }*/

    return mav; // forward
  }
  
  /**
   * 수정 처리
   * http://localhost:9093/question/update_quest.do?questno=1
   * 
   * @return
   */
  @RequestMapping(value = "/question/update_quest.do", method = RequestMethod.POST)
  public ModelAndView update_quest(HttpSession session, QnAVO qnaVO) {
    ModelAndView mav = new ModelAndView();
    
    /*if (this.memberProc.isMember(session)) { // 회원 로그인 확인
      HashMap<String, Object> hashMap = new HashMap<String, Object>();
      hashMap.put("questno", qnaVO.getQuestno());
      
      this.qnaProc.update_quest(qnaVO); // 글수정  
      
      // mav 객체 이용
      mav.addObject("questno", qnaVO.getQuestno());
      mav.addObject("tcateno", qnaVO.getTcateno());
      mav.setViewName("redirect:/question/read.do"); // 페이지 자동 이동
    } else { // 정상적인 로그인이 아닌 경우 로그인 유도
      mav.addObject("url", "/member/login_need"); // /WEB-INF/views/admin/login_need.jsp
      mav.setViewName("redirect:/question/msg.do"); 
    }*/
    
    mav.addObject("now_page", qnaVO.getNow_page()); // POST -> GET: 데이터 분실이 발생함으로 다시하번 데이터 저장 ★     
    
    return mav; // forward
  }
  
  /**
   * 파일 삭제 폼
   * http://localhost:9093/question/delete.do?questno=1
   * 
   * @return
   */
  @RequestMapping(value = "/question/delete.do", method = RequestMethod.GET)
  public ModelAndView delete(HttpSession session, int questno) {
    ModelAndView mav = new ModelAndView();
    
    /*if (memberProc.isMember(session)) { // 회원 로그인 경우
      QnAVO qnaVO = this.qnaProc.read(questno);
      mav.addObject("qnaVO", qnaVO);
      
      TcateVO tcateVO = this.tcateProc.read(qnaVO.getTcateno());
      mav.addObject("tcateVO", tcateVO);
      
      mav.setViewName("/question/delete"); // /WEB-INF/views/question/delete.jsp
      
    } else {
      mav.addObject("url", "/member/login_need"); // /WEB-INF/views/member/login_need.jsp
      mav.setViewName("redirect:/question/msg.do"); 
    }*/


    return mav; // forward
  }
  
  /**
   * 삭제 처리 http://localhost:9093/question/delete.do
   * 
   * @return
   */
  @RequestMapping(value = "/question/delete.do", method = RequestMethod.POST)
  public ModelAndView delete(QnAVO qnaVO) {
    ModelAndView mav = new ModelAndView();
    
    // -------------------------------------------------------------------
    // 파일 삭제 시작
    // -------------------------------------------------------------------
    // 삭제할 파일 정보를 읽어옴.
    QnAVO QnAVO_read = qnaProc.read(qnaVO.getQuestno());
    
    this.qnaProc.delete(qnaVO.getQuestno()); // DBMS 삭제
        
    // -------------------------------------------------------------------------------------
    // 마지막 페이지의 마지막 레코드 삭제시의 페이지 번호 -1 처리
    // -------------------------------------------------------------------------------------    
    // 마지막 페이지의 마지막 10번째 레코드를 삭제후
    // 하나의 페이지가 3개의 레코드로 구성되는 경우 현재 9개의 레코드가 남아 있으면
    // 페이지수를 4 -> 3으로 감소 시켜야함, 마지막 페이지의 마지막 레코드 삭제시 나머지는 0 발생
    int now_page = qnaVO.getNow_page();
    
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("questno", qnaVO.getQuestno());
    
    /*if (qnaProc.search_count(hashMap) % Contents.RECORD_PER_PAGE == 0) {
      now_page = now_page - 1; // 삭제시 DBMS는 바로 적용되나 크롬은 새로고침등의 필요로 단계가 작동 해야함.
      if (now_page < 1) {
        now_page = 1; // 시작 페이지
      }
    }*/
    // -------------------------------------------------------------------------------------

    mav.addObject("questno", qnaVO.getQuestno());
    mav.addObject("now_page", now_page);
    mav.setViewName("redirect:/question/list_by_tcateno.do"); 
    
    return mav;
  }
  
  
}
