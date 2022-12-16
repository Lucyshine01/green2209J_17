package content;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conn.GetConn;
import user.UserVO;

public class ContentDAO {
	GetConn getConn = GetConn.getInstance();
	
	private Connection conn = getConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	UserVO vo = null;
	
	
	public UserVO getCpInfo(String mid) {
		vo = new UserVO();
		try {
			sql = "select * from user u, company c where u.mid = c.mid and u.mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setUidx(rs.getInt("uidx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setEmail(rs.getString("email"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setCreateDay(rs.getString("createDay"));
				vo.setUserLevel(rs.getString("userLevel"));
				vo.setPoint(rs.getInt("point"));
				
				vo.setCidx(rs.getInt("cidx"));
				vo.setName(rs.getString("name"));
				vo.setCpName(rs.getString("cpName"));
				vo.setCpAddr(rs.getString("cpAddr"));
				vo.setCpImg(rs.getString("cpImg"));
				vo.setCpHomePage(rs.getString("cpHomePage"));
				vo.setCpIntro(rs.getString("cpIntro"));
				vo.setCpIntroImg(rs.getString("cpIntroImg"));
				vo.setCpExp(rs.getString("cpExp"));
				vo.setAct(rs.getString("act"));
				vo.setImgSize(rs.getInt("imgSize"));
				vo.setCreateDayCP(rs.getString("createDayCP"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vo;
	}

	
}
