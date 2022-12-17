package admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.MyInfoCommand;

@WebServlet("*.ad")
public class AdminController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminInterface command = null;
		
		String viewPage = "/WEB-INF/admin";
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		HttpSession session = request.getSession();
		String userLevel = session.getAttribute("sUserLevel")==null ? "" : (String)session.getAttribute("sUserLevel");
		
		if(!userLevel.equals("관리자")) {
			request.setAttribute("msg", "noAdmin");
			request.setAttribute("url", request.getContextPath()+"/");
			viewPage = "/include/message.jsp";
		}
		
		if(cmd.equals("/adminMain")) {
			viewPage += "/adminMain.jsp";
		}
		else if(cmd.equals("/adLeft")){
			viewPage += "/adLeft.jsp";
		}
		else if(cmd.equals("/adContent")){
			command = new AdContentCommand();
			command.execute(request, response);
			viewPage += "/adContent.jsp";
		}
		else if(cmd.equals("/adminHeader")){
			viewPage += "/adminHeader.jsp";
		}
		else if(cmd.equals("/adUserList")){
			command = new AdUserListCommand();
			command.execute(request, response);
			viewPage += "/adUserList.jsp";
		}
		else if(cmd.equals("/adUserSearch")){
			command = new AdUserSearchCommand();
			command.execute(request, response);
			viewPage += "/adUserList.jsp";
		}
		else if(cmd.equals("/adUserDel")){
			command = new AdUserDelCommand();
			command.execute(request, response);
			return;
		}
		else if(cmd.equals("/adCPList")){
			command = new AdCPListCommand();
			command.execute(request, response);
			viewPage += "/adCPList.jsp";
		}
		else if(cmd.equals("/adCPSearch")){
			command = new AdCPSearchCommand();
			command.execute(request, response);
			viewPage += "/adCPList.jsp";
		}
		else if(cmd.equals("/adCPUpdate")){
			command = new AdCPUpdateCommand();
			command.execute(request, response);
			return;
		}
		else if(cmd.equals("/adCPDel")){
			command = new AdCPDelCommand();
			command.execute(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}
