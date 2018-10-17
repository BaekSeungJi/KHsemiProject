package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;
import dto.ReserveDto;
import dto.ReviewDto;

public class MemberManager implements iMemberManager {
	
	// 생성할 때 db연결
	public MemberManager() {
		DBConnection.initConnect();
	}

	@Override
	public boolean addMember(MemberService ms) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getId(String ID) {
		// TODO Auto-generated method stub
		return false;
	}


	
	// 예약 리스트 가져오기
	@Override
	public List<ReserveDto> ad_reserveList(String id) {

		String sql = " SELECT seq, hotelname, request, realdate, regdate, del from reserve where id = ? ";
		List<ReserveDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 ad_reserveList Success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 ad_reserveList Success");
			System.out.println(id);
			
			psmt.setString(1, id);
			
			System.out.println("3/6 ad_reserveList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 ad_reserveList Success");
			
			
			if(rs.next()){			
				System.out.println("5/6 ad_reserveList Success");
				
			
				int seq = rs.getInt(1);
				String _id = id;
				String hotelname = rs.getString(2);
				String request = rs.getString(3);
				String realdate = rs.getString(4);
				String regdate = rs.getString(5);
				int del = rs.getInt(6);
				ReserveDto dto = new ReserveDto(seq, _id, hotelname, request, realdate, regdate, del);
				list.add(dto);
			
			}
		} catch (Exception e1) {
			System.out.println("ad_reserveList Fail");
			e1.printStackTrace();
			
		}	finally{			
			DBClose.close(psmt, conn, rs);			
		}
		
		return list;
	}
	
	// 후기 리스트 가져오기
	@Override
	public List<ReviewDto> ad_reviewList(String id) {

		String sql = " SELECT num, hotelname, TITLE, CONTENT, regdate, del from REVIEW where id = ? ";
		List<ReviewDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 ad_reviewList Success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 ad_reviewList Success");
			System.out.println(id);
			
			psmt.setString(1, id);
			
			System.out.println("3/6 ad_reviewList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 ad_reviewList Success");
			
			
			if(rs.next()){			
				System.out.println("5/6 ad_reviewList Success");
				
			
				int seq = rs.getInt(1);
				String _id = id;
				String hotelname = rs.getString(2);
				String title = rs.getString(3);
				String content = rs.getString(4);
				String regdate = rs.getString(5);
				int del = rs.getInt(6);
				ReviewDto dto = new ReviewDto(seq, _id, hotelname, title, content, 0, del, 0, regdate);
				list.add(dto);
			
			}
		} catch (Exception e1) {
			System.out.println("ad_reviewList Fail");
			e1.printStackTrace();
			
		}	finally{			
			DBClose.close(psmt, conn, rs);			
		}
		
		return list;
	}

	@Override
	public boolean ad_MemberUpdate(String id, String pw, String name, String email, String phone) {
		String sql = " UPDATE member SET "
				+ " pwd=?, name=?, email=?, phone=? "
				+ " WHERE id=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
	
			try {
			conn = DBConnection.getConnection();
		
			
		
			System.out.println("2/6  ad_MemberUpdate");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pw);
			psmt.setString(2, name);
			psmt.setString(3, email);
			psmt.setString(4, phone);
			psmt.setString(5, id);
			
			
			System.out.println("3/6  ad_MemberUpdate");
			
			count = psmt.executeUpdate();
			System.out.println("4/6 ad_MemberUpdate");
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);	
			System.out.println("5/6 ad_MemberUpdate");
		}		
		
		return count>0?true:false;
	}

	@Override
	public MemberDto ad_login(String id) {
		String sql = " SELECT ID, pwd, name, email , phone, blacklist, auth "
				+ " FROM MEMBER "
				+ " WHERE ID=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberDto mem = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			System.out.println("1/6 login Success");
			
			psmt.setString(1, id);
			
			
			System.out.println("2/6 login Success");
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String _id = rs.getString(1);
				String pwd = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				String phone = rs.getString(5);
				int blacklist = rs.getInt(6);
				int auth = rs.getInt(7);
				
				mem = new MemberDto(_id, pwd, name, email, phone, blacklist, auth);
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
	public String ad_getHotelname(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberDto login(String id) {
		String sql = " SELECT ID, pwd, name, email , phone, blacklist, auth "
				+ " FROM MEMBER "
				+ " WHERE ID=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberDto mem = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			System.out.println("1/6 login Success");
			
			psmt.setString(1, id);
			
			
			System.out.println("2/6 login Success");
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				String _id = rs.getString(1);
				String pwd = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				String phone = rs.getString(5);
				int blacklist = rs.getInt(6);
				int auth = rs.getInt(7);
				
				mem = new MemberDto(_id, pwd, name, email, phone, blacklist, auth);
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
	

}
