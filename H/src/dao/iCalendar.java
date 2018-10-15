package dao;

import java.util.List;

import dto.CalendarDto;

public interface iCalendar {

	public List<CalendarDto> getCalendarList(String id, String yyyyMM);
	public boolean addCalendar(CalendarDto cal);
	
	public List<CalendarDto> getDayList(String id, String yyyymmdd);
	public CalendarDto getDay(int seq);
	
	public boolean deleteCalendar(int seq);
	public boolean updateCalendar(CalendarDto dto);
}








