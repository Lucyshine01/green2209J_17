package content;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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


	public ArrayList<UserVO> getCpList(int strat, int ea) {
		ArrayList<UserVO> vos = new ArrayList<>();
		try {
			sql = "select * from user u, company c "
					+ "where u.mid = c.mid order by createDayCP desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, strat);
			pstmt.setInt(2, ea);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new UserVO();
				
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
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}


	public ArrayList<UserVO> getCpListCategori(String firstCategori, String subCategori, int strat, int ea) {
		ArrayList<UserVO> vos = new ArrayList<>();
		try {
			if(!subCategori.equals("no")) {
				sql = "select * from user u, company c "
						+ "where u.mid = c.mid and cpExp like '%"+subCategori+"%' "
						+ "order by createDayCP desc limit ?,?";
			}
			else {
				sql = "select * from user u, company c "
						+ "where u.mid = c.mid and (";
				String[] categori = firstCategori.split("/");
				for(String strCategori : categori) {
					sql += " cpExp like '%"+strCategori+"%' or ";
				}
				sql = sql.substring(0,sql.lastIndexOf("or"));
				sql += ") order by createDayCP desc limit ?,?";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, strat);
			pstmt.setInt(2, ea);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new UserVO();
				
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
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return vos;
	}

	
	public int getCPCntCategori(String firstCategori, String subCategori) {
		int totRecCnt = 0;
		try {
			if(!subCategori.equals("no")) {
				sql = "select count(*) as cnt from user u, company c "
						+ "where u.mid = c.mid and cpExp like '%"+subCategori+"%' "
						+ "order by createDayCP desc";
			}
			else {
				sql = "select count(*) as cnt from user u, company c "
						+ "where u.mid = c.mid and (";
				String[] categori = firstCategori.split("/");
				for(String strCategori : categori) {
					sql += " cpExp like '%"+strCategori+"%' or ";
				}
				sql = sql.substring(0,sql.lastIndexOf("or"));
				sql += ") order by createDayCP desc";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}


	public int getCPSearchCnt(String searching, String searchItem) {
		int totRecCnt = 0;
		int forCnt = 0;
		try {
			if(searchItem.equals("all")) {
				sql = "select count(*) as cnt from user u, company c "
						+ "where u.mid = c.mid and (cpName like ? or name like ? or cpIntro like ? or cpExp like ?) "
						+ "order by createDayCP desc";
				forCnt = 4;
			}
			else {
				sql = "select count(*) as cnt from user u, company c "
						+ "where u.mid = c.mid and "+searchItem+" like ? "
						+ "order by createDayCP desc";
				forCnt = 1;
			}
			pstmt = conn.prepareStatement(sql);
			for(int i=1; i<=forCnt; i++) pstmt.setString(i, "%"+searching+"%");
			rs = pstmt.executeQuery();
			rs.next();
			totRecCnt = rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			getConn.rsClose();
		}
		return totRecCnt;
	}


	public ArrayList<UserVO> getCPSearchList(String searching, String searchItem, int stratIndexNo, int pageSize) {
		ArrayList<UserVO> vos = new ArrayList<>();
		int forCnt = 0;
		try {
			if(searchItem.equals("all")) {
				sql = "select * from user u, company c "
						+ "where u.mid = c.mid and (cpName like ? or name like ? or cpIntro like ? or cpExp like ?) "
						+ "order by createDayCP desc";
				forCnt = 4;
			}
			else {
				sql = "select * from user u, company c "
						+ "where u.mid = c.mid and "+searchItem+" like ? "
						+ "order by createDayCP desc";
				forCnt = 1;
			}
			pstmt = conn.prepareStatement(sql);
			for(int i=1; i<=forCnt; i++) pstmt.setString(i, "%"+searching+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new UserVO();
				
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
