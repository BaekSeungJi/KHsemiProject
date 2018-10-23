package model.reserve;

import java.util.List;


import dto.ReserveDto;

public interface iReserveManager {

	public boolean ad_reserveUpdate(int seq, String checkin,String checkout, String request);

	public boolean ad_reservedelete(int seq);
	
	public List<ReserveDto> getCalendarList(String hotelname, String yyyyMM);
	
	public List<ReserveDto> getReserve(String hotelname);
	
	public List<ReserveDto> getlist(String hotelname, String yyyymmdd);

	public List<ReserveDto> getreserveList(String id);

	public boolean reserveUpdate(int seq, String checkin,String checkout, String request);

	public boolean reservedelete(int seq);

	public ReserveDto getDay(int seq);

	public boolean reserve(String id, String hotelname, String request, String checkin, String checkout);

	
	
	/////
	
	
		public boolean profileedit(String id, String pw, String name, String email, String phone);
		public List<ReserveDto> getReservelist(String id); 

		public boolean ad_reserveUpdate(int seq, String regdate, String request);



		

	
	
}
