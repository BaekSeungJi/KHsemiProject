package model.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;

import dto.HotelDto;
import dto.MemberDto;
import dto.MonthlysalesDto;
import dto.ReserveDto;

public class HotelManager implements iHotelManager {
		
	
		// 생성할 때 db연결
		public HotelManager() {
			DBConnection.initConnect();
		}

		@Override
		public boolean ad_HotelUpdate(String hotelname, String DESCRIPTION, int MAXPEOPLE, int PRICE,
				int HOTELPHONE) {
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
				psmt.setInt(4, HOTELPHONE);
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

		@Override
		public HotelDto getHoteldetail(String hotelname) {
			HotelDto dto = null;
		
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			String sql = " SELECT id, HOTELNAME, DESCRIPTION, region,MAXPEOPLE, PRICE, HOTELPHONE "
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
					int hotelphone = rs.getInt(7);
				
					dto = new HotelDto(id, _hotelname, description, region, maxpeople, price, hotelphone);
				}
				
			} catch (Exception e) {		
				System.out.println("fail getHoteldetail");
				e.printStackTrace();
			} finally{
				DBClose.close(psmt, conn, rs);	
			}
			return dto;
		}

		@Override
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

		
}
