package model.hotel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;

import db.DBClose;
import db.DBConnection;
import dto.HotelDto;

public class HotelManager implements iHotelManager {
	

	@Override
	public List<HotelDto> getSearchHotelList(String place, String price, String people, String date1, String date2) {
		
		
		String sql = " SELECT SEQ, ID, HOTELNAME, REGION, MAXPEOPLE, PRICE, HOTELPHONE, DEL, READCOUNT "
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
			System.out.println("1/6 getSearchHotelList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getSearchHotelList Success");
			
			psmt.setString(1, place.substring(0, 2));
			System.out.println("3/6 getSearchHotelList Success");
			psmt.setInt(2, Integer.parseInt(price));
			System.out.println("4/6 getSearchHotelList Success");
			psmt.setInt(3, Integer.parseInt(people));
			System.out.println("5/6 getSearchHotelList Success");
			
			rs = psmt.executeQuery();
			System.out.println("6/6 getSearchHotelList Success");
			
			while(rs.next()) {
				int i = 1;
				
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
						""
						);
				
				list.add(d);
				
			}
			System.out.println("5/6 getSearchHotelList Success");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("getSearchHotelList Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}

	
	
	
	

}
