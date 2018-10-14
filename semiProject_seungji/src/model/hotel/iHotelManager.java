package model.hotel;

import java.security.PublicKey;
import java.util.List;

import dto.HotelDto;

public interface iHotelManager {
	
	public List<HotelDto> getSearchHotelList(String place, String price, String people, String date1, String date2);
	

}
