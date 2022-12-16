package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import pds.pdsDAO;
import pds.pdsVO;

public class CompanyFormCommand implements UserInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String realPath = request.getServletContext().getRealPath("/") + "data/logo";
		int maxSize = 1024 * 1024 * 10;	// 최대용량 10MByte
		String encoding = "UTF-8";
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy());
		String sw = multipartRequest.getParameter("sw")==null ? "" : multipartRequest.getParameter("sw");
		
		UserVO vo = new UserVO();
		UserDAO dao = new UserDAO();
		
		String mid = (String)session.getAttribute("sMid");
		String name = multipartRequest.getParameter("name")==null ? null : multipartRequest.getParameter("name");
		String cpName = multipartRequest.getParameter("cpName")==null ? "" : multipartRequest.getParameter("cpName");
		String cpAddr = multipartRequest.getParameter("cpAddr")==null ? "" : multipartRequest.getParameter("cpAddr");
		String cpHomePage = multipartRequest.getParameter("cpHomePage")==null ? "" : multipartRequest.getParameter("cpHomePage");
		String cpIntro = multipartRequest.getParameter("cpIntro")==null ? "" : multipartRequest.getParameter("cpIntro");
		String strExp = multipartRequest.getParameter("strExp")==null ? "" : multipartRequest.getParameter("strExp");
		String filesystemName = multipartRequest.getFilesystemName("cpImg");
		int imgSize = multipartRequest.getParameter("imgSize")==null ? 0 : Integer.parseInt(multipartRequest.getParameter("imgSize"));
		if(filesystemName == null) filesystemName = "noLogo.png";
		vo.setName(name);
		vo.setCpName(cpName);
		vo.setCpAddr(cpAddr);
		vo.setCpImg(filesystemName);
		vo.setCpHomePage(cpHomePage);
		vo.setCpIntro(cpIntro);
		vo.setCpExp(strExp);
		vo.setImgSize(imgSize);
		vo.setMid(mid);
		String res = dao.createCompany(vo);
		
		if(res.equals("1")) {
			if(!filesystemName.equals("noLogo.png")) {
				pdsVO pdsVo = new pdsVO();
				pdsDAO pdsDao = new pdsDAO();
				String fileoriginalName = multipartRequest.getOriginalFileName("cpImg");
				pdsVo.setfOriName(fileoriginalName);
				pdsVo.setfSysName(filesystemName);
				pdsVo.setfSize(imgSize);
				pdsVo.setMid(mid);
				pdsDao.fileInput(pdsVo);
			}
			dao.setUserLevelUpdate(mid);
			
			request.setAttribute("msg", "createCompanyOk");
			request.setAttribute("url", request.getContextPath()+"/");
		}
		else {
			request.setAttribute("msg", "createCompanyNo");
			request.setAttribute("url", request.getContextPath()+"/create.us");
		}
	}

}
