package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import conn.SecurityUtil;
import pds.pdsDAO;
import pds.pdsVO;

public class CreateUserOkCommand implements UserInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/") + "data/logo";
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
		
		if(mid.equals("") || pwd.equals("") || email.equals("") || birth.equals("") || tel.equals("")) {
			request.setAttribute("msg", "createUserNo");
			request.setAttribute("url", request.getContextPath()+"/create.us");
			return;
		}
		String regId = "^([a-zA-Z0-9]){6,20}$";  
		String regPwd = "^([!@#$%^&+=<>?,\\./\\*()_-]?[a-zA-Z0-9]){6,20}$";
		String regEmail = "^([-_.]?[0-9a-zA-Z]){4,20}@+([-_.]?[0-9a-zA-Z]){4,20}.+[a-zA-Z]{2,3}$";
		String regTel = "^([0-9]){2,3}-+([0-9]){3,4}-+([0-9]){3,4}$";
		if(!mid.matches(regId)) {
			System.out.println("아이디 정규식 오류");
			request.setAttribute("msg", "createUserNo");
			request.setAttribute("url", request.getContextPath()+"/create.us");
			return;
		}
		else if(!pwd.matches(regPwd)){
			System.out.println("비밀번호 정규식 오류");
			request.setAttribute("msg", "createUserNo");
			request.setAttribute("url", request.getContextPath()+"/create.us");
			return;
		}
		else if(!email.matches(regEmail)){
			System.out.println("이메일 정규식 오류");
			request.setAttribute("msg", "createUserNo");
			request.setAttribute("url", request.getContextPath()+"/create.us");
			return;
		}
		else if(!tel.matches(regTel)){
			System.out.println("전화번호 정규식 오류");
			request.setAttribute("msg", "createUserNo");
			request.setAttribute("url", request.getContextPath()+"/create.us");
			return;
		}
		
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
				request.setAttribute("url", request.getContextPath()+"/");
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
				pdsVO pdsVo = new pdsVO();
				pdsDAO pdsDao = new pdsDAO();
				
				String fileoriginalName = multipartRequest.getOriginalFileName("cpImg");
				pdsVo.setfOriName(fileoriginalName);
				pdsVo.setfSysName(filesystemName);
				pdsVo.setfSize(imgSize);
				pdsVo.setMid(mid);
				
				pdsDao.fileInput(pdsVo);
				
				request.setAttribute("msg", "createCompanyOk");
				request.setAttribute("url", request.getContextPath()+"/");
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
