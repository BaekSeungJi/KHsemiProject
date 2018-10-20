package model.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.ReviewDto;

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

	
	@Override
	public List<ReviewDto> ad_getReview(String hotelname) {
		String sql = " SELECT score from REVIEW where hotelname = ? and del = 0 ";
		List<ReviewDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/6 ad_reviewList Success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 ad_reviewList Success");
	
			
			psmt.setString(1, hotelname);
			
			System.out.println("3/6 ad_reviewList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 ad_reviewList Success");
			
			
			while(rs.next()){			
				System.out.println("5/6 ad_reviewList Success");
				
				int score = rs.getInt(1);
				
				ReviewDto dto = new ReviewDto(0, "", "", "", "", score, 0, 0, "");
				list.add(dto);
			
			}
		} catch (Exception e1) {
			System.out.println("ad_reviewList Fail");
			e1.printStackTrace();
			
		}	finally{			
			DBClose.close(psmt, conn, rs);			
		}
		
		return list;
	}

	
	
	
}
