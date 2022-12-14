package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateCPCommand implements UserInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sMid = session.getAttribute("sMid")==null ? "" : (String)session.getAttribute("sMid");
		int res = 0;
		
		if(res == 0) {
			request.setAttribute("msg", "createCPOk");
			request.setAttribute("url", request.getContextPath()+"/createCP.us");
		}
		else {
			request.setAttribute("msg", "createCPNo");
			request.setAttribute("url", "http://192.168.50.79:9090/green2209J_17/");
		}
		
	}

}
