package admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import help.helpDAO;
import help.helpVO;
import pds.pdsDAO;
import pds.pdsVO;
import reply.replyDAO;
import reply.replyVO;
import report.reportDAO;
import report.reportVO;
import user.UserDAO;
import user.UserVO;

public class AdContentCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pdsDAO pdsDAO = new pdsDAO();
		UserDAO userDAO = new UserDAO();
		replyDAO replyDAO = new replyDAO();
		helpDAO helpDAO = new helpDAO();
		reportDAO reportDAO = new reportDAO();
		
		ArrayList<pdsVO> pdsVOS = pdsDAO.getFileInfo();
		int totFSize = 0;
		for(int i=0; i<pdsVOS.size(); i++) totFSize += pdsVOS.get(i).getfSize();
		request.setAttribute("totFSize", totFSize);
		request.setAttribute("totFile", pdsVOS.size());
		request.setAttribute("pdsVOS", pdsVOS);
		
		ArrayList<UserVO> userVOS = userDAO.getUserList(0,5);
		request.setAttribute("userTot", userVOS.size());
		request.setAttribute("userVOS", userVOS);
		
		ArrayList<UserVO> companyVOS = userDAO.getCPList(0,5);
		request.setAttribute("companyTot", companyVOS.size());
		request.setAttribute("companyVOS", companyVOS);
		
		ArrayList<replyVO> replyVOS = replyDAO.getReplyList(0, 5);
		request.setAttribute("replyTot", replyVOS.size());
		request.setAttribute("replyVOS", replyVOS);
		
		ArrayList<helpVO> helpVOS = helpDAO.getHelpAllList();
		int helpCnt = helpDAO.getOffHelpCnt();
		request.setAttribute("helpCnt", helpCnt);
		request.setAttribute("helpVOS", helpVOS);
		
		ArrayList<reportVO> reportVOS = reportDAO.getreportList(0,5);
		request.setAttribute("reportTot", reportVOS.size());
		request.setAttribute("reportVOS", reportVOS);
		
	}

}
