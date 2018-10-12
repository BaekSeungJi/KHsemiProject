package model.pds;

public class PdsService {
	
	private static PdsService pdsService = null;
	public iPdsManager manager;
	
	private PdsService() {
		manager = new PdsManager();
	}
	
	public static PdsService getInstance() {
		if(pdsService == null){
			pdsService = new PdsService();
		}
		
		return pdsService;
	}
	

}
