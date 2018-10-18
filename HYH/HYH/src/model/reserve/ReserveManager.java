package model.reserve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.ReserveDto;
import model.pds.PdsManager;

public class ReserveManager implements iReserveManager {
	private static ReserveManager reserveManager = new ReserveManager();
	
	public static ReserveManager getInstance() {
		return reserveManager;
	}

	
}
