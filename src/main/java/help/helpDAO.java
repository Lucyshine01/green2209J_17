package help;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.GetConn;

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
			sql = "select * from help where mid = ? order by writeDay desc;";
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
				vo.setWriteDay(rs.getString("writeDay"));
				vo.setAnswerDay(rs.getString("answerDay"));
				vos.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}

	public String setInputHelp(helpVO vo) {
		String res = "0";
		try {
			sql = "insert into help values(default,?,?,default,'',?,default,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getMid());
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}

	public int getHelpCnt() {
		int totRecCnt = 0;
		try {
			sql = "select count(*) as 'helpCnt' from help";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("helpCnt");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}

	public ArrayList<helpVO> getHelpList(int start, int ea) {
		ArrayList<helpVO> vos = new ArrayList<>();
		try {
			sql = "select * from help order by writeDay desc limit ?,?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, ea);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new helpVO();
				vo.setHidx(rs.getInt("hidx"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setConf(rs.getString("conf"));
				vo.setAnswer(rs.getString("answer"));
				vo.setMid(rs.getString("mid"));
				vo.setWriteDay(rs.getString("writeDay"));
				vo.setAnswerDay(rs.getString("answerDay"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}

	public int getHelpSearchCnt(String searching, String searchItem) {
		int totRecCnt = 0;
		try {
			sql = "select count(*) as 'helpCnt' from help where "+searchItem+" like ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searching+"%");
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("helpCnt");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}

	public ArrayList<helpVO> getHelpSearchList(String searching, String searchItem, int start, int ea) {
		ArrayList<helpVO> vos = new ArrayList<>();
		try {
			sql = "select * from help "
					+ "where "+searchItem+" like ? "
					+ "order by writeDay desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searching+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, ea);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new helpVO();
				vo.setHidx(rs.getInt("hidx"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setConf(rs.getString("conf"));
				vo.setAnswer(rs.getString("answer"));
				vo.setMid(rs.getString("mid"));
				vo.setWriteDay(rs.getString("writeDay"));
				vo.setAnswerDay(rs.getString("answerDay"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}
	
	
	public String setAnswerHelp(int hidx, String answer) {
		String res = "0";
		try {
			sql = "update help set conf = 'on', answer = ?, answerDay = now() where hidx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, answer);
			pstmt.setInt(2, hidx);
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return res;
	}

	public ArrayList<helpVO> getHelpAllList() {
		ArrayList<helpVO> vos = new ArrayList<>();
		try {
			sql = "select * from help order by writeDay desc;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new helpVO();
				vo.setHidx(rs.getInt("hidx"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setConf(rs.getString("conf"));
				vo.setAnswer(rs.getString("answer"));
				vo.setMid(rs.getString("mid"));
				vo.setWriteDay(rs.getString("writeDay"));
				vo.setAnswerDay(rs.getString("answerDay"));
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
