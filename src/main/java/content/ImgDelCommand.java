package content;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pds.pdsDAO;
import user.UserDAO;
import user.UserVO;

public class ImgDelCommand implements ContentInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String imgName = request.getParameter("imgName")==null ? "" : request.getParameter("imgName");
		
		UserDAO userDAO = new UserDAO();
		pdsDAO pdsDAO = new pdsDAO();
		
		String res = "0";
		try {
			String realPath = request.getServletContext().getRealPath("/data/picture/");
			new File(realPath+imgName).delete();
			res = "1";
		} catch (Exception e) {
			System.out.println("파일 삭제 오류 : " + e.getMessage());
			response.getWriter().write(res);
			return;
		}
		
		UserVO userVO = userDAO.getCPCheck(mid);
		String cpIntroImg = userVO.getCpIntroImg();
		
		if(cpIntroImg.contains(imgName + "/")) {
			cpIntroImg = cpIntroImg.replace(imgName + "/", "");
		}
		else if(cpIntroImg.contains("/" + imgName)) {
			cpIntroImg = cpIntroImg.replace("/" + imgName, "");
		}
		else cpIntroImg = cpIntroImg.replace(imgName, "");
		userDAO.setCPIntroImg(cpIntroImg, mid);
		pdsDAO.fileDel(imgName);
		
		response.getWriter().write(res);
		
	}

}
