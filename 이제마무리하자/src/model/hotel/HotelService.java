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

	//admin 호텔 정보 수정
	public boolean ad_HotelUpdate(String hotelname, String DESCRIPTION, int MAXPEOPLE, int PRICE,
			String HOTELPHONE) {
		return manager.ad_HotelUpdate(hotelname, DESCRIPTION, MAXPEOPLE, PRICE, HOTELPHONE);
	}
	
	//admin 호텔 디테일 
	public HotelDto getHoteldetail(String hotelname) {
		
		return manager.getHoteldetail(hotelname);
	}
	
	//admin 월별매출
	public List<String> getMonthlyChart(String hotelname){
		return manager.getMonthlyChart(hotelname);
	}
	
	// 호텔 가격 가져오기 
	public String getPrice(String hotelname) {
		return manager.getPrice(hotelname);
	}
	
	
}
