package model.review;

import java.util.List;

import dto.ReviewDto;

public class ReviewService {
	
	private static ReviewService reviewService = null;
	public iReviewManager manager;
	
	private ReviewService() {
		manager = new ReviewManager();
	}
	
	public static ReviewService getInstance() {
		if(reviewService == null) {
			reviewService = new ReviewService();
		}
		
		return reviewService;
	}
	
	// 해당 호텔 후기 전부 가져오기
	public List<ReviewDto> getReviewList(String hotelname){
		return manager.getReviewList(hotelname);
	}
	
	// 내가 쓴 리뷰 추가하기
	public void addReview(ReviewDto inputDto) {
		manager.addReview(inputDto);
	}
	
	public  boolean reviewdelete(int seq){
		
		return manager.ad_reviewdelete(seq);
		}

	//admin 호텔 리뷰 가져오기 
	public List<ReviewDto> ad_getReview(String hotelname) {
		return manager.ad_getReview(hotelname);
	}

}
