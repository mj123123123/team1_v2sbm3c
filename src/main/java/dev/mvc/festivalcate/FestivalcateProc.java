package dev.mvc.festivalcate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Controller가 사용하는 이름 
@Component("dev.mvc.festivalcate.FestivalcateProc")
public class FestivalcateProc implements FestivalcateProcInter {
	@Autowired // FestivalfestivalcateDAOInter interface를 구현한 클래스의 객체를 만들어 자동으로 할당해라.
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

	@Override
	public FestivalcateVO read(int festivalcateno) {
		FestivalcateVO festivalcateVO = this.festivalcateDAO.read(festivalcateno);
		return festivalcateVO;
	}

	@Override
	public int update(FestivalcateVO festivalVO) {
		int cnt = this.festivalcateDAO.update(festivalVO);

		return cnt;
	}

	@Override
	public int delete(int festivalfestivalcateno) {
		int cnt = this.festivalcateDAO.delete(festivalfestivalcateno);

		return cnt;
	}

	@Override
	public int update_seqno_forward(int festivalcateno) {
		int cnt = this.festivalcateDAO.update_seqno_forward(festivalcateno);
		return cnt;
	}

	@Override
	public int update_seqno_backward(int festivalcateno) {
		int cnt = this.festivalcateDAO.update_seqno_backward(festivalcateno);
		return cnt;
	}

	@Override
	public int update_visible_y(int festivalcateno) {
		int cnt = this.festivalcateDAO.update_visible_y(festivalcateno);
		return cnt;
	}

	@Override
	public int update_visible_n(int festivalcateno) {
		int cnt = this.festivalcateDAO.update_visible_n(festivalcateno);
		return cnt;
	}

	@Override
	public ArrayList<FestivalcateVO> list_all_y() {
		ArrayList<FestivalcateVO> list = this.festivalcateDAO.list_all_y();
		return list;
	}

}
