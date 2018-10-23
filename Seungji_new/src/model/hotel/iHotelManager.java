package model.hotel;

import java.security.PublicKey;
import java.util.List;

import dto.HotelDto;

public interface iHotelManager {
	
	public List<HotelDto> getSearchHotelList(String place, String price, String people, String date1, String date2);
	
	public void readCountPlus(int seq);
	
	public HotelDto getHotelDetail(int seq);
	
	public List<HotelDto> getHotelList1(String place, String price, String people, String date1, String date2);
	
	public List<HotelDto> getHotelList2(String place, String price, String people);

}
