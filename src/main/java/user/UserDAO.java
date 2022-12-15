package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conn.GetConn;

public class UserDAO {
	GetConn getConn = GetConn.getInstance();
	
	private Connection conn = getConn.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	UserVO vo = null;
	
	
	// 유저 생성
	public String createUser(UserVO vo) {
		String res = "0";
		try {
			sql = "insert into user values(default,?,?,?,?,?,default,default,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getBirth());
			pstmt.setString(5, vo.getTel());
			pstmt.executeUpdate();
			res="1";
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}

	// 회사 생성(관리자 승인 전 등록)
	public String createCompany(UserVO vo) {
		String res = "0";
		try {
			sql = "insert into company values(default,?,?,?,?,?,?,'',?,default,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getCpName());
			pstmt.setString(3, vo.getCpAddr());
			pstmt.setString(4, vo.getCpImg());
			pstmt.setString(5, vo.getCpHomePage());
			pstmt.setString(6, vo.getCpIntro());
			pstmt.setString(7, vo.getCpExp());
			pstmt.setInt(8, vo.getImgSize());
			pstmt.setString(9, vo.getMid());
			pstmt.executeUpdate();
			res="1";
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}
	
	// 유저 체크
	public UserVO getUserCheck(String mid) {
		vo = new UserVO();
		try {
			sql = "select * from user where mid = ?";
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
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vo;
	}

	public String setPwdUpdate(String mid, String pwd) {
		String res = "0";
		try {
			sql = "update user set pwd = ? where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, mid);
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}

	public UserVO getCPCheck(String mid) {
		vo = new UserVO();
		try {
			sql = "select * from company where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
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
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vo;
	}

	
}
