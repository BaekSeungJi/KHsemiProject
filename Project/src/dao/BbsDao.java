package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.BbsDto;
import dto.PagingBean;
import dto.PagingUtil;

public class BbsDao implements iBbsDao {
	
	private static BbsDao bbsdao = new BbsDao();
	
	private BbsDao() {}
	
	public static BbsDao getInstance() {
		return bbsdao;
	}

	@Override
	public List<BbsDto> getBbsList() {
		
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, WDATE, PARENT, "
				+ " DEL, READCOUNT "
				+ " FROM BBS "
				+ " ORDER BY REF DESC, STEP ASC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> list = new ArrayList<>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBbsList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getBbsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getBbsList Success");
			
			while(rs.next()) {
				
				BbsDto dto = new BbsDto(rs.getInt(1), 
										rs.getString(2), 
										rs.getInt(3), 
										rs.getInt(4), 
										rs.getInt(5), 
										rs.getString(6), 
										rs.getString(7), 
										rs.getString(8), 
										rs.getInt(9), 
										rs.getInt(10), 
										rs.getInt(11));
				list.add(dto);				
			}			
			System.out.println("4/6 getBbsList Success");
			
		} catch (Exception e) {			
			System.out.println("getBbsList Fail");
		} finally {
			DBClose.close(psmt, conn, rs);			
		}	
		
		return list;
	}
	
	@Override
	public List<BbsDto> getBbsPagingList(PagingBean paging, String searchWord, String choice) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<BbsDto> bbslist = new ArrayList<>();
		
		// 검색어
		String sWord = "";
		if(choice.equals("title")) {	// 제목
			sWord = " WHERE TITLE LIKE '%" + searchWord.trim() + "%'";
		}else if(choice.equals("writer")) {	// 작성자
			sWord = " WHERE ID='" + searchWord.trim() + "' ";
		}else if(choice.equals("content")) { // 내용
			
		} 
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBbsPagingList Success");
			
			String totalSql = " SELECT COUNT(SEQ) "
							+ "	FROM BBS "
							+ sWord;
			
			psmt = conn.prepareStatement(totalSql);
			rs = psmt.executeQuery();
			
			int totalCount = 0;
			rs.next();
			totalCount = rs.getInt(1);		// row의 총갯수
			paging.setTotalCount(totalCount);
			paging = PagingUtil.setPagingInfo(paging);
			
			psmt.close();
			rs.close();
			
			String sql = " SELECT * FROM "
					+ " (SELECT * FROM "
					+ "		(SELECT * FROM BBS "
					+ "		" + sWord	
					+ "		ORDER BY REF ASC, STEP DESC) "		
					+ " WHERE ROWNUM <=" + paging.getStartNum() + ""	// 시작번호
					+ " ORDER BY REF DESC, STEP ASC) "	
					+ " WHERE ROWNUM <=" + paging.getCountPerPage();	// 10개까지
					
			psmt = conn.prepareStatement(sql);			
			System.out.println("2/6 getBbsPagingList Success");		
			
			rs = psmt.executeQuery();
			System.out.println("3/6 getBbsPagingList Success");
			
			while(rs.next()) {
				BbsDto dto = new BbsDto(rs.getInt(1), 
										rs.getString(2), 
										rs.getInt(3), 
										rs.getInt(4), 
										rs.getInt(5), 
										rs.getString(6), 
										rs.getString(7), 
										rs.getString(8), 
										rs.getInt(9), 
										rs.getInt(10), 
										rs.getInt(11));
				bbslist.add(dto);				
			}
			System.out.println("4/6 getBbsPagingList Success");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);			
		}
				
		return bbslist;
	}

	@Override
	public boolean writeBbs(BbsDto bbs) {
		String sql = " INSERT INTO BBS "
				+ " (SEQ, ID, "
				+ " REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, WDATE, PARENT, "
				+ " DEL, READCOUNT) "
				+ " VALUES(SEQ_BBS.NEXTVAL, ?, "
				+ " (SELECT NVL(MAX(REF), 0)+1 FROM BBS), "				// ref
				+ " 0, 0, "							// step, depth
				+ " ?, ?, SYSDATE, 0, "
				+ " 0, 0) ";
		
		int count = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 writeBbs Success");	
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 writeBbs Success");
			
			psmt.setString(1, bbs.getId());
			psmt.setString(2, bbs.getTitle());
			psmt.setString(3, bbs.getContent());
			
			count = psmt.executeUpdate();
			System.out.println("3/6 writeBbs Success");
						
		} catch (Exception e) {
			System.out.println("writeBbs Fail");
		} finally {
			DBClose.close(psmt, conn, null);			
		}
				
		return count>0?true:false;
	}

	@Override
	public BbsDto getBbs(int seq) {
		String sql = " SELECT SEQ, ID, REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, WDATE, PARENT, DEL, READCOUNT "
				+ " FROM BBS "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		BbsDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 S getBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("3/6 S getBbs");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 S getBbs");
			
			while(rs.next()){
				int i = 1;
				dto = new BbsDto(
						rs.getInt(i++),	// seq 
						rs.getString(i++),	// id 
						rs.getInt(i++),	// ref 
						rs.getInt(i++), // step 
						rs.getInt(i++), // depth 
						rs.getString(i++), // title
						rs.getString(i++), // content 
						rs.getString(i++), // wdate 
						rs.getInt(i++),    // parent 
						rs.getInt(i++),		//	del 
						rs.getInt(i++));	// readcount
			}
			System.out.println("5/6 S getBbs");
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, rs);			
			System.out.println("6/6 S getBbs");
		}
		
		return dto;
	}

	@Override
	public void readcount(int seq) {
		String sql = " UPDATE BBS SET "
				+ " READCOUNT=READCOUNT+1 "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn=DBConnection.getConnection();
			System.out.println("2/6 S readcount");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("3/6 S readcount");
			
			psmt.executeUpdate();
			System.out.println("4/6 S readcount");
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);			
			System.out.println("5/6 S readcount");
		}
	}

	@Override
	public boolean updateBbs(int seq, String title, String content) {
		String sql = " UPDATE BBS SET "
				+ " TITLE=?, CONTENT=? "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 S updateBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
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
	public boolean deleteBbs(int seq) {
		String sql = " UPDATE BBS SET "
				+ " DEL=1 "
				+ " WHERE SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/6 S deleteBbs");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("3/6 S deleteBbs");
			
			count = psmt.executeUpdate();
			System.out.println("4/6 S deleteBbs");
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(psmt, conn, null);
			System.out.println("5/6 S deleteBbs");
		}
		
		return count>0?true:false;
	}

	@Override
	public boolean answer(int seq, BbsDto bbs) {
						// 부모글번호		답글DTO
		/* 
		  	11111111111111111111	3-0-0
			22222222222222222222	2-0-0
				--->				2-1-1
				-->		seq=4		2-2-1	->> 2.insert	
							-->		4-3-2				
				->					2-3-1	->> 1.update					
			33333333333333333333	1-0-0
		
		*/
		
		// update
		String sql1 = " UPDATE BBS "
				+ " SET STEP=STEP+1 "
				+ " WHERE REF=(SELECT REF FROM BBS WHERE SEQ=?) " // 현재 부모글의 SEQ와 같은 글들
				+ " 	AND STEP>(SELECT STEP FROM BBS WHERE SEQ=?) "; 
						
		// insert
		String sql2 = " INSERT INTO BBS "
				+ " (SEQ, ID, REF, STEP, DEPTH, "
				+ " TITLE, CONTENT, WDATE, PARENT, DEL, READCOUNT) "
				+ " VALUES(SEQ_BBS.NEXTVAL, ?, "
				+ "			(SELECT REF FROM BBS WHERE SEQ=?), "	// REF
				+ "			(SELECT STEP FROM BBS WHERE SEQ=?)+1, " // STEP	
				+ "			(SELECT DEPTH FROM BBS WHERE SEQ=?)+1, "// DEPTH 
				+ "			?, ?, SYSDATE, ?, 0, 0) "; 
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			System.out.println("1/6 S answer");
			
			psmt = conn.prepareStatement(sql1);	// update
			psmt.setInt(1, seq);
			psmt.setInt(2, seq);
			System.out.println("2/6 S answer");
			
			count = psmt.executeUpdate();
			System.out.println("3/6 S answer");
			
			psmt.clearParameters();
			
			psmt = conn.prepareStatement(sql2);	// insert
			psmt.setString(1, bbs.getId());	// id
			psmt.setInt(2, seq);			// ref
			psmt.setInt(3, seq);			// step
			psmt.setInt(4, seq);			// depth
			psmt.setString(5, bbs.getTitle());
			psmt.setString(6, bbs.getContent());
			psmt.setInt(7, seq);
			System.out.println("4/6 S answer");
			
			count = psmt.executeUpdate();
			conn.commit();
			System.out.println("5/6 S answer");
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			DBClose.close(psmt, conn, null);
			System.out.println("6/6 S answer");
		}		
		
		return count>0?true:false;
	}
	
	
	
	
	
	
}





