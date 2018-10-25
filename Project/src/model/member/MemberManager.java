package model.member;


import db.DBClose;
import db.DBConnection;
import dto.MemberDto;
import dto.ReserveDto;
import dto.ReserveTableDto;
import dto.ReviewDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
			
			count = psmt.executeUpdate();	
			System.out.println("3/3 addMember Success");
		} catch (Exception e) {			
			System.out.println("addMember False");
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


	@Override
	public List<ReserveTableDto> ad_GetHotelmember(String hotelname, String sWord, String selected) {
		List<ReserveTableDto> list = new ArrayList<>();
		
		// 검색어
		String Word = "";
		if(selected.equals("이름")) {	
			Word = " and m.name LIKE '%" + sWord.trim() + "%'";
		}else if(selected.equals("아이디")) {	
			Word = " and m.id='" + sWord.trim() + "' ";
		}
		
		String sql = " SELECT  m.id, m.name,m.email,m.phone, e.checkin,e.checkout,m.blacklist  FROM member m, RESERVE e "
				+ " WHERE e.HOTELNAME = ? and m.id=e.id " + Word;
		
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
				int blacklist = rs.getInt(7);
				ReserveTableDto dto = new ReserveTableDto(id, name, email, phone, checkin,checkout,blacklist);
				
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
	public boolean profileedit(String id, String pw, String name, String email, String phone) {
		String sql = " UPDATE member SET " + " pwd=?, name=?, email=?, phone=? " + " WHERE id=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();

			System.out.println("2/6  profile update");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pw);
			psmt.setString(2, name);
			psmt.setString(3, email);
			psmt.setString(4, phone);
			psmt.setString(5, id);

			System.out.println("3/6  profile update");

			count = psmt.executeUpdate();
			System.out.println("4/6 profile update");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
			System.out.println("5/6 profile update");
		}

		return count > 0 ? true : false;
	}

	//////////////////////////
	
	

	@Override
	public MemberDto login(String id) {
		String sql = " SELECT ID, pwd, name, email , phone, blacklist, auth " + " FROM MEMBER " + " WHERE ID=? ";

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

			if (rs.next()) {
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
	public boolean addMember(MemberService ms) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MemberDto ad_login(String id) {
		String sql = " SELECT ID, pwd, name, email , phone, blacklist, auth " + " FROM MEMBER " + " WHERE ID=? ";

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

			if (rs.next()) {
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
	public List<ReserveDto> reserveList(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	@Override
	public List<MemberDto> ad_memlist(String sWord,String selected) {
		List<MemberDto> list = new ArrayList<>();
		
		// 검색어
		String Word = "";
		if(selected.equals("이름")) {	
			Word = " WHERE  name LIKE '%" + sWord.trim() + "%'";
		}else if(selected.equals("아이디")) {	
			Word = " WHERE id='" + sWord.trim() + "' ";
		}
		
		String sql = " SELECT  *  FROM member "
				 + Word;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 memlist Success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 memlist Success");
		
			
			
			
			
			rs = psmt.executeQuery();
			System.out.println("3/6 memlist Success");
			
			
			while(rs.next()){			
				
				System.out.println("4/6 memlist Success");
				
			
				String id = rs.getString(1);
				String pwd = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				String phone = rs.getString(5);
				int blacklist = rs.getInt(6);
				int auth = rs.getInt(7);
				
				MemberDto dto  = new MemberDto(id, pwd, name, email, phone, blacklist, auth);
				
				
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

	@Override
	public boolean ad_memDel(String id) {
		String sql = " UPDATE member SET " + "blacklist = 1 " + " WHERE id=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();

			System.out.println("2/6  ad_memDel update");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			

			System.out.println("3/6  ad_memDel update");

			count = psmt.executeUpdate();
			System.out.println("4/6 ad_memDel update");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
			System.out.println("5/6 ad_memDel update");
		}

		return count > 0 ? true : false;
	}

	
	
	
	
	
	
	
	
	
	}
	
