package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IdOverCheckCommand implements UserInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		vo = dao.getUserCheck(mid);
		
		if(vo.getMid() == null) response.getWriter().write("0");
		else response.getWriter().write("1");
	}

}
