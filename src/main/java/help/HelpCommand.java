package help;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HelpCommand implements HelpInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mid = (String) session.getAttribute("sMid");
		helpDAO dao = new helpDAO();
		ArrayList<helpVO> vos = dao.gethelpCheck(mid);
		request.setAttribute("vos", vos);
		request.setAttribute("tot", vos.size());
	}

}
