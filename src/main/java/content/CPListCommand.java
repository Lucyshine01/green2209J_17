package content;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;
import user.UserVO;

public class CPListCommand implements ContentInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContentDAO dao = new ContentDAO();
		UserDAO userDAO = new UserDAO();
		
		String categori = request.getParameter("categori")==null ? "" : request.getParameter("categori");
		int detail = request.getParameter("detail")==null ? 1111 : Integer.parseInt(request.getParameter("detail"));
		String subCategori = "";
		if(detail == 1) subCategori = "홈 인테리어";
		else if(detail == 2) subCategori = "상업 인테리어";
		else if(detail == 3) subCategori = "조명 인테리어";
		else if(detail == 4) subCategori = "욕실,화장실 인테리어";
		else if(detail == 5) subCategori = "타일시공";
		else if(detail == 6) subCategori = "페인트시공";
		else if(detail == 7) subCategori = "싱크대 교체";
		else if(detail == 8) subCategori = "도배장판";
		else if(detail == 9) subCategori = "인테리어 필름";
		else if(detail == 10) subCategori = "도면 제작·수정";
		else if(detail == 11) subCategori = "인테리어 컨설팅";
		else if(detail == 12) subCategori = "3D 모델링";
		else if(detail == 1111) subCategori = "no";
		
		String firstCategori = "";
		if(categori.equals("인테리어") && subCategori.equals("no")) {
			firstCategori = "홈 인테리어/상업 인테리어/조명 인테리어/욕실,화장실 인테리어";
		}
		else if(categori.equals("시공") && subCategori.equals("no")) {
			firstCategori = "타일시공/페인트시공/싱크대 교체/도배장판/인테리어 필름";
		}
		else if(categori.equals("디자인") && subCategori.equals("no")) {
			firstCategori = "도면 제작·수정/인테리어 컨설팅/3D 모델링";
		}
		
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize")==null? 9 : Integer.parseInt(request.getParameter("pageSize"));
		int totRecCnt = 0;
		
		if(categori.equals("")) totRecCnt = userDAO.getCPCnt();
		else totRecCnt = dao.getCPCntCategori(firstCategori,subCategori);
		
		int totPage = (totRecCnt % pageSize)==0 ? totRecCnt / pageSize : (totRecCnt / pageSize)+1;
		int stratIndexNo = (pag-1) * pageSize;
		int curScrStartNo = totRecCnt - stratIndexNo;
		
		// 블록페이징처리.....(3단계) -> 블록의 시작번호를 0번부터 처리했다.
		int blockSize = 3;
		int curBlock = (pag - 1) / blockSize;
		int lastBlock = (totPage-1) / blockSize;
		
		ArrayList<UserVO> vos = null;
		
		if(categori.equals("")) vos = dao.getCpList(stratIndexNo, pageSize);
		else vos = dao.getCpListCategori(firstCategori, subCategori, stratIndexNo, pageSize);
		
		request.setAttribute("vos", vos);
		request.setAttribute("CPTot", totRecCnt);
		if(!categori.equals("")) request.setAttribute("categori", categori);
		request.setAttribute("subCategori", subCategori);
		request.setAttribute("detail", detail);
		
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);
		
		request.setAttribute("pag", pag);
		request.setAttribute("totPage", totPage);
		request.setAttribute("stratIndexNo", stratIndexNo);
		request.setAttribute("curScrStartNo", curScrStartNo);
	}
}
