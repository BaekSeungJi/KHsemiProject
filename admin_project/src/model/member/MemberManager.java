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
import dto.ReserveTableDto;
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

	@Override
	public MemberService login(MemberService ms) {
		// TODO Auto-generated method stub
		return null;
	}

	// 임시로 로그인
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

		// 회원 정보 수정
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

		
		
	//admin 해당아이디의 호텔 이름 가져오기
	@Override
	public String ad_getHotelname(String id) {
	
		String sql = " SELECT HOTELNAME FROM HOTEL "
				+ " WHERE ID = '" + id + "'";
		
		String find = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 ad_getHotelname Success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 ad_getHotelname Success");
			rs = psmt.executeQuery(sql);
			System.out.println("3/6 ad_getHotelname Success");
			
			if(rs.next()){			
				find = rs.getString(1);	
				System.out.println(find);
				
			}
		} catch (Exception e1) {
			System.out.println("ad_getHotelname Fail");
			e1.printStackTrace();
			
		}	finally{			
			DBClose.close(psmt, conn, rs);			
		}
		
		
		
		return find;
	}



	
	// 해당호텔 예약 정보
	@Override
	public List<ReserveTableDto> ad_GetHotelmember(String hotelname,String sWord,String selected) {
		
		List<ReserveTableDto> list = new ArrayList<>();
		
		// 검색어
		String Word = "";
		if(selected.equals("이름")) {	
			Word = " and m.name LIKE '%" + sWord.trim() + "%'";
		}else if(selected.equals("아이디")) {	
			Word = " and m.id='" + sWord.trim() + "' ";
		}
		
		String sql = " SELECT  m.id, m.name,m.email,m.phone, e.checkin,e.checkout  FROM member m, RESERVE e "
				+ " WHERE e.HOTELNAME = ? " + Word;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 ad_GetHotelmember Success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 ad_GetHotelmember Success");
			System.out.println(hotelname);
			
			psmt.setString(1, hotelname);
			
			System.out.println("3/6 ad_GetHotelmember Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 ad_GetHotelmember Success");
			
			
			while(rs.next()){			
				System.out.println("5/6 ad_GetHotelmember Success");
				
			
				String id = rs.getString(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				String checkin = rs.getString(5);
				String checkout = rs.getString(6);
				
				ReserveTableDto dto = new ReserveTableDto(id, name, email, phone, checkin,checkout);
				
				list.add(dto);
				System.out.println(dto.getId());
				
			}
		} catch (Exception e1) {
			System.out.println("ad_GetHotelmember Fail");
			e1.printStackTrace();
			
		}	finally{			
			DBClose.close(psmt, conn, rs);			
		}
		
		return list;
	}

	
	// 예약 리스트 가져오기
	@Override
	public List<ReserveDto> ad_reserveList(String id) {

		String sql = " SELECT seq, hotelname, request, CHECKIN,checkout, regdate, del from reserve where id = ? ";
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
			
			
			while(rs.next()){			
				System.out.println("5/6 ad_reserveList Success");
				
			
				int seq = rs.getInt(1);
				String _id = id;
				String hotelname = rs.getString(2);
				String request = rs.getString(3);
				String CHECKIN = rs.getString(4);
				String CHECKout = rs.getString(5);
				String regdate = rs.getString(6);
				int del = rs.getInt(7);
				ReserveDto dto = new ReserveDto(seq, _id, hotelname, request,CHECKIN,CHECKout , regdate, del);
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
			
			
			while(rs.next()){			
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

	
	
	
}
