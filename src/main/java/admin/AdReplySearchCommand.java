package admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reply.replyDAO;
import reply.replyVO;

public class AdReplySearchCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		replyDAO dao = new replyDAO();
		String searching = request.getParameter("searching")==null ? "" : request.getParameter("searching");
		String searchItem = request.getParameter("searchItem")==null ? "" : request.getParameter("searchItem");
		
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null? 5 : Integer.parseInt(request.getParameter("pageSize"));
		int totRecCnt = dao.getReplySearchCnt(searching, searchItem);
		int totPage = (totRecCnt % pageSize)==0 ? totRecCnt / pageSize : (totRecCnt / pageSize)+1;
		int stratIndexNo = (pag-1) * pageSize;
		int curScrStartNo = totRecCnt - stratIndexNo;
		
		int blockSize = 3;
		int curBlock = (pag - 1) / blockSize;
		int lastBlock = (totPage-1) / blockSize;
		System.out.println(stratIndexNo +","+pageSize);
		ArrayList<replyVO> vos = dao.getReplySearchList(searching, searchItem, stratIndexNo, pageSize);
		request.setAttribute("vos", vos);
		request.setAttribute("replyTot", totRecCnt);
		
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);
		
		request.setAttribute("pag", pag);
		request.setAttribute("totPage", totPage);
		request.setAttribute("stratIndexNo", stratIndexNo);
		request.setAttribute("curScrStartNo", curScrStartNo);
		
		request.setAttribute("searching", searching);
		request.setAttribute("searchItem", searchItem);
	}

}
