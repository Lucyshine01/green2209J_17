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
		String userLevel = session.getAttribute("sUserLevel")==null ? "" : (String)session.getAttribute("sUserLevel");
		
		if(cmd.equals("/create")) {
			if(!sMid.equals("") && sMid != null) {
				request.setAttribute("msg", "haveSession");
				request.setAttribute("url", request.getContextPath()+"/");
				viewPage = "/include/message.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
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
				if(userLevel.equals("일반")) {
					String act = session.getAttribute("sAct")==null ? "" : (String)session.getAttribute("sAct");
					if(act.equals("off")) {
						request.setAttribute("msg", "createCPNo3");
						request.setAttribute("url", request.getContextPath()+"/");
						viewPage = "/include/message.jsp";
					}
					else viewPage += "/createCP.jsp";
				}
				else {
					request.setAttribute("msg", "createCPNo2");
					request.setAttribute("url", request.getContextPath()+"/");
					viewPage = "/include/message.jsp";
				}
			}
		}
		else if(cmd.equals("/createUserOk")){
			command = new CreateUserOkCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		else if(cmd.equals("/loginCheck")){
			command = new LoginCheckCommand();
			command.execute(request, response);
			return;
		}
		else if(sMid.equals("")) {
			request.setAttribute("msg", "noSession");
			request.setAttribute("url", request.getContextPath()+"/");
			viewPage = "/include/message.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		else if(cmd.equals("/createCPOk")){
			command = new CompanyFormCommand();
			command.execute(request, response);
			viewPage = "/include/message.jsp";
		}
		
		if(viewPage.equals("/WEB-INF/create")) viewPage = "/WEB-INF/user";
		if(cmd.equals("/logoutCheck")){
			command = new LogoutCheckCommand();
			command.execute(request, response);
			return;
		}
		else if(cmd.equals("/myInfo")){
			command = new MyInfoCommand();
			command.execute(request, response);
			viewPage += "/myInfo.jsp";
		}
		else if(cmd.equals("/changePwd")){
			viewPage += "/changePwd.jsp";
		}
		else if(cmd.equals("/oldPwdCheck")){
			command = new OldPwdCheckCommand();
			command.execute(request, response);
			return;
		}
		else if(cmd.equals("/pwdUpdate")){
			command = new PwdUpdateCommand();
			command.execute(request, response);
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}
}
