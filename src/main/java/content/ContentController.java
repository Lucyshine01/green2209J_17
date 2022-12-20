package content;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.AdReplyDelCommand;
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
		else if(cmd.equals("/CPSearch")) {
			command = new CPSearchCommand();
			command.execute(request, response);
			viewPage += "/CPList.jsp";
		}
		else if(sMid.equals("")) {
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
		// 로그인후 접근 가능
		else if(cmd.equals("/CPdivisionList")) {
			command = new CPdivisionListCommand();
			command.execute(request, response);
			viewPage += "/CPList.jsp";
		}
		else if(cmd.equals("/CPContentView")) {
			command = new CPContentViewCommand();
			command.execute(request, response);
			viewPage += "/CPContentView.jsp";
		}
		else if(cmd.equals("/submitReply")) {
			command = new SubmitReplyCommand();
			command.execute(request, response);
			return;
		}
		else if(cmd.equals("/coReplyDel")){
			command = new CoReplyDelCommand();
			command.execute(request, response);
			return;
		}
		else if(cmd.equals("/coReplyReport")){
			command = new CoReplyReportCommand();
			command.execute(request, response);
			return;
		}
		// 업체전용
		else if(!userLevel.equals("업체")) {
			request.setAttribute("msg", "noCompany");
			request.setAttribute("url", request.getContextPath()+"/");
			viewPage = "/include/message.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
			return;
		}
		else if(cmd.equals("/CPInfo")) {
			command = new CPInfoCommand();
			command.execute(request, response);
			viewPage += "/CPInfo.jsp";
		}
		else if(cmd.equals("/inputCPImg")) {
			command = new PlusImgFormCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(cmd.equals("/imgDel")) {
			command = new ImgDelCommand();
			command.execute(request, response);
			return;
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}
