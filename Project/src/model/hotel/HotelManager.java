package model.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import db.DBClose;
import db.DBConnection;
import dto.HotelDto;

public class HotelManager implements iHotelManager {
	
	public HotelManager() {
		DBConnection.initConnect();
	}
	

@Override
	public List<HotelDto> getSearchHotelList(String place, String price, String people, String date1, String date2) {
		
		
		String sql1 = " SELECT R.HOTELNAME "
				+ " FROM HOTEL H, RESERVE R "
				+ " WHERE H.SEQ = R.NUM "
				+ " AND CHECKIN BETWEEN ? AND ? "
				+ " AND CHECKOUT BETWEEN ? AND ? ";
		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<HotelDto> list = new ArrayList<HotelDto>();
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 getSearchHotelList Success");
			
			psmt = conn.prepareStatement(sql1);
			System.out.println("2/6 getSearchHotelList Success");
			
			psmt.setString(1, date1);
			System.out.println("3/6 getSearchHotelList Success");
			psmt.setString(2, date2);
			System.out.println("4/6 getSearchHotelList Success");
			psmt.setString(3, date1);
			System.out.println("5/6 getSearchHotelList Success");
			psmt.setString(4, date2);
			System.out.println("6/6 getSearchHotelList Success");
			
			rs = psmt.executeQuery();
			System.out.println("7/6 getSearchHotelList Success");
			
			if(rs.next()) {
				list = getHotelList1(place, price, people, date1, date2);
				System.out.println("8/6 getSearchHotelList Success");
			}else {
				list = getHotelList2(place, price, people);
				System.out.println("9/6 getSearchHotelList Success");
			}
			
			System.out.println("10/6 getSearchHotelList Success");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("getSearchHotelList Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}


	@Override
	public void readCountPlus(int seq) {
		
		String sql = " UPDATE HOTEL "
				+ " SET READCOUNT = READCOUNT+1 "
				+ " WHERE SEQ=" + seq;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 readCountPlus Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 readCountPlus Success");
			
			psmt.executeQuery();
			System.out.println("3/6 readCountPlus Success");
			
			
		} catch(Exception e){
			System.out.println("readCountPlus Fail");
			
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
		
	}

	@Override
	public HotelDto getHotelDetail(int seq) {
		
		// 조회수 + 1 먼저 해주고
		readCountPlus(seq);
		
		// 디테일 가져온다
		String sql = " SELECT DESCRIPTION, READCOUNT, REGDATE  "
				+ " FROM HOTEL "
				+ " WHERE SEQ=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		HotelDto detailDto = new HotelDto();
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 getHotelDetail Success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getHotelDetail Success");
			psmt.setInt(1, seq);
			System.out.println("3/6 getHotelDetail Success");
			rs = psmt.executeQuery();
			System.out.println("4/6 getHotelDetail Success");
			
			if(rs.next()) {
				detailDto.setDescription(rs.getString(1).replaceAll(" ", "&nbsp;").replaceAll("\n", "<br>"));
				detailDto.setReadcount(rs.getInt(2));
				detailDto.setRegdate(rs.getString(3));
				System.out.println("5/6 getHotelDetail Success");
				
			}
			
		} catch(Exception e) {
			System.out.println("getHotelDetail Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return detailDto;
		
		
	}
	
@Override
	public List<HotelDto> getHotelList1(String place, String price, String people, String date1, String date2) {
		
		String sql2 = " SELECT SEQ, ID, HOTELNAME, REGION, MAXPEOPLE, PRICE, HOTELPHONE, DEL, READCOUNT, IMAGE "
				+ " FROM HOTEL "
				+ " WHERE HOTELNAME != ( "
				+ 		" SELECT R.HOTELNAME "
				+ 		" FROM HOTEL H, RESERVE R "
				+ 		" WHERE H.SEQ = R.NUM "
				+ 		" AND CHECKIN BETWEEN ? AND ? "
				+ 		" AND CHECKOUT BETWEEN ? AND ? ) "
				+ " AND REGION LIKE '%'||?||'%' "
				+ " AND PRICE >=? "
				+ " AND MAXPEOPLE >=? "
				+ " ORDER BY REGDATE DESC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<HotelDto> list = new ArrayList<HotelDto>();
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 getHotelList1 Success");
			
			psmt = conn.prepareStatement(sql2);
			System.out.println("2/6 getHotelList1 Success");
			
			psmt.setString(1, date1);
			System.out.println("3/6 getHotelList1 Success");
			psmt.setString(2, date2);
			System.out.println("4/6 getHotelList1 Success");
			psmt.setString(3, date1);
			System.out.println("5/6 getHotelList1 Success");
			psmt.setString(4, date2);
			System.out.println("6/6 getHotelList1 Success");
			psmt.setString(5, place.substring(0, 2));
			System.out.println("7/6 getHotelList1 Success");
			psmt.setInt(6, Integer.parseInt(price));
			System.out.println("8/6 getHotelList1 Success");
			psmt.setInt(7, Integer.parseInt(people));
			System.out.println("9/6 getHotelList1 Success");
			
			rs = psmt.executeQuery();
			System.out.println("10/6 getHotelList1 Success");
			
			
			while(rs.next()) {
				int i = 1;
				
				System.out.println("getHotelList1 결과 들어옴 ");
				
				HotelDto d = new HotelDto(rs.getInt(i++),
						rs.getString(i++),
						rs.getString(i++),
						"",
						rs.getString(i++),
						rs.getInt(i++),
						rs.getInt(i++),
						rs.getString(i++),
						rs.getInt(i++),
						rs.getInt(i++),
						"",
						rs.getString(i++));
				
				list.add(d);
				
			}
			System.out.println("11/6 getHotelList1 Success");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("getHotelList1 Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
		
	}

@Override
	public List<HotelDto> getHotelList2(String place, String price, String people) {

		String sql3 = " SELECT SEQ, ID, HOTELNAME, REGION, MAXPEOPLE, PRICE, HOTELPHONE, DEL, READCOUNT, IMAGE "
				+ " FROM HOTEL "
				+ " WHERE REGION LIKE '%'||?||'%' "
				+ " AND PRICE >=? "
				+ " AND MAXPEOPLE >=? "
				+ " ORDER BY REGDATE DESC ";
			

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<HotelDto> list = new ArrayList<HotelDto>();
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 getHotelList2 Success");
			
			psmt = conn.prepareStatement(sql3);
			System.out.println("2/6 getHotelList2 Success");
			
			psmt.setString(1, place.substring(0, 2));
			System.out.println("3/6 getHotelList2 Success");
			psmt.setInt(2, Integer.parseInt(price));
			System.out.println("4/6 getHotelList2 Success");
			psmt.setInt(3, Integer.parseInt(people));
			System.out.println("5/6 getHotelList2 Success");
			
			rs = psmt.executeQuery();
			System.out.println("6/6 getHotelList2 Success");
			
			while(rs.next()) {
				int i = 1;
				
				System.out.println("getHotelList2 결과 들어옴 ");
				
				HotelDto d = new HotelDto(rs.getInt(i++),
						rs.getString(i++),
						rs.getString(i++),
						"",
						rs.getString(i++),
						rs.getInt(i++),
						rs.getInt(i++),
						rs.getString(i++),
						rs.getInt(i++),
						rs.getInt(i++),
						"",
						rs.getString(i++));
				
				list.add(d);
				
			}
			System.out.println("7/6 getHotelList2 Success");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("getHotelList2 Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}

	public boolean ad_HotelUpdate(String hotelname, String DESCRIPTION, int MAXPEOPLE, int PRICE,
		String HOTELPHONE) {
	String sql = " UPDATE hotel SET "
			+ " DESCRIPTION=?, MAXPEOPLE=?, PRICE=?, HOTELPHONE=? "
			+ " WHERE hotelname=? ";
	
	Connection conn = null;
	PreparedStatement psmt = null;
	int count = 0;
	

		try {
		conn = DBConnection.getConnection();
	
		
	
		System.out.println("2/6  ad_HotelUpdate");
		
		psmt = conn.prepareStatement(sql);
		psmt.setString(1, DESCRIPTION);
		psmt.setInt(2, MAXPEOPLE);
		psmt.setInt(3, PRICE);
		psmt.setString(4, HOTELPHONE);
		psmt.setString(5, hotelname);
		
		
		System.out.println("3/6  ad_HotelUpdate");
		
		count = psmt.executeUpdate();
		System.out.println("4/6 ad_HotelUpdate");
		
	} catch (Exception e) {			
		e.printStackTrace();
	} finally{
		DBClose.close(psmt, conn, null);	
		System.out.println("5/6 ad_HotelUpdate");
	}		
	
	return count>0?true:false;
}

	public HotelDto getHoteldetail(String hotelname) {
		HotelDto dto = null;
	
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT id, HOTELNAME, DESCRIPTION, region,MAXPEOPLE, PRICE, HOTELPHONE,image "
				+ " FROM hotel "
				+ " WHERE hotelname=? ";
		
		try {			
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 getHoteldetail");
			psmt = conn.prepareStatement(sql);	
			System.out.println("2/6 getHoteldetail");
			psmt.setString(1, hotelname);			
			rs = psmt.executeQuery();
			System.out.println("3/6 getHoteldetail");
		
			while(rs.next()){				
				String id = rs.getString(1);
				String _hotelname = rs.getString(2);
				String description = rs.getString(3);
				String region = rs.getString(4);	
				int maxpeople = rs.getInt(5);
				int price = rs.getInt(6);
				String hotelphone = rs.getString(7);
				String image = rs.getString(8);	
				
				System.out.println("이미지경로 테스트:"+image);
				dto = new HotelDto(id, _hotelname, description, region, maxpeople, price, hotelphone, image);
			}
			
		} catch (Exception e) {		
			System.out.println("fail getHoteldetail");
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, rs);	
		}
		return dto;
	}

	public List<String> getMonthlyChart(String hotelname) {
		String sql = " SELECT REALDATE "
				+ " FROM reserve "
				+ " WHERE hotelname=? , del=0 ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<String> list = null;
		
		String[] rdate = null;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			System.out.println("1/6 login Success");
			
			psmt.setString(1, hotelname);
			
			
			System.out.println("2/6 login Success");
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String s = rs.getString(1);
				
				list.add(s);
			}
			System.out.println("3/6 login Success");
			
		} catch (Exception e) {
			System.out.println("login fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		
		return list;
	}

	public String getPrice(String hotelname) {
		String sql = " SELECT price "
				+ " FROM hotel "
				+ " WHERE hotelname= '" + hotelname + "'";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String p = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			System.out.println("1/6 login Success");

			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				p = rs.getString(1);
			}
			System.out.println("3/6 login Success");
			
		} catch (Exception e) {
			System.out.println("login fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
		
		return p;
	}
	
	

}
