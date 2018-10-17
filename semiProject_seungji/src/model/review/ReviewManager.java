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

	@Override
	public List<ReviewDto> getReviewList(String hotelname) {
		// 해당 호텔 후기 전부 가져오기
		
		String sql = " SELECT NUM, ID, TITLE, CONTENT, SCORE, DEL, REGDATE "
				+ " FROM REVIEW "
				+ " WHERE HOTELNAME = ? "
				+ " ORDER BY REGDATE DESC ";
		

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<ReviewDto> list = new ArrayList<ReviewDto>();
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 getReviewList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getReviewList Success");
			
			psmt.setString(1, hotelname);
			System.out.println("3/6 getReviewList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getReviewList Success");
			
			while(rs.next()) {
				int i = 1;
				
				ReviewDto d = new ReviewDto();
				d.setNum(rs.getInt(i++));
				d.setId(rs.getString(i++));
				d.setTitle(rs.getString(i++));
				d.setContent(rs.getString(i++));
				d.setScore(rs.getInt(i++));
				d.setDel(rs.getInt(i++));
				d.setRegdate(rs.getString(i++));
				
				list.add(d);
				
			}
			System.out.println("5/6 getReviewList Success");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("getReviewList Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return list;
	}


	
	
	
	
	
	
}
