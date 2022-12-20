package report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.GetConn;
import reply.replyVO;

public class reportDAO {
	GetConn getConn = GetConn.getInstance();
	
	private Connection conn = getConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	reportVO vo = null;

	public String setReplyReport(replyVO replyVO, String mid) {
		String res = "2";
		try {
			sql = "insert into report values(?,?,?,?,?,default);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyVO.getRidx());
			pstmt.setString(2, replyVO.getMid());
			pstmt.setString(3, replyVO.getContent());
			pstmt.setString(4, replyVO.getWriteDay());
			pstmt.setString(5, mid);
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " +e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}

	public String getReplyReport(int ridx, String mid) {
		String res = "1";
		try {
			sql = "select * from report where replyIdx = ? and reportMid = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ridx);
			pstmt.setString(2, mid);
			rs = pstmt.executeQuery();
			if(rs.next()) res = "0";
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " +e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return res;
	}

	public ArrayList<reportVO> getreportList(int start, int ea) {
		ArrayList<reportVO> vos = new ArrayList<>();
		try {
			sql = "select * from report order by reportWriteDay desc limit ?,?;;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, ea);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new reportVO();
				vo.setReplyIdx(rs.getInt("replyIdx"));
				vo.setReplyMid(rs.getString("replyMid"));
				vo.setReplyContent(rs.getString("replyContent"));
				vo.setReplyWriteDay(rs.getString("replyWriteDay"));
				vo.setReportMid(rs.getString("reportMid"));
				vo.setReportWriteDay(rs.getString("reportWriteDay"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}

	public int getreportCnt() {
		int totRecCnt = 0;
		try {
			sql = "select count(*) as 'reportCnt' from report";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("reportCnt");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}

	
	
}
