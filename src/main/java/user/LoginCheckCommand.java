package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conn.SecurityUtil;

public class LoginCheckCommand implements UserInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		
		UserDAO dao = new UserDAO();
		
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(pwd);
		
		UserVO vo = dao.getUserCheck(mid);
		
		if(vo.getPwd() == null) response.getWriter().write("0");
		else if(vo.getPwd().equals(pwd)) {
			response.getWriter().write("1");
			HttpSession session = request.getSession();
			session.setAttribute("sMid", vo.getMid());
			session.setAttribute("sUserLevel", vo.getUserLevel());
		}
		else response.getWriter().write("0");
	}

}
