package user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("*.us")
public class UserController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserInterface command = null;
		
		String viewPage = "/WEB-INF/create";
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		HttpSession session = request.getSession();
		String sMid = session.getAttribute("sMid")==null ? "" : (String)session.getAttribute("sMid");
		if(!sMid.equals("") && sMid != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		}
		
		if(cmd.equals("/create")) {
			viewPage += "/createUser.jsp";
		}
		else if(cmd.equals("/createCP")) {
			if(sMid.equals("") || sMid == null) viewPage += "/createUser.jsp";
			else {
				String userLevel = (String)session.getAttribute("sUserLevel");
				if(userLevel.equals("일반")) {
					viewPage += "/createCP.jsp";
				}
				else {
					viewPage = "http://192.168.50.79:9090/green2209J_17/";
				}
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}
