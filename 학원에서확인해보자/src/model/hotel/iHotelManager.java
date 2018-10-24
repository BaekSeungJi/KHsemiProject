package model.hotel;

import java.security.PublicKey;
import java.util.List;

import dto.HotelDto;

public interface iHotelManager {
	
	public List<HotelDto> getSearchHotelList(String place, String price, String people, String date1, String date2);
	
	public void readCountPlus(int seq);
	
	public HotelDto getHotelDetail(int seq);
	
	public HotelDto getHoteldetail(String hotelname);
	
	public List<String> getMonthlyChart(String hotelname);
	
	public String getPrice(String hotelname);
	
		
	public List<HotelDto> getHotelList1(String place, String price, String people, String date1, String date2);
	
	public List<HotelDto> getHotelList2(String place, String price, String people);

	public boolean ad_HotelUpdate(String hotelname, String DESCRIPTION, int MAXPEOPLE, int PRICE,String HOTELPHONE);
	
}
