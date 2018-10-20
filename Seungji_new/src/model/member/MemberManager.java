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

	public MemberManager() {
		DBConnection.initConnect();
	}

	public static MemberManager getInstance() {
		return memberManager;
	}


	@Override
	public boolean addMember(MemberDto dto) {
		
		System.out.println("dto:" + dto.toString()); 
		
		String sql = " INSERT INTO MEMBER "
				+ " ( ID, PWD, NAME, EMAIL, PHONE, BLACKLIST, AUTH ) "
				+ " VALUES(?, ?, ?, ?, ?, 0, 3) ";

		Connection conn = null;
		PreparedStatement psmt = null;

		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 addMember Success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 addMember Success");

			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			psmt.setString(5, dto.getPhone());
		//	psmt.setInt(6, dto.getBlacklist());
		//	psmt.setInt(7, dto.getAuth());
			System.out.println("여기까지클리어");

			count = psmt.executeUpdate();	//??
			System.out.println("3/3 addMember Success");
		} catch (Exception e) {			
			System.out.println("addMember Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}

		return count>0?true:false;

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

			System.out.println("1/3 login Success");

			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());

			System.out.println("2/3 login Success");

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
			System.out.println("3/3 login Success");

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

		String sql = " SELECT ID FROM MEMBER"
				+ " WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		boolean findId = false;


		int count = 0;

		try {

			conn = DBConnection.getConnection();
			System.out.println("1/3 getId Success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 getId Success");

			psmt.setString(1, id.trim());

			rs = psmt.executeQuery();		
			
			System.out.println("3/3 getId Success");
			while(rs.next()) {
				findId = true;
			}	

			
		} catch (Exception e) {	
			System.out.println("getId Fail");
			e.printStackTrace();
		

		} finally {
			DBClose.close(psmt, conn, null);
		}
		System.out.println("findId = " + findId);
		return findId;
	}

	public MemberDto suchid(MemberDto dto) {
		String sql = " SELECT ID, PWD, NAME, EMAIL, PHONE, BLACKLIST, AUTH "
				+ " FROM MEMBER "
				+ " WHERE email=?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		MemberDto mem = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);

			System.out.println("1/3 Suchid Success");

			psmt.setString(1, dto.getEmail());
		

			System.out.println("2/3 Suchid Success");

			rs = psmt.executeQuery();

			if(rs.next()) {
				String id = rs.getString(1);
				/*String pwd = rs.getString(2);
				String name = rs.getString(3);
				String phone = rs.getString(5);	*/	
				String email = rs.getString(4);
				int blacklist = rs.getShort(6);
				int auth = rs.getInt(7);

				mem = new MemberDto(id, null, null, email, null, blacklist, auth);
			}
			System.out.println("3/3 Suchid Success");

		} catch (Exception e) {
			System.out.println("Suchid fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}

		return mem;
	}

	
	public MemberDto suchpwd(MemberDto dto) {
		String sql = " SELECT ID, PWD, NAME, EMAIL, PHONE, BLACKLIST, AUTH "
				+ " FROM MEMBER "
				+ " WHERE ID=? AND PHONE=?";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		MemberDto mem = null;

		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);

			System.out.println("1/3 Suchid Success");

			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPhone());

			System.out.println("2/3 Suchid Success");

			rs = psmt.executeQuery();

			if(rs.next()) {
				String id = rs.getString(1);
				String pwd = rs.getString(2);
				/*String name = rs.getString(3);
				String email = rs.getString(4);	*/
				String phone = rs.getString(5);	
				int blacklist = rs.getShort(6);
				int auth = rs.getInt(7);

				mem = new MemberDto(id, pwd, null, null, phone, blacklist, auth);
			}
			System.out.println("3/3 Suchid Success");

		} catch (Exception e) {
			System.out.println("Suchpwd fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}

		return mem;
	}

	
}
