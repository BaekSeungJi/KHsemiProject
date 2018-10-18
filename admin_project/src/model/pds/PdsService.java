package model.pds;

import java.util.List;

import dto.FileDto;
import dto.PdsDto;
import dto.PdsfileDto;

public class PdsService {
	private static PdsService pdsService = null;
	public iPdsManager manager;
	
	private PdsService() {
		manager = new PdsManager();
	}
	
	
	public static PdsService getInstance() {
		if(pdsService == null) {
			pdsService = new PdsService();
		}		
		return pdsService;
	}
	
	
	
	public List<PdsfileDto> getPdsList(){
		return manager.getPdsList();
	}
	
	public boolean writePds(PdsDto pds){
		return manager.writePds(pds);
	}
	
	public boolean writeFile(FileDto File) {
		return manager.writeFile(File);
	}
	
	public void readCount(int seq) {
		manager.readCount(seq);
	}
	
	public void downCount(int seq) {
		 manager.downCount(seq);
	}
	
	public boolean pdsDelete(int seq) {
		return manager.pdsDelete(seq);
	}


	public PdsfileDto getPds(int seq) {
		return manager.getPds(seq);
	}
}