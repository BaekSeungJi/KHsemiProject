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
			public boolean ad_reserveUpdate(int seq, String regdate, String request) {
				return manager.ad_reserveUpdate(seq, regdate, request);
			}
	//admin 예약 달력 
			public List<ReserveDto> getCalendarList(String id, String yyyyMM){
				return manager.getCalendarList(id, yyyyMM);
			}
	
}
