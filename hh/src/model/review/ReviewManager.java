package model.review;

public class ReviewManager implements iReviewManager {
	private static ReviewManager reviewManager = new ReviewManager();
	
	public static ReviewManager getInstance() {
		return reviewManager;
	}
	
	
}
