package content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reply.replyDAO;
import reply.replyVO;
import report.reportDAO;

public class CoReplyReportCommand implements ContentInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		reportDAO reportDAO = new reportDAO();
		replyDAO replyDAO = new replyDAO();
		int ridx = request.getParameter("ridx")==null? null : Integer.parseInt(request.getParameter("ridx"));
		String mid = (String)session.getAttribute("sMid");
		
		replyVO replyVO = replyDAO.getReplyRidx(ridx);
		String res = "1";
		res = reportDAO.getReplyReport(ridx,mid);
		if(!res.equals("0")) res = reportDAO.setReplyReport(replyVO,mid);
		response.getWriter().write(res);
	}

}
