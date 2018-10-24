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
	
	public ReviewManager() {
		DBConnection.initConnect();
	}

	@Override
	public List<ReviewDto> getReviewList(String hotelname) {
		// 해당 호텔 후기 전부 가져오기
		
		System.out.println("getReviewList 다오에 넘어온 호텔이름 = " + hotelname);
		
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
				// json 데이터는 value값 중간에 개행문자(엔터값)이 절대 들어가선 안된다. 파싱을 못함.
				// 따라서 textArea에서 유저가 입력한 리뷰에 들어간 엔터값을 다른 값으로 치환해줘야 한다.
				// replaceAll("(\r\n|\r|\n|\n\r)", " ") 로 엔터값을 공백으로 처리했다.
				// 미친 이거 찾느라고 하루 죙일 걸렸네....ㅅㅂㅠㅠㅠㅠㅠㅠ아!!!!!!!!!!!!!!!!!!!!
				d.setContent(rs.getString(i++).replaceAll("(\r\n|\r|\n|\n\r)", " "));
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
	
	
	
	@Override
	public void addReview(ReviewDto inputDto) {
		// 리뷰페이지 - 리뷰쓴거 등록
		
		String sql = " INSERT INTO REVIEW "
				+ " VALUES( ?, ?, ?, ?, ?, ?, 0, 0, SYSDATE) ";
		// ? 순서대로 NUM, ID, HOTELNAME, TITLE, CONTENT, SCORE
		// 0, 0, SYSDATE는 순서대로 DEL, READCOUNT, REGDATE
				
		Connection conn = null;
		PreparedStatement psmt = null;
		
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 addReview Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 addReview Success");
			
			// ? 순서대로 NUM, ID, HOTELNAME, TITLE, CONTENT, SCORE
			psmt.setInt(1, inputDto.getNum());
			psmt.setString(2, inputDto.getId());
			psmt.setString(3, inputDto.getHotelname());
			psmt.setString(4, inputDto.getTitle());
			psmt.setString(5, inputDto.getContent());
			psmt.setInt(6, inputDto.getScore());
			System.out.println("3/6 addReview Success");
			
			psmt.executeQuery();
			System.out.println("4/6 addReview Success");
			
			
		} catch(Exception e){
			System.out.println("addReview Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, null);
		}
		
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
