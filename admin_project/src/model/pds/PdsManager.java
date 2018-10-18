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
import dto.PdsfileDto;

public class PdsManager implements iPdsManager {

	// 생성할 때 db연결
	public PdsManager() {
		DBConnection.initConnect();
	}

	@Override
	public List<PdsfileDto> getPdsList() {
		String sql = " SELECT p.SEQ, p.ID, p.TITLE, p.CONTENT, f.FILENAME, f.REALNAME, p.READCOUNT, p.DOWNCOUNT, p.REF, p.STEP, p.DEPTH, p.DEL, p.REGDATE "
				+ " FROM PDS p, FILE_DB f "
				+ " where p.SEQ = f.num "
				+ " ORDER BY REF DESC, STEP ASC  ";
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	List<PdsfileDto> list = new ArrayList<PdsfileDto>();
	
	try {
		conn = DBConnection.getConnection();
		System.out.println("1/6 getPdsList Success");
		
		psmt = conn.prepareStatement(sql);
		System.out.println("2/6 getPdsList Success");
		
		rs = psmt.executeQuery();
		System.out.println("3/6 getPdsList Success");
		
		while(rs.next()) {
			
			PdsfileDto dto = new PdsfileDto(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9),
					rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getString(13));
			
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
	public PdsfileDto getPds(int seq) {

		String sql = " SELECT p.SEQ, p.ID, p.TITLE, p.CONTENT, f.FILENAME, f.REALNAME, p.READCOUNT, p.DOWNCOUNT, p.REF, p.STEP, p.DEPTH, p.DEL, p.REGDATE "
				+ " FROM PDS p, FILE_DB f "
				+ " where p.SEQ = f.num and p.seq = ? "
				+ " ORDER BY REF DESC, STEP ASC  ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		PdsfileDto dto = null;
		
		
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
				dto = new PdsfileDto(rs.getInt(i), rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++), rs.getInt(i++), rs.getString(i++));
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
				+ " VALUES(SEQ_PDS.NEXTVAL, ?, ?, ?, 0, 0, (SELECT NVL(MAX(REF), 0)+1 FROM pds), 0, 0, 0, SYSDATE ) ";
		
		try {
			
				conn = DBConnection.getConnection();
			
			
				
			
			System.out.println("2/6 writePds Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 writePds Success");
			
			psmt.setString(1, pds.getId());
			psmt.setString(2, pds.getTitle());
			psmt.setString(3, pds.getContent());
			
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
	public boolean writeFile(FileDto File) {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO FILE_DB "
				+ " VALUES(SEQ_FILE_DB.NEXTVAL, ?, ?) ";
		
		try {
			
				conn = DBConnection.getConnection();
			
			
			
			
			System.out.println("2/6 writeFile Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 writeFile Success");
			
			psmt.setString(1, File.getFilename());
			psmt.setString(2, File.getRealname());

			System.out.println( File.getFilename());
			System.out.println( File.getRealname());
			count = psmt.executeUpdate();
			System.out.println("4/6 writeFile Success");
			
		} catch (Exception e) {			
			System.out.println("writeFile fail");
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

	
}
