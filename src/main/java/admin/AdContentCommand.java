package admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pds.pdsDAO;
import pds.pdsVO;
import user.UserDAO;
import user.UserVO;

public class AdContentCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pdsDAO pdsDAO = new pdsDAO();
		UserDAO userDAO = new UserDAO();
		
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
		
	}

}
