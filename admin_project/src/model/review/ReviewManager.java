package model.review;

import java.sql.Connection;
import java.sql.PreparedStatement;

import db.DBClose;
import db.DBConnection;

public class ReviewManager implements iReviewManager {
	
	// 생성할 때 db연결
	public ReviewManager() {
		DBConnection.initConnect();
	}

	@Override
	public boolean ad_reviewdelete(int seq) {
		String sql=" UPDATE review SET  "
				+" DEL=1 "
				+ " WHERE num=? ";
		
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
