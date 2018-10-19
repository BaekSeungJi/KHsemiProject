package model.reserve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.CalendarDto;
import dto.ReserveDto;
import model.pds.PdsManager;

public class ReserveManager implements iReserveManager {
	
	// 생성할 때 db연결
	public ReserveManager() {
	DBConnection.initConnect();
	}
	
	
	// 예약 리스트 가져오기
	@Override
	public List<ReserveDto> getreserveList(String id) {

	String sql = " SELECT SEQ, HOTELNAME, REQUEST , CHECKIN , CHECKOUT, REGDATE, DEL "
			+ " FROM RESERVE "
			+ " WHERE ID = ? ";
		List<ReserveDto> list = new ArrayList<>();
				
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
					
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getreserveList Success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getreserveList Success");
			System.out.println(id);
					
			psmt.setString(1, id);
					
			System.out.println("3/6 getreserveList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getreserveList Success");
					
					
			while(rs.next()){			
				System.out.println("5/6 getreserveList Success");
						
					
				int seq = rs.getInt(1);
				String _id = id;
				String hotelname = rs.getString(2);
				String request = rs.getString(3);
				String checkin = rs.getString(4);
				String checkout = rs.getString(5);
				String regdate = rs.getString(6);
				int del = rs.getInt(7);
				ReserveDto dto = new ReserveDto(seq, _id, hotelname, request, checkin, checkout, regdate, del);
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
	public boolean reserve(ReserveDto dto) {
		
		String sql = " INSERT INTO RESERVE( "
				+ " SEQ, ID, HOTELNAME, REQUEST , CHECKIN , CHECKOUT, REGDATE, DEL ) "
				+ " VALUES(SEQ_RESERVE.NEXTVAL, "
				+ " ?, ?, ?, ?, ?, SYSDATE, 0) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 S reserve");
					
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getHotelname());
			psmt.setString(3, dto.getRequest());
			psmt.setString(4, dto.getCheckin());
			psmt.setString(5, dto.getCheckout());
			System.out.println("2/6 S reserve");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 S reserve");
			
		} catch (Exception e) {			
			System.out.println("fail addCalendar");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);	
			System.out.println("4/6 S reserve");
		}
		
		return count>0?true:false;
	}
	
	

	@Override
	public boolean ad_reserveUpdate(int seq, String regdate, String request) {
		String sql = " UPDATE reserve SET "
				+ " regdate=?, request=? "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
	
			try {
			conn = DBConnection.getConnection();
		
			
		
			System.out.println("2/6 S updateBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, regdate);
			psmt.setString(2, request);
			psmt.setInt(3, seq);
			
			System.out.println("3/6 S updateBbs");
			
			count = psmt.executeUpdate();
			System.out.println("4/6 S updateBbs");
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);	
			System.out.println("5/6 S updateBbs");
		}		
		
		return count>0?true:false;
	}

	@Override
	public boolean ad_reservedelete(int seq) {
		String sql=" UPDATE reserve SET  "
				+" DEL=1 "
				+ " WHERE SEQ=? ";
		
		int count = 0;
		Connection conn=null;
		PreparedStatement psmt=null;
		
		
			try {
			conn = DBConnection.getConnection();
		
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);			
			count = psmt.executeUpdate();
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);			
		}
				
		return count>0?true:false;
	}

	@Override
	public List<ReserveDto> getCalendarList(String hotelname, String yyyyMM) {
		return null;
		
	}

	@Override
	public List<ReserveDto> getReserve(String hotelname) {
		return null;

	}

	@Override
	public List<ReserveDto> getReservelist(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
