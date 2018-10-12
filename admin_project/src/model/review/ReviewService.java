package model.review;


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
}
