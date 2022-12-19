package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import help.helpDAO;

public class AdhelpAnswerCommand implements AdminInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hidx = request.getParameter("hidx")==null ? 0 : Integer.parseInt(request.getParameter("hidx"));
		String answer = request.getParameter("answer")==null ? "" : request.getParameter("answer");
		
		helpDAO dao = new helpDAO();
		
		String res = dao.setAnswerHelp(hidx,answer);
		response.getWriter().write(res);
		
	}

}
