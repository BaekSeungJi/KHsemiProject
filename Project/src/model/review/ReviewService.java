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

}
