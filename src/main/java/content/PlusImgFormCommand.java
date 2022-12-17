package content;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import pds.pdsDAO;
import pds.pdsVO;
import user.UserDAO;
import user.UserVO;

public class PlusImgFormCommand implements ContentInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pdsDAO pdsDAO = new pdsDAO();
		pdsVO pdsVO = null;
		HttpSession session = request.getSession();
		String realPath = request.getServletContext().getRealPath("/data/picture");
		int maxSize = 1024 * 1024 * 10;	// 서버에 저장할 최대용량을 10MByte로 한다.(1회 저장)
		String encoding = "UTF-8";

		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy());
		
		String mid = (String)session.getAttribute("sMid");
		
		// 업로드 시킨 멀티파일에 대한 처리(올린 파일명과 서버에 저장된 파일명을 '/'로 결합해서 준비한다.)
		Enumeration fileNames = multipartRequest.getFileNames();
		String originalFileName = "";
		String filesystemName = "";
		String cpIntroImg = "";
		int cnt = 0;
		while (fileNames.hasMoreElements()) {
			String file = (String)fileNames.nextElement();
			if(multipartRequest.getFilesystemName(file) != null) {
				originalFileName = multipartRequest.getOriginalFileName(file);
				filesystemName = multipartRequest.getFilesystemName(file);
				int fSize = Integer.parseInt(multipartRequest.getParameter("fileSize"+cnt));
				pdsVO = new pdsVO();
				pdsVO.setfOriName(originalFileName);
				pdsVO.setfSysName(filesystemName);
				pdsVO.setfSize(fSize);
				pdsVO.setMid(mid);
				pdsDAO.fileInput(pdsVO);
				
				cpIntroImg += filesystemName + "/";
				cnt++;
			}
		}
		cpIntroImg = cpIntroImg.substring(0,cpIntroImg.lastIndexOf("/"));
		
		UserDAO userDAO = new UserDAO();
		UserVO vo = userDAO.getCPCheck(mid);
		if(!vo.getCpIntroImg().equals("")) cpIntroImg = vo.getCpIntroImg() + "/" + cpIntroImg;
		userDAO.setCPIntroImg(cpIntroImg,mid);
		
		request.setAttribute("msg", "inputImg");
		request.setAttribute("url", request.getContextPath()+"/CPInfo.co");
	}

}
