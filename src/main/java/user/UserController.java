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
		String userLevel = (String)session.getAttribute("sUserLevel");
		
		if(cmd.equals("/create")) {
			if(!sMid.equals("") && sMid != null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/");
				dispatcher.forward(request, response);
			}
			else viewPage += "/createUser.jsp";
		}
		else if(cmd.equals("/idOverCheck")){
			command = new IdOverCheckCommand();
			command.execute(request, response);
			return;
		}
		else if(cmd.equals("/createCP")) {
			if(sMid.equals("") || sMid == null) {
				request.setAttribute("msg", "createCPNo1");
				request.setAttribute("url", request.getContextPath()+"/create.us");
				viewPage = "/include/message.jsp";
			}
			else {
				if(userLevel.equals("일반")) viewPage += "/createCP.jsp";
				else {
					request.setAttribute("msg", "createCPNo2");
					request.setAttribute("url", "http://192.168.50.79:9090/green2209J_17/");
					viewPage = "/include/message.jsp";
				}
			}
		}
		else if(cmd.equals("/createUserOk")){
			command = new CreateUserOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}
