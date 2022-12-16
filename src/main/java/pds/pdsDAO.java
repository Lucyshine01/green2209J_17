package pds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.GetConn;

public class pdsDAO {
	GetConn getConn = GetConn.getInstance();
	
	private Connection conn = getConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	pdsVO vo = null;
	
	// 파일 등록
	public void fileInput(pdsVO vo) {
		try {
			sql = "insert into pds values(default,?,?,?,?,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getfOriName());
			pstmt.setString(2, vo.getfSysName());
			pstmt.setInt(3, vo.getfSize());
			pstmt.setString(4, vo.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
	}
	
	public ArrayList<pdsVO> getFileInfo() {
		ArrayList<pdsVO> vos = new ArrayList<>();
		try {
			sql = "select * from pds order by inDate desc;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new pdsVO();
				vo.setpIdx(rs.getInt("pIdx"));
				vo.setfOriName(rs.getString("fOriName"));
				vo.setfSysName(rs.getString("fSysName"));
				vo.setfSize(rs.getInt("fSize"));
				vo.setMid(rs.getString("mid"));
				vo.setInDate(rs.getString("inDate"));
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
