package model.reserve;

import java.util.List;

import dto.ReserveDto;

public class ReserveService {
	
	private static ReserveService reserveService = null;
	public iReserveManager manager;
	
	private ReserveService() {
		manager = new ReserveManager();
	}

	public static ReserveService getInstance() {
		if(reserveService == null) {
			reserveService = new ReserveService();
		}
		return reserveService;
	}
	
	//admin 예약 삭제
			public boolean ad_reservedelete(int seq){
				return manager.ad_reservedelete(seq);
			}
	//admin 예약 수정 
			public boolean ad_reserveUpdate(int seq, String checkin,String checkout, String request) {
				return manager.ad_reserveUpdate(seq,  checkin,checkout, request);
			}
	//admin 예약 달력 
			public List<ReserveDto> getCalendarList(String hotelname, String yyyyMM){
				return manager.getCalendarList(hotelname, yyyyMM);
			}

	//admin 해당호텔의 예약 전체 가져오기
			public List<ReserveDto> getReserve(String hotelname) {
				return manager.getReserve(hotelname);
			}
	//admin callist 가져오기
			public List<ReserveDto> getlist(String hotelname, String yyyymmdd) {
				return manager.getlist(hotelname, yyyymmdd);
			}

			public List<ReserveDto> getreserveList(String id){
				return manager.getreserveList(id);
			}
			
			//getday
			public ReserveDto getDay(int seq) {
				return manager.getDay(seq);
			}
			
			//예약 수정 
			public boolean reserveUpdate(int seq, String checkin,String checkout, String request) {
				return manager.reserveUpdate(seq, checkin, checkout, request);
			}
			
			public boolean reserve(String id, String hotelname, String request, String checkin, String checkout){
				return manager.reserve(id, hotelname, request, checkin, checkout);
			}
			
}
