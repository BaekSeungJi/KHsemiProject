package model.reserve;

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
