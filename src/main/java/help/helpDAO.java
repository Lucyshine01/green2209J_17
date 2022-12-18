package help;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.GetConn;
import user.UserVO;

public class helpDAO {
	GetConn getConn = GetConn.getInstance();
	
	private Connection conn = getConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	helpVO vo = null;

	public ArrayList<helpVO> gethelpCheck(String mid) {
		ArrayList<helpVO> vos = new ArrayList<>();
		try {
			sql = "select * from help where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new helpVO();
				vo.setHidx(rs.getInt("hidx"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setConf(rs.getString("conf"));
				vo.setAnswer(rs.getString("answer"));
				vo.setMid(rs.getString("mid"));
				vos.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}
	
	

	
}
