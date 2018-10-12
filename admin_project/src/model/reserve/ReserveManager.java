package model.reserve;

import model.pds.PdsManager;

public class ReserveManager implements iReserveManager {
	private static ReserveManager reserveManager = new ReserveManager();
	
	public static ReserveManager getInstance() {
		return reserveManager;
	}
}
