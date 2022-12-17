package content;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.MyInfoCommand;

@WebServlet("*.co")
public class ContentController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContentInterface command = null;
		
		String viewPage = "/WEB-INF/content";
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		HttpSession session = request.getSession();
		String sMid = session.getAttribute("sMid")==null ? "" : (String)session.getAttribute("sMid");
		String userLevel = session.getAttribute("sUserLevel")==null ? "" : (String)session.getAttribute("sUserLevel");
		
		// 비로그인 접근 가능
		if(cmd.equals("/CPList")) {
			command = new CPListCommand();
			command.execute(request, response);
			viewPage += "/CPList.jsp";
		}
		else if(sMid.equals("")) {
			request.setAttribute("msg", "noSession");
			request.setAttribute("url", request.getContextPath()+"/");
			viewPage = "/include/message.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		else if(!userLevel.equals("업체")) {
			request.setAttribute("msg", "noCompany");
			request.setAttribute("url", request.getContextPath()+"/");
			viewPage = "/include/message.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		else if(cmd.equals("/CPInfo")) {
			command = new CPInfoCommand();
			command.execute(request, response);
			viewPage += "/CPInfo.jsp";
		}
		else if(cmd.equals("/plusImgForm")) {
			command = new PlusImgFormCommand();
			command.execute(request, response);
			viewPage += "/CPInfo.jsp";
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}
