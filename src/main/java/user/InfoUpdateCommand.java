package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InfoUpdateCommand implements UserInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		UserVO vo = new UserVO();
		int uidx = request.getParameter("uidx")==null ? 0 : Integer.parseInt(request.getParameter("uidx"));
		String email = request.getParameter("email")==null ? "" : request.getParameter("email");
		String birth = request.getParameter("birth")==null ? "" : request.getParameter("birth");
		String tel = request.getParameter("tel")==null ? "" : request.getParameter("tel");
		
		String regEmail = "^([-_.]?[0-9a-zA-Z]){4,20}@+([-_.]?[0-9a-zA-Z]){4,20}.+[a-zA-Z]{2,3}$";
		String regTel = "^([0-9]){2,3}-+([0-9]){3,4}-+([0-9]){3,4}$";
		if(!email.matches(regEmail)){
			System.out.println("이메일 정규식 오류");
			request.setAttribute("msg", "createUserNo");
			request.setAttribute("url", request.getContextPath()+"/create.us");
			return;
		}
		else if(!tel.matches(regTel)){
			System.out.println("전화번호 정규식 오류");
			request.setAttribute("msg", "createUserNo");
			request.setAttribute("url", request.getContextPath()+"/create.us");
			return;
		}
		
		vo.setUidx(uidx);
		vo.setEmail(email);
		vo.setBirth(birth);
		vo.setTel(tel);
		
		String res = dao.setUserInfoUpdate(vo);
		response.getWriter().write(res);
	}

}
