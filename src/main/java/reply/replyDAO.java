package reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.GetConn;

public class replyDAO {
	GetConn getConn = GetConn.getInstance();
	
	private Connection conn = getConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	replyVO vo = null;

	public ArrayList<replyVO> getReply(String boardIdx) {
		ArrayList<replyVO> vos = new ArrayList<>();
		try {
			sql = "select * from reply where boardIdx = ? order by writeDay;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardIdx);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new replyVO();
				vo.setRidx(rs.getInt("ridx"));
				vo.setBoardIdx(rs.getString("boardIdx"));
				vo.setContent(rs.getString("content"));
				vo.setRating(rs.getInt("rating"));
				vo.setWriteDay(rs.getString("writeDay"));
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
	
	
	public String setReply(replyVO vo) {
		String res = "0";
		try {
			sql = "insert into reply values(default,?,?,?,default,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBoardIdx());
			pstmt.setString(2, vo.getContent());
			pstmt.setDouble(3, vo.getRating());
			pstmt.setString(4, vo.getMid());
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}

	
	
	public int getReplyCnt() {
		int totRecCnt = 0;
		try {
			sql = "select count(*) as 'replyCnt' from reply";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("replyCnt");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}
	
	public ArrayList<replyVO> getReplyList(int start, int ea) {
		ArrayList<replyVO> vos = new ArrayList<>();
		try {
			sql = "select * from reply order by writeDay desc limit ?,?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, ea);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new replyVO();
				vo.setRidx(rs.getInt("ridx"));;
				vo.setBoardIdx(rs.getString("boardIdx"));
				vo.setContent(rs.getString("content"));
				vo.setRating(rs.getInt("rating"));
				vo.setWriteDay(rs.getString("writeDay"));
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


	public String setReplyDel(int ridx) {
		String res = "0";
		try {
			sql = "delete from reply where ridx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ridx);
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}


	public int getReplySearchCnt(String searching, String searchItem) {
		int totRecCnt = 0;
		try {
			sql = "select count(*) as 'replyCnt' from reply where "+searchItem+" like ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searching+"%");
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("replyCnt");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}

	public ArrayList<replyVO> getReplySearchList(String searching, String searchItem, int start, int ea) {
		ArrayList<replyVO> vos = new ArrayList<>();
		try {
			sql = "select * from reply "
					+ "where "+searchItem+" like ? "
					+ "order by writeDay desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searching+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, ea);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new replyVO();
				vo.setRidx(rs.getInt("ridx"));;
				vo.setBoardIdx(rs.getString("boardIdx"));
				vo.setContent(rs.getString("content"));
				vo.setRating(rs.getInt("rating"));
				vo.setWriteDay(rs.getString("writeDay"));
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
