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
	
	
	//예약하기
	public boolean reserve(String id, String hotelname, String request, String checkin, String checkout){
		return manager.reserve(id, hotelname, request, checkin, checkout);
	}
	
	//해당 id 예약 리스트 가져오기
	public List<ReserveDto> getreserveList(String id){
		return manager.getreserveList(id);
	}
	
	//admin 예약 삭제
			public boolean ad_reservedelete(int seq){
				return manager.ad_reservedelete(seq);
			}
	//admin 예약 수정 
			public boolean ad_reserveUpdate(int seq, String regdate, String request) {
				return manager.ad_reserveUpdate(seq, regdate, request);
			}
	//admin 예약 달력 
			public List<ReserveDto> getCalendarList(String hotelname, String yyyyMM){
				return manager.getCalendarList(hotelname, yyyyMM);
			}

	//admin 해당호텔의 예약 전체 가져오기
			public List<ReserveDto> getReserve(String hotelname) {
				return manager.getReserve(hotelname);
			}
			
			
			
}
