 
package model.reserve;

import java.util.List;

import dto.ReserveDto;

public class ReserveService {
	
	private static ReserveManager reserveManager = null;
	public iReserveManager manager;
	
	private ReserveService() {
		manager = new ReserveManager();
	}

	public static ReserveManager getInstance() {
		if(reserveManager == null) {
			reserveManager = new ReserveManager();
		}
		return reserveManager;
	}
	
	
	

	
}
