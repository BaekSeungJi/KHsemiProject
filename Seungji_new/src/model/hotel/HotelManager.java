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
		
		
		System.out.println("체크인 = " + date1);
		System.out.println("체크아웃 = " + date2);
		
		String sql1 = " SELECT R.HOTELNAME "
				+ " FROM HOTEL H, RESERVE R "
				+ " WHERE H.SEQ = R.NUM "
				+ " AND CHECKIN BETWEEN '2018/10/20' AND '2018/10/23' "
				+ " AND CHECKOUT BETWEEN '2018/10/20' AND '2018/10/22' ";
		
		
		/*
		SELECT SEQ, HOTELNAME
		FROM HOTEL
		WHERE HOTELNAME != (
		   SELECT R.HOTELNAME
		   FROM HOTEL H, RESERVE R
		   WHERE H.SEQ = R.NUM
		   AND CHECKIN BETWEEN '2018/10/20' AND '2018/10/23'
		   AND CHECKOUT BETWEEN '2018/10/20' AND '2018/10/23')
		AND REGION LIKE '%역삼%'
		AND PRICE >= 1000
		AND MAXPEOPLE >= 1
		 */
		
		/*String sql = " SELECT HOTEL.SEQ, HOTEL.ID, HOTEL.HOTELNAME, HOTEL.REGION, HOTEL.MAXPEOPLE, HOTEL.PRICE, HOTEL.HOTELPHONE, HOTEL.DEL, HOTEL.READCOUNT, HOTEL.IMAGE "
				+ " FROM HOTEL LEFT JOIN RESERVE "
				+ " ON HOTEL.SEQ = RESERVE.NUM "
				+ " AND RESERVE.CHECKIN >= '2017/10/22' "
				+ " AND RESERVE.CHECKOUT < '2017/10/24' "
				+ " AND HOTEL.REGION LIKE '%역삼%' "
				+ " AND HOTEL.PRICE >= 1000 "
				+ " AND HOTEL.MAXPEOPLE >= 1 "
				+ " ORDER BY HOTEL.REGDATE DESC ";*/
				
				/*
				+ " AND REGION LIKE '%'||?||'%' "
				+ " AND PRICE >=? "
				+ " AND MAXPEOPLE >=? "
				+ " ORDER BY REGDATE DESC ";
		
		*/
		/*
			SELECT HOTEL.SEQ, HOTEL.ID, HOTEL.HOTELNAME, HOTEL.REGION
			FROM HOTEL LEFT JOIN RESERVE
			ON HOTEL.SEQ = RESERVE.NUM
			AND RESERVE.CHECKIN >= '2017/10/22'
			AND RESERVE.CHECKOUT < '2017/10/24'
			AND HOTEL.REGION LIKE '%역삼%' 
			AND HOTEL.PRICE >= 1000
			AND HOTEL.MAXPEOPLE >= 1
			ORDER BY HOTEL.REGDATE DESC
		 */

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<HotelDto> list = new ArrayList<HotelDto>();
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 getSearchHotelList Success");
			
			psmt = conn.prepareStatement(sql1);
			System.out.println("2/6 getSearchHotelList Success");
			
			/*psmt.setString(1, date1);
			System.out.println("3/6 getSearchHotelList Success");
			psmt.setString(2, date2);
			System.out.println("4/6 getSearchHotelList Success");
			psmt.setString(3, place.substring(0, 2));
			System.out.println("5/6 getSearchHotelList Success");
			psmt.setInt(4, Integer.parseInt(price));
			System.out.println("6/6 getSearchHotelList Success");
			psmt.setInt(5, Integer.parseInt(people));
			System.out.println("7/6 getSearchHotelList Success");*/
			
			rs = psmt.executeQuery();
			System.out.println("8/6 getSearchHotelList Success");
			
			if(rs.next()) {
				list = getHotelList1(place, price, people, date1, date2);
			}else {
				list = getHotelList2(place, price, people);
			}
			
			System.out.println("9/6 getSearchHotelList Success");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("getSearchHotelList Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}
	

	@Override
	public List<HotelDto> getHotelList1(String place, String price, String people, String date1, String date2) {
		
		String sql2 = " SELECT SEQ, ID, HOTELNAME, REGION, MAXPEOPLE, PRICE, HOTELPHONE, DEL, READCOUNT, IMAGE "
				+ " FROM HOTEL "
				+ " WHERE HOTELNAME != ( "
				+ 		" SELECT R.HOTELNAME "
				+ 		" FROM HOTEL H, RESERVE R "
				+ 		" WHERE H.SEQ = R.NUM "
				+ 		" AND CHECKIN BETWEEN '2018/10/20' AND '2018/10/23' "
				+ 		" AND CHECKOUT BETWEEN '2018/10/20' AND '2018/10/23' ) "
				+ " AND REGION LIKE '%역삼%' "
				+ " AND PRICE >= 1000 "
				+ " AND MAXPEOPLE >= 1 ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<HotelDto> list = new ArrayList<HotelDto>();
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 getHotelList1 Success");
			
			psmt = conn.prepareStatement(sql2);
			System.out.println("2/6 getHotelList1 Success");
			
			/*psmt.setString(1, date1);
			System.out.println("3/6 getSearchHotelList Success");
			psmt.setString(2, date2);
			System.out.println("4/6 getSearchHotelList Success");
			psmt.setString(3, place.substring(0, 2));
			System.out.println("5/6 getSearchHotelList Success");
			psmt.setInt(4, Integer.parseInt(price));
			System.out.println("6/6 getSearchHotelList Success");
			psmt.setInt(5, Integer.parseInt(people));
			System.out.println("7/6 getSearchHotelList Success");*/
			
			rs = psmt.executeQuery();
			System.out.println("8/6 getHotelList1 Success");
			
			
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
			System.out.println("9/6 getHotelList1 Success");
			
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
				+ " WHERE REGION LIKE '%역삼%' "
				+ " AND PRICE >= 1000 "
				+ " AND MAXPEOPLE >= 1 ";
			

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<HotelDto> list = new ArrayList<HotelDto>();
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 getHotelList2 Success");
			
			psmt = conn.prepareStatement(sql3);
			System.out.println("2/6 getHotelList2 Success");
			
			/*psmt.setString(1, date1);
			System.out.println("3/6 getSearchHotelList Success");
			psmt.setString(2, date2);
			System.out.println("4/6 getSearchHotelList Success");
			psmt.setString(3, place.substring(0, 2));
			System.out.println("5/6 getSearchHotelList Success");
			psmt.setInt(4, Integer.parseInt(price));
			System.out.println("6/6 getSearchHotelList Success");
			psmt.setInt(5, Integer.parseInt(people));
			System.out.println("7/6 getSearchHotelList Success");*/
			
			rs = psmt.executeQuery();
			System.out.println("8/6 getHotelList2 Success");
			
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
			System.out.println("9/6 getHotelList2 Success");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("getHotelList2 Fail");
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
	
	
	

	
	
	
	

}
