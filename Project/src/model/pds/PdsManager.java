package model.pds;

public class PdsManager implements iPdsManager {

	private static PdsManager pdsmanager = new PdsManager();
	
	
	public static PdsManager getInstance() {
	
		return pdsmanager;
		
	}
	
	
}
