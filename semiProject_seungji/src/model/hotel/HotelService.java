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
	
	public void readCountPlus(int seq) {
		// 호텔디테일 들어왔을때 단순히 조회수 +1해주는 함수
	}
	
	public HotelDto getHotelDetail(int seq) {
		return manager.getHotelDetail(seq);
	}

}
