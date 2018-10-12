package model.hotel;

import java.util.List;

import dto.HotelDto;

public class HotelService {
	
	private static HotelService hotelService = null;
	public iHotelManager manager;
	
	private HotelService() {
		manager = new HotelManager();
	}
	
	public static HotelService getInstance() {
		if(hotelService == null) {
			hotelService = new HotelService();
		}
		
		return hotelService;
	}
	
	public List<HotelDto> getSearchHotelList(String place, String price, String people, String date1, String date2){
		
		return manager.getSearchHotelList(place, price, people, date1, date2);
	}

}
