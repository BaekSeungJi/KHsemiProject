package model.member;

import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class MemberManager implements iMemberManager {
	
	private static MemberManager memberManager = new MemberManager();
	
	public static MemberManager getInstance() {
		return memberManager;
	}
	
	public MemberManager() {
		DBConnection.initConnect();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
	}
	
	

	@Override
	public boolean addMember(MemberDto dto) {
		String sql = " INSERT INTO CUSTUSER(ID, PWD, NAME, EMAIL, PHONE, BLACKLIST, AUCH ) "
				+ " VALUES(?, ?, ?, ?, ?, 0, 3) ";

		Connection conn = null;
		PreparedStatement psmt = null;

		int count = 0;

		try {
			conn = getConnection();
			System.out.println("1/6 addMember Success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 addMember Success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			psmt.setString(5, dto.getPhone());
			psmt.setInt(6, dto.getBlacklist());
			psmt.setInt(7, dto.getAuth());

			count = psmt.executeUpdate();		
			System.out.println("3/6 addMember Success");
		} catch (SQLException e) {			
			System.out.println("addMember Fail");
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count>0?true:false;

	}


	public Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "hr";
		String pass = "hr";

		Connection conn = DriverManager.getConnection(url, user, pass);

		return conn;
	}


	public List<MemberDto> getMemberDtoList() {
		String sql = " SELECT ID, PWD, NAME, EMAIL, PHONE "
				+ " FROM CUSTUSER "
				+ " ORDER BY ID DESC ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<MemberDto> list = new ArrayList<>();

		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while(rs.next()) {
				MemberDto c = new MemberDto();

				c.setId(rs.getString("ID"));
				c.setPwd(rs.getString("PWD"));
				c.setName(rs.getString("NAME"));
				c.setEmail(rs.getString("Email"));
				c.setPhone(rs.getString("Phone"));
				list.add(c);				
			}			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		return list;		
	}


	@Override
	public MemberDto login(MemberDto dto) {
		String sql = " SELECT ID, PWD, NAME, EMAIL, PHONE, BLACKLIST, AUTH "
				+ " FROM MEMBER "
				+ " WHERE ID=? AND PWD=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		MemberDto mem = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);

			System.out.println("1/6 login Success");

			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());

			System.out.println("2/6 login Success");

			rs = psmt.executeQuery();

			if(rs.next()) {
				String id = rs.getString(1);
				String pwd = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				String phone = rs.getString(5);		
				int blacklist = rs.getShort(6);
				int auth = rs.getInt(7);




				mem = new MemberDto(id, pwd, name, email, phone, blacklist, auth);
			}
			System.out.println("3/6 login Success");

		} catch (Exception e) {
			System.out.println("login fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}

		return mem;
	}

	@Override
	public boolean getId(String id) {
		System.out.println("여기까지");
		String sql = " SELECT ID FROM MEMBER WHERE ID=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		boolean findId = false;

	
		int count = 0;

		try {

			conn = getConnection();
			System.out.println("1/6 getId Success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getId Success");

			psmt.setString(1, id.trim());

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();		
			System.out.println("3/6 getId Success");
			while(rs.next()) {
				count = count + rs.getInt("cnt");
			}	

			if(count > 0) {
				findId = true;
			}else {
				DBClose.close(psmt, conn, null);
				findId = false;
			}
			


		} catch (SQLException e) {			
			e.printStackTrace();
			DBClose.close(psmt, conn, null);
			
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return findId;
	}

	@Override
	public List<MemberManager> getMemberList() {
		// TODO Auto-generated method stub
		return null;
	}


}
