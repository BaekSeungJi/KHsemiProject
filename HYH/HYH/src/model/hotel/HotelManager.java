package model.hotel;

public class HotelManager implements iHotelManager {
	private static HotelManager hotelmanager = new HotelManager();

	public static HotelManager getInstance() {
		return hotelmanager;
	}


}
