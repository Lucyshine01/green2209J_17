package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.GetConn;
import pds.pdsVO;

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
			sql = "insert into company values(default,?,?,?,?,?,?,'',?,default,?,?,default,default)";
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
				vo.setCreateDayCP(rs.getString("createDayCP"));
				vo.setViewCP(rs.getInt("viewCP"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vo;
	}

	public ArrayList<UserVO> getUserList(int start, int ea) {
		ArrayList<UserVO> vos = new ArrayList<>();
		try {
			sql = "select * from user where userLevel != '관리자'"
					+ "order by createDay desc limit ?,?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, ea);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new UserVO();
				vo.setUidx(rs.getInt("uidx"));;
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setEmail(rs.getString("email"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setCreateDay(rs.getString("createDay"));
				vo.setUserLevel(rs.getString("userLevel"));
				vo.setPoint(rs.getInt("point"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}

	public ArrayList<UserVO> getCPList(int start, int ea) {
		ArrayList<UserVO> vos = new ArrayList<>();
		try {
			sql = "select * from company order by createDayCP desc limit ?,?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, ea);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new UserVO();
				vo.setCidx(rs.getInt("cidx"));;
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
				vo.setMid(rs.getString("mid"));
				vo.setCreateDayCP(rs.getString("createDayCP"));
				vo.setViewCP(rs.getInt("viewCP"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}

	public int getUserCnt() {
		int totRecCnt = 0;
		try {
			sql = "select count(*) as 'UserCnt' from user where userLevel != '관리자'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("UserCnt");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}
	
	public int getCPCnt() {
		int totRecCnt = 0;
		try {
			sql = "select count(*) as 'CPCnt' from company";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("CPCnt");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}
	
	public int getCPCnt_rating() {
		int totRecCnt = 0;
		try {
			sql = "select count(r.ridx) as CPcnt "
					+ "from (select * from reply group by boardidx) as r;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("CPCnt");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}
	
	
	public String setUserDel(int uidx) {
		String res = "0";
		try {
			sql = "update user set pwd = '', userLevel = '삭제' where uidx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uidx);
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return res;
	}
	
	
	public ArrayList<UserVO> getSearchUserList(String searching, String searchItem, int start, int ea) {
		ArrayList<UserVO> vos = new ArrayList<>();
		try {
			sql = "select * from user "
					+ "where userLevel != '관리자' and "+searchItem+" like ? "
					+ "order by createDay desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searching+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, ea);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new UserVO();
				vo.setUidx(rs.getInt("uidx"));;
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setEmail(rs.getString("email"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setCreateDay(rs.getString("createDay"));
				vo.setUserLevel(rs.getString("userLevel"));
				vo.setPoint(rs.getInt("point"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}

	public int getUserSearchCnt(String searching, String searchItem) {
		int totRecCnt = 0;
		try {
			sql = "select count(*) as 'UserCnt' from user where userLevel != '관리자' and "+searchItem+" like ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searching+"%");
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("UserCnt");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}

	public int getCPSearchCnt(String searching, String searchItem) {
		int totRecCnt = 0;
		try {
			sql = "select count(*) as 'CPCnt' from company where "+searchItem+" like ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searching+"%");
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("CPCnt");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}

	public ArrayList<UserVO> getSearchCPList(String searching, String searchItem, int start, int ea) {
		ArrayList<UserVO> vos = new ArrayList<>();
		try {
			sql = "select * from company "
					+ "where "+searchItem+" like ? "
					+ "order by createDayCP desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searching+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, ea);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new UserVO();
				vo.setCidx(rs.getInt("cidx"));;
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
				vo.setMid(rs.getString("mid"));
				vo.setCreateDayCP(rs.getString("createDayCP"));
				vo.setViewCP(rs.getInt("viewCP"));
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}
	
	
	public String setCPOn(int cidx, String act) {
		String res = "0";
		try {
			sql = "update company set act = ? where cidx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, act);
			pstmt.setInt(2, cidx);
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}
	
	public void setUserLevelUpdate(String mid) {
		try {
			sql = "update user set userLevel = '업체' where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
	}
	
	public void setUserLevelDownUpdate(String mid) {
		try {
			sql = "update user set userLevel = '일반' where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
	}
	
	public String setUserInfoUpdate(UserVO vo) {
		String res = "0";
		try {
			sql = "update user set email=?, tel=?, birth=? where uidx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getTel());
			pstmt.setString(3, vo.getBirth());
			pstmt.setInt(4, vo.getUidx());
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}
	
	public String setCPDel(int cidx) {
		String res = "0";
		try {
			sql = "delete from company where cidx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cidx);
			pstmt.executeUpdate();
			res = "1";
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
		return res;
	}
	
	public void setCPIntroImg(String cpIntroImg, String mid) {
		try {
			sql = "update company set cpIntroImg = ? where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cpIntroImg);
			pstmt.setString(2, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.pstmtClose();
		}
	}

	
}
