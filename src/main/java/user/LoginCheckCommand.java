package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conn.SecurityUtil;

public class LoginCheckCommand implements UserInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		String rememId = request.getParameter("rememId")==null ? "" : request.getParameter("rememId");
		
		Cookie cookieMid;
		if(rememId.equals("on")) {
			cookieMid = new Cookie("cookieMid", mid);
			cookieMid.setMaxAge(60*60*24*7);
		}
		else {
			cookieMid = new Cookie("cookieMid", null);
			cookieMid.setMaxAge(0);
		}
		response.addCookie(cookieMid);
		
		UserDAO dao = new UserDAO();
		
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(pwd);
		
		UserVO vo = dao.getUserCheck(mid);
		
		if(vo.getPwd() == null) response.getWriter().write("0");
		else if(vo.getPwd().equals(pwd)) {
			if(request.getHeader("referer") != null) {
				response.getWriter().write("1" + request.getHeader("referer"));
			}
			else {
				response.getWriter().write("1" + request.getContextPath() + "/");
			}
			HttpSession session = request.getSession();
			session.setAttribute("sMid", vo.getMid());
			session.setAttribute("sUserLevel", vo.getUserLevel());
			
			vo = dao.getCPCheck(mid);
			if(vo.getAct() != null) session.setAttribute("sAct", vo.getAct());
		}
		else response.getWriter().write("0");
	}

}
