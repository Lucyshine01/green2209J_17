package help;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelpInputCommand implements HelpInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title")==null ? "" : request.getParameter("title");
		String content = request.getParameter("content")==null ? "" : request.getParameter("content");
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		content = content.replaceAll("\n", "<br/>");
		
		helpDAO dao = new helpDAO();
		helpVO vo = new helpVO();
		
		if(title.length() > 50) {
			System.out.println("타이틀 길이가 너무 깁니다. -- (HelpInputCommand)");
			response.getWriter().write("0");
			return;
		}
		
		vo.setTitle(title);
		vo.setContent(content);
		vo.setMid(mid);
		
		String res = dao.setInputHelp(vo);
		response.getWriter().write(res);
	}

}
