package admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import help.helpDAO;
import help.helpVO;
import reply.replyDAO;
import reply.replyVO;

public class AdhelpSearchCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		helpDAO dao = new helpDAO();
		String searching = request.getParameter("searching")==null ? "" : request.getParameter("searching");
		String searchItem = request.getParameter("searchItem")==null ? "" : request.getParameter("searchItem");
		
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null? 5 : Integer.parseInt(request.getParameter("pageSize"));
		int totRecCnt = dao.getHelpSearchCnt(searching, searchItem);
		int totPage = (totRecCnt % pageSize)==0 ? totRecCnt / pageSize : (totRecCnt / pageSize)+1;
		int stratIndexNo = (pag-1) * pageSize;
		int curScrStartNo = totRecCnt - stratIndexNo;
		
		int blockSize = 3;
		int curBlock = (pag - 1) / blockSize;
		int lastBlock = (totPage-1) / blockSize;
		
		ArrayList<helpVO> vos = dao.getHelpSearchList(searching, searchItem, stratIndexNo, pageSize);
		request.setAttribute("vos", vos);
		request.setAttribute("helpTot", totRecCnt);
		int answerTot = 0;
		int noAnswerTot = 0;
		for(int i=0; i<vos.size(); i++) {
			if(vos.get(i).getConf().equals("off")) noAnswerTot++;
			else answerTot++;
		}
		request.setAttribute("answerTot", answerTot);
		request.setAttribute("noAnswerTot", noAnswerTot);
		
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
