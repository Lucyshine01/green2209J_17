package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import conn.SecurityUtil;

public class CreateUserOkCommand implements UserInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/data/logo");
		int maxSize = 1024 * 1024 * 10;	// 최대용량 10MByte
		String encoding = "UTF-8";
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy());
		String sw = multipartRequest.getParameter("sw")==null ? "" : multipartRequest.getParameter("sw");
		
		UserVO vo = new UserVO();
		UserDAO dao = new UserDAO();
		
		String mid = multipartRequest.getParameter("mid")==null ? "" : multipartRequest.getParameter("mid");
		String pwd = multipartRequest.getParameter("pwd")==null ? "" : multipartRequest.getParameter("pwd");
		String email = multipartRequest.getParameter("email")==null ? "" : multipartRequest.getParameter("email");
		String birth = multipartRequest.getParameter("birth")==null ? "" : multipartRequest.getParameter("birth");
		String tel = multipartRequest.getParameter("tel")==null ? "" : multipartRequest.getParameter("tel");
		SecurityUtil security = new SecurityUtil();
		
		pwd = security.encryptSHA256(pwd);
		
		vo.setMid(mid);
		vo.setPwd(pwd);
		vo.setEmail(email);
		vo.setBirth(birth);
		vo.setTel(tel);
		String res = dao.createUser(vo);
		if(sw.equals("0")) {
			if(res.equals("1")) {
				request.setAttribute("msg", "createUserOk");
				request.setAttribute("url", "http://192.168.50.79:9090/green2209J_17/");
			}
			else {
				request.setAttribute("msg", "createUserNo");
				request.setAttribute("url", request.getContextPath()+"/create.us");
			}
			
		}
		else if(sw.equals("1") && res.equals("1")){
			String name = multipartRequest.getParameter("name")==null ? "" : multipartRequest.getParameter("name");
			String cpName = multipartRequest.getParameter("cpName")==null ? "" : multipartRequest.getParameter("cpName");
			String cpAddr = multipartRequest.getParameter("cpAddr")==null ? "" : multipartRequest.getParameter("cpAddr");
			String cpHomePage = multipartRequest.getParameter("cpHomePage")==null ? "" : multipartRequest.getParameter("cpHomePage");
			String cpIntro = multipartRequest.getParameter("cpIntro")==null ? "" : multipartRequest.getParameter("cpIntro");
			String strExp = multipartRequest.getParameter("strExp")==null ? "" : multipartRequest.getParameter("strExp");
			String filesystemName = multipartRequest.getFilesystemName("cpImg");
			int imgSize = multipartRequest.getParameter("imgSize")==null ? 0 : Integer.parseInt(multipartRequest.getParameter("imgSize"));
			vo.setName(name);
			vo.setCpName(cpName);
			vo.setCpAddr(cpAddr);
			vo.setCpImg(filesystemName);
			vo.setCpHomePage(cpHomePage);
			vo.setCpIntro(cpIntro);
			vo.setCpExp(strExp);
			vo.setImgSize(imgSize);
			res = dao.createCompany(vo);
			
			if(res.equals("1")) {
				request.setAttribute("msg", "createCompanyOk");
				request.setAttribute("url", "http://192.168.50.79:9090/green2209J_17/");
			}
			else {
				request.setAttribute("msg", "createCompanyNo");
				request.setAttribute("url", request.getContextPath()+"/create.us");
			}
		}
		else {
			request.setAttribute("msg", "createUserNo");
			request.setAttribute("url", request.getContextPath()+"/create.us");
		}
		
	}

}
