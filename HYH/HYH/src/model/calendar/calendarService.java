package model.calendar;

public class calendarService {

	private static calendarService hotelService = null;
	public icalendarManager manager;
	
	private calendarService() {
		manager = new calendarManager();
	}
	
	
	public static calendarService getInstance() {
		if(hotelService == null) {
			hotelService = new calendarService();
		}		
		return hotelService;
	}
	
	
	
}
