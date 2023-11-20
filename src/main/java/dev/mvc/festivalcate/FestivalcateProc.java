package dev.mvc.festivalcate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Controller가 사용하는 이름 
@Component("dev.mvc.festivalcate.FestivalcateProc")
public class FestivalcateProc implements FestivalcateProcInter {
	@Autowired // FestivalcateDAOInter interface를 구현한 클래스의 객체를 만들어 자동으로 할당해라.
	private FestivalcateDAOInter festivalcateDAO;

	@Override
	public int create(FestivalcateVO festivalcateVO) {
		int cnt = this.festivalcateDAO.create(festivalcateVO);

		return cnt;
	}

	@Override
	public ArrayList<FestivalcateVO> list_all() {
		ArrayList<FestivalcateVO> list = this.festivalcateDAO.list_all();

		return list;
	}

}
