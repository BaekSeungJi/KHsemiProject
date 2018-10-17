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


	//admin 리뷰 삭제
	public  boolean reviewdelete(int seq){
		
		return manager.ad_reviewdelete(seq);
		}

	//admin 호텔 리뷰 가져오기 
	public List<ReviewDto> ad_getReview(String hotelname) {
		return manager.ad_getReview(hotelname);
	}
}
