package model.pds;

import java.util.List;

import dto.FileDto;
import dto.PdsDto;


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
	
	
	
	
	public List<PdsDto> getPdsList(){
		return manager.getPdsList();
	}
	
	public boolean writePds(PdsDto pds){
		return manager.writePds(pds);
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


	public PdsDto  getPds(int seq) {
		return manager.getPds(seq);
	}
	
	public boolean ad_PdsUpdate(int seq, PdsDto dto) {
		return manager.ad_PdsUpdate(seq, dto);
	}
	
	public boolean answer(int seq, PdsDto pds) {
		return manager.answer(seq, pds);
	
	}
}