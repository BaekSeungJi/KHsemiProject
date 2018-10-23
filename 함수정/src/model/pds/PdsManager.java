package model.pds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import db.DBClose;
import db.DBConnection;
import dto.FileDto;
import dto.PdsDto;


public class PdsManager implements iPdsManager {

	// 생성할 때 db연결
	public PdsManager() {
		DBConnection.initConnect();
	}

	@Override
	public List<PdsDto> getPdsList() {
		String sql = " SELECT seq,id,title,content,readcount,downcount,filename,ref,step,depth,del, regdate "
				+ " FROM PDS "
			
				+ " ORDER BY REF DESC, STEP ASC ";
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	List<PdsDto> list = new ArrayList<PdsDto>();
	
	try {
		conn = DBConnection.getConnection();
		System.out.println("1/6 getPdsList Success");
		
		psmt = conn.prepareStatement(sql);
		System.out.println("2/6 getPdsList Success");
		
		rs = psmt.executeQuery();
		System.out.println("3/6 getPdsList Success");
		
		while(rs.next()) {
			PdsDto dto = new PdsDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getString(12));
			list.add(dto);
		}		
		System.out.println("4/6 getPdsList Success");
		
	} catch (Exception e) {
		System.out.println("getPdsList Fail");
		e.printStackTrace();
	} finally {			
		DBClose.close(psmt, conn, rs);			
	}
			
	return list;
}


	@Override
	public void readCount(int seq) {
		String sql=" UPDATE PDS SET  "
				+" READCOUNT=READCOUNT+1 "
				+ " WHERE SEQ=? ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;

		
			try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 readCount Success");

			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/6 readCount Success");

			psmt.executeUpdate();			
			System.out.println("3/6 readCount Success");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" readCount fail");

		}finally{
			DBClose.close(psmt, conn, rs);	
		}		
	}


	@Override
	public void downCount(int seq) {
		String sql=" UPDATE PDS SET  "
				+" DOWNCOUNT=DOWNCOUNT+1 "
				+ " WHERE SEQ=? ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		
			try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 downCount Success");

			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			psmt.executeUpdate();			
			System.out.println("2/6 downCount Success");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" downCount fail");

		}finally{
			DBClose.close(psmt, conn, rs);	
		}		
	}




	@Override
	public PdsDto getPds(int seq) {

		String sql = " SELECT * "
				+ " FROM PDS "
				+ " WHERE SEQ=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		PdsDto dto = null;
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getPds Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			System.out.println("2/6 getPds Success");
			
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getPds Success");
			
			if(rs.next()) {
				int i = 1;
				 dto = new PdsDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getString(12));
				System.out.println("4/6 getPds Success");
			}
		} catch (Exception e) {
			System.out.println("getPds fail");
			e.printStackTrace();
		}finally {
			DBClose.close(psmt, conn, rs);
		}
				
		
	return dto;	
	}

	@Override
	public boolean writePds(PdsDto pds) {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO pds "
				+ " VALUES(SEQ_PDS.NEXTVAL, ?, ?, ?, 0, 0, ?,(SELECT NVL(MAX(REF), 0)+1 FROM pds), 0, 0, 0, SYSDATE ) ";
		
		try {
			
				conn = DBConnection.getConnection();
			
			
				
			
			System.out.println("2/6 writePds Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 writePds Success");
			
			psmt.setString(1, pds.getId());
			psmt.setString(2, pds.getTitle());
			psmt.setString(3, pds.getContent());
			psmt.setString(4, pds.getFilename());
			count = psmt.executeUpdate();
			System.out.println("4/6 writePds Success");
			
		} catch (Exception e) {			
			System.out.println("writePds fail");
		} finally{
			DBClose.close(psmt, conn, rs);			
		}
		
		return count>0?true:false;		
	}



	
	

	@Override
	public boolean pdsDelete(int seq) {
		String sql=" UPDATE pds SET  "
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
	public boolean ad_PdsUpdate(int seq, PdsDto dto) {
		String sql = " UPDATE pds SET "
				+ " title=?, content=?, filename=? "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
	
			try {
			conn = DBConnection.getConnection();
		
			
		
			System.out.println("2/6 S updateBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getFilename());
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
	public boolean answer(int seq, PdsDto pds) {

		// update
		String sql1 = " UPDATE pds "
				+ " SET STEP=STEP+1 "
				+ " WHERE REF=(SELECT REF FROM pds WHERE SEQ=?) "
				+ " AND STEP > (SELECT STEP FROM pds WHERE SEQ=?) ";
		
		//insert
		String sql2 = " INSERT INTO pds "
		+ " VALUES(SEQ_PDS.NEXTVAL, ?, ?, ?, 0, 0, ?,(SELECT REF FROM pds WHERE SEQ=?),(SELECT STEP FROM pds WHERE SEQ=?)+1,(SELECT DEPTH FROM pds WHERE SEQ=?)+1, 0, SYSDATE ) ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			System.out.println("1/6 answer Success");
			
			
			psmt= conn.prepareStatement(sql1);
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
			
			System.out.println("2/6 answer Success");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 answer Success");
			
			psmt.clearParameters();
			
			psmt= conn.prepareStatement(sql2);
			
			psmt.setString(1, pds.getId());
			psmt.setString(2, pds.getTitle());
			psmt.setString(3, pds.getContent());
			psmt.setString(4, "answer");
			psmt.setInt(5, seq);
			psmt.setInt(6, seq);
			psmt.setInt(7, seq);
			
			System.out.println("4/6 answer Success");
			
			count = psmt.executeUpdate();
			conn.commit();
			System.out.println("5/6 answer Success");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}finally {
			
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBClose.close(psmt, conn, null);
			System.out.println("6/6 answer Success");
		}
		
		return count>0?true:false;
		

	}
}
