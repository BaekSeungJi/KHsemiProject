package model.hotel;

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
	
	
	
}
