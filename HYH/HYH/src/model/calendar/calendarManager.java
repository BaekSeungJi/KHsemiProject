package model.calendar;

public class calendarManager implements icalendarManager {
	private static calendarManager hotelmanager = new calendarManager();

	public static calendarManager getInstance() {
		return hotelmanager;
	}


}
