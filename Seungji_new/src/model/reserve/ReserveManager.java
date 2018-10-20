package model.reserve;

import db.DBConnection;

public class ReserveManager implements iReserveManager {
	
	public ReserveManager() {
		DBConnection.initConnect();
	}

}
