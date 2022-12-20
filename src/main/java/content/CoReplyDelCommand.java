package content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reply.replyDAO;

public class CoReplyDelCommand implements ContentInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		replyDAO dao = new replyDAO();
		int ridx = request.getParameter("ridx")==null? null : Integer.parseInt(request.getParameter("ridx"));
		String res = dao.setReplyDel(ridx);
		response.getWriter().write(res);
	}

}
