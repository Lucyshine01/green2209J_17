package help;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import content.CPContentViewCommand;
import user.MyInfoCommand;

@WebServlet("*.he")
public class helpController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HelpInterface command = null;
		
		String viewPage = "/WEB-INF/help";
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		HttpSession session = request.getSession();
		String sMid = session.getAttribute("sMid")==null ? "" : (String)session.getAttribute("sMid");
		String userLevel = session.getAttribute("sUserLevel")==null ? "" : (String)session.getAttribute("sUserLevel");
		
		if(sMid.equals("")) {
			request.setAttribute("msg", "noLogin");
			// 이전페이지 주소확인 (사파리 작동x)
			if(request.getHeader("referer") != null)
				request.setAttribute("url", request.getHeader("referer"));
			else request.setAttribute("url", request.getContextPath() + "/");
			viewPage = "/include/message.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
			return;
		}
		else if(cmd.equals("/help")) {
			command = new HelpCommand();
			command.execute(request, response);
			viewPage += "/help.jsp";
		}
		else if(cmd.equals("/helpInput")) {
			command = new HelpInputCommand();
			command.execute(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}
