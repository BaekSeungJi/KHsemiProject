package model.reserve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;

import dto.ReserveDto;
import model.pds.PdsManager;

public class ReserveManager implements iReserveManager {

	// 생성할 때 db연결
	public ReserveManager() {
		DBConnection.initConnect();
	}

	@Override
	public boolean ad_reserveUpdate(int seq, String checkin,String checkout, String request) {
		String sql = " UPDATE reserve SET "
				+ " checkin=?,checkout=?, request=? "
				+ " WHERE SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;


		try {
			conn = DBConnection.getConnection();



			System.out.println("2/6 S updateBbs");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, checkin);
			psmt.setString(2, checkout);
			psmt.setString(3, request);
			psmt.setInt(4, seq);

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
		System.out.println("delete함수 들옴");

		try {
			conn = DBConnection.getConnection();

			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);			
			count = psmt.executeUpdate();
			System.out.println("count"+count);
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);			
		}

		return count>0?true:false;
	}

	@Override
	public List<ReserveDto> getCalendarList(String hotelname, String yyyyMM) {
		String sql = " SELECT SEQ, ID, HOTELNAME, REQUEST, checkin,checkout, REGDATE, DEL "
				+ " FROM ( "
				+ " 	SELECT ROW_NUMBER() OVER(PARTITION BY SUBSTR(checkin, 1, 8) ORDER BY checkin ASC) RN, "
				+ "				SEQ, ID, HOTELNAME, REQUEST, checkin,checkout, REGDATE, DEL "
				+ "		FROM reserve "
				+ "		WHERE HOTELNAME=? AND SUBSTR(checkin, 1, 6)=?) "
				+ "  WHERE RN BETWEEN 1 AND 5 "
				+ " and del = 0 ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		System.out.println(hotelname.trim());
		System.out.println(yyyyMM.trim());

		List<ReserveDto> list = new ArrayList<ReserveDto>();

		ReserveDto dto = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getCalendarList Success");

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, hotelname.trim());
			psmt.setString(2, yyyyMM.trim());
			System.out.println("2/6 getCalendarList Success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getCalendarList Success");

			while(rs.next()) {
				dto = new ReserveDto();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setHotelname(rs.getString(3));
				dto.setRequest(rs.getString(4));
				dto.setCheckin(rs.getString(5));
				dto.setCheckout(rs.getString(6));
				dto.setRegdate(rs.getString(7));
				dto.setDel(rs.getInt(8));
				list.add(dto);			


			}
			System.out.println("4/6 getCalendarList Success");

		} catch (Exception e) {
			System.out.println("getCalendarList Fail");
		} finally {			
			DBClose.close(psmt, conn, rs);			
		}

		return list;
	}

	@Override
	public List<ReserveDto> getReserve(String hotelname) {

		String sql = " SELECT checkin,checkout FROM reserve "
				+ " WHERE hotelname = '" + hotelname + "'";

		List<ReserveDto> dto = new ArrayList<>();
		ReserveDto Rdto = null;


		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getReserve Success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getReserve Success");
			rs = psmt.executeQuery(sql);
			System.out.println("3/6 getReserve Success");

			while(rs.next()){			
				System.out.println("확인2:"+rs.getString(1));
				System.out.println("확인2:"+rs.getString(2));
				Rdto = new ReserveDto(0, "", "", "", rs.getString(1),rs.getString(2), "", 0);

				dto.add(Rdto);

			}
		} catch (Exception e1) {
			System.out.println("getReserve Fail");
			e1.printStackTrace();

		}	finally{			
			DBClose.close(psmt, conn, rs);			
		}



		return dto;
	}

	@Override
	public List<ReserveDto> getlist(String hotelname, String yyyymmdd) {
		String sql = " SELECT * "
				+ " FROM reserve "
				+ "	WHERE hotelname=? AND checkin =? ";

		System.out.println("getlist:"+yyyymmdd);
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		List<ReserveDto> list = new ArrayList<ReserveDto>();

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getlist Success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, hotelname.trim());
			psmt.setString(2, yyyymmdd.trim());
			System.out.println("2/6 getlist Success");

			rs = psmt.executeQuery();
			System.out.println("3/6 getlist Success");

			while(rs.next()) {
				ReserveDto dto = new ReserveDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getString(7), rs.getInt(8));
				System.out.println("지금:"+rs.getString(2));
				list.add(dto);				
			}
			System.out.println("4/6 getlist Success");

		} catch (Exception e) {
			System.out.println("getlist Fail");
		} finally {			
			DBClose.close(psmt, conn, rs);			
		}

		return list;
	}

	@Override
	public List<ReserveDto> getreserveList(String id) {
		String sql = " SELECT SEQ, HOTELNAME, REQUEST, CHECKIN, CHECKOUT, REGDATE, DEL "
				+ " FROM RESERVE "
				+ " WHERE ID=? ";
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
			System.out.println("getreserveList Fail");
			e1.printStackTrace();

		}	finally{			
			DBClose.close(psmt, conn, rs);			
		}

		return list;
	}

	public boolean reserve(String id, String hotelname, String request, String checkin, String checkout) {
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
			psmt.setString(1, id);
			psmt.setString(2, hotelname);
			psmt.setString(3, request);
			psmt.setString(4, checkin);
			psmt.setString(5, checkout);
			System.out.println("2/6 S reserve");
			System.out.println("checkin :" + checkin);
			System.out.println("checkout :" + checkout);

			count = psmt.executeUpdate();
			System.out.println("3/6 S reserve");

		} catch (Exception e) {			
			System.out.println("fail addreserve");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);	
			System.out.println("4/6 S reserve");
		}

		return count>0?true:false;
	}

	@Override
	public boolean reserveUpdate(int seq, String checkin, String checkout, String request) {
		String sql = " UPDATE reserve SET "
				+ " checkin=?, checkout=?, request=? "
				+ " WHERE SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;


		try {
			conn = DBConnection.getConnection();



			System.out.println("2/6 S updateBbs");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, checkin);
			psmt.setString(2, checkout);
			psmt.setString(3, request);
			psmt.setInt(4, seq);

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

	public boolean reservedelete(int seq) {
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

	public ReserveDto getDay(int seq) {

		String sql = " SELECT "
				+ " SELECT SEQ, HOTELNAME, REQUEST, CHECKIN, CHECKOUT, REGDATE, DEL "
				+ " FROM RESERVE "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		ReserveDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 S getDay");	
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("3/6 S getDay");	
			
			rs = psmt.executeQuery();
			System.out.println("4/6 S getDay");	
			
			while(rs.next()){
				dto = new ReserveDto();
				dto.setSeq(rs.getInt(1));
				dto.setHotelname(rs.getString(2));
				dto.setRequest(rs.getString(3));
				dto.setCheckin(rs.getString(4));
				dto.setCheckout(rs.getString(5));
				dto.setRegdate(rs.getString(6));
				dto.setDel(rs.getInt(7));					
			}	
			System.out.println("5/6 S getDay");			
						
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, rs);
			System.out.println("6/6 S getDay");			
		}		
		
		return dto;


}
}