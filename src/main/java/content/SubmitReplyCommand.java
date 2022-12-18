package content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import reply.replyDAO;
import reply.replyVO;

public class SubmitReplyCommand implements ContentInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String boardIdx = request.getParameter("boardIdx")==null ? "" : request.getParameter("boardIdx");
		double rating = request.getParameter("rating")==null ? 0.0 : Double.parseDouble(request.getParameter("rating"));
		String content = request.getParameter("content")==null ? "" : request.getParameter("content");
		
		content = content.replaceAll("\n", "<br/>");
		
		replyDAO dao = new replyDAO();
		replyVO vo = new replyVO();
		vo.setMid(mid);
		vo.setBoardIdx(boardIdx);
		vo.setRating(rating);
		vo.setContent(content);
		String res = dao.setReply(vo);
		response.getWriter().write(res);
	}

}
