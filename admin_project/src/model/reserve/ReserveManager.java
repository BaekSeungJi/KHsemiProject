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
		String sql = " SELECT SEQ, ID, HOTELNAME, REQUEST, REALDATE, REGDATE, DEL "
				+ " FROM ( "
				+ " 	SELECT ROW_NUMBER() OVER(PARTITION BY SUBSTR(RDATE, 1, 8) ORDER BY REALDATE ASC) RN, "
				+ "				SEQ, ID, HOTELNAME, REQUEST, REALDATE, REGDATE, DEL "
				+ "		FROM reserve "
				+ "		WHERE HOTELNAME=? AND SUBSTR(REALDATE, 1, 6)=?) "
				+ " WHERE RN BETWEEN 1 AND 5 ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<ReserveDto> list = new ArrayList<ReserveDto>();
		
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
				ReserveDto dto = new ReserveDto();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setHotelname(rs.getString(3));
				dto.setRequest(rs.getString(4));
				dto.setRealdate(rs.getString(5));
				dto.setRegdate(rs.getString(6));
				dto.setDel(rs.getInt(7));
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
}
