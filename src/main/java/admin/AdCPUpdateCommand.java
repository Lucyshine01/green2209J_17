package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;

public class AdCPUpdateCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		int cidx = request.getParameter("cidx")==null ? 0 : Integer.parseInt(request.getParameter("cidx"));
		String act = request.getParameter("act")==null ? "" : request.getParameter("act");
		String res = dao.setCPOn(cidx,act);
		response.getWriter().write(res);
		
	}

}
