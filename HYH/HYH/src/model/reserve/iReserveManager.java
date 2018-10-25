package model.reserve;

import java.util.List;

import dto.ReserveDto;

public interface iReserveManager {
	
	public List<ReserveDto> getReservelist(String id); 

	public boolean ad_reserveUpdate(int seq, String regdate, String request);

	public boolean ad_reservedelete(int seq);
	
	public List<ReserveDto> getCalendarList(String hotelname, String yyyyMM);
	
	public List<ReserveDto> getReserve(String hotelname);
}
