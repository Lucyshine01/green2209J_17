package content;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reply.replyDAO;
import reply.replyVO;
import user.UserVO;

public class CPContentViewCommand implements ContentInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		ContentDAO dao = new ContentDAO();
		
		UserVO vo = dao.getCpInfo(mid);
		request.setAttribute("vo", vo);
		
		
		replyDAO replyDAO = new replyDAO();
		String boardIdx = "c" + vo.getCidx();
		
		String sViewCnt = (String) session.getAttribute("sViewCnt");
		if(sViewCnt == null) {
			sViewCnt = boardIdx;
			dao.setPlusViewCP(vo.getCidx());
		}
		else {
			if(!sViewCnt.contains(boardIdx)) {
				sViewCnt += boardIdx;
				dao.setPlusViewCP(vo.getCidx());
			}
		}
		session.setAttribute("sViewCnt", sViewCnt);
		
		ArrayList<replyVO> replyVOS = replyDAO.getReply(boardIdx);
		request.setAttribute("replyVOS", replyVOS);
		request.setAttribute("replyTot", replyVOS.size());
		double avgRating = 0.0;
		if(replyVOS.size()>= 1) {
			int cnt = 0;
			for(int i=0; i<replyVOS.size(); i++) {
				if(replyVOS.get(i).getRating() != 0.0) {
					cnt++;
					avgRating += replyVOS.get(i).getRating();
				}
			}
			avgRating = avgRating / cnt;
		}
		request.setAttribute("avgRating", avgRating);
	}

}
