package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;

public class AdCPDelCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		int cidx = request.getParameter("cidx")==null? null : Integer.parseInt(request.getParameter("cidx"));
		String mid = request.getParameter("mid")==null? "" : request.getParameter("mid");
		String res = dao.setCPDel(cidx);
		if(res.equals("1")) dao.setUserLevelDownUpdate(mid);
		response.getWriter().write(res);
	}

}
