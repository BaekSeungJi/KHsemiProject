package model.review;

import java.util.List;

import dto.ReviewDto;

public interface iReviewManager {
	
	// 해당 호텔 후기 전부 가져오기
	public List<ReviewDto> getReviewList(String hotelname);

}
