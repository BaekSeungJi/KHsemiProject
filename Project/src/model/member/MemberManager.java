package model.member;

import java.util.ArrayList;
import java.util.List;


import dto.MemberDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class MemberManager implements iMemberManager {
	
	 public MemberManager() {
			try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
	
	}
	 
	 
	 	 
	 public boolean addMember(String id, String pwd, String name, String email, String phone) {

		 String sql = " INSERT INTO CUSTUSER(ID, PWD, NAME, EMAIL, PHONE) "
					+ " VALUES(?, ?, ?, ?, ?) ";
			
		 	Connection conn = null;
			PreparedStatement psmt = null;
			
			int count = 0;
			
			try {
				conn = getConnection();
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setString(2, pwd);
				psmt.setString(3, name);
				psmt.setString(4, email);
				psmt.setString(5, phone);
								
				
				count = psmt.executeUpdate();		
							
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally {
						
			}
			
			return count>0?true:false;
	
	}

	 
	 public Connection getConnection() throws SQLException {
			String url = "jdbc:oracle:thin:@192.168.30.2:1521:xe";
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
	 public boolean idDupId(String id) {

		 String sql = "select count(*) as cnt from CUSTUSER where id=?";
			
		 	Connection conn = null;
			PreparedStatement psmt = null;
			
			int count = 0;
			
			try {
				conn = getConnection();
				
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				
				psmt = conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				
				while(rs.next()) {
					count = count + rs.getInt("cnt");
				}	
				
				if(count > 0) {
					return true;
				}else {
					return false;
				}
				
				
							
			} catch (SQLException e) {			
				e.printStackTrace();
				return false;
			} finally {
			}
	
	}



	@Override
	public MemberManager getid(String id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean isDupId(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
