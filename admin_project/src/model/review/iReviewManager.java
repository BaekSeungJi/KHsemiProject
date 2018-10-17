package model.review;

import java.util.List;

import dto.ReviewDto;

public interface iReviewManager {

	public boolean ad_reviewdelete(int seq);
	
	public List<ReviewDto> ad_getReview(String hotelname);
}
