package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;

public class AdUserDelCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		int uidx = request.getParameter("uidx")==null? 0 : Integer.parseInt(request.getParameter("uidx"));
		String res = dao.setUserDel(uidx);
		response.getWriter().write(res);
	}

}
