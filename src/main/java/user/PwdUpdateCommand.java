package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.SecurityUtil;

public class PwdUpdateCommand implements UserInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		
		UserDAO dao = new UserDAO();
		
		SecurityUtil security = new SecurityUtil();
		pwd = security.encryptSHA256(pwd);
		
		String res = dao.setPwdUpdate(mid,pwd);
		response.getWriter().write(res);
	}

}
