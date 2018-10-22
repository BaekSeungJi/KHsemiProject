package model.review;

import java.util.List;

import dto.ReviewDto;

public interface iReviewManager {
	
	// 해당 호텔 후기 전부 가져오기
	public List<ReviewDto> getReviewList(String hotelname);
	
	public void addReview(ReviewDto inputDto);

	public boolean ad_reviewdelete(int seq);
	
	public List<ReviewDto> ad_getReview(String hotelname);
	
}
