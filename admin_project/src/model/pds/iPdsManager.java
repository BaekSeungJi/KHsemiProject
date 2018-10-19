package model.pds;

import java.util.List;

import dto.FileDto;
import dto.PdsDto;




public interface iPdsManager {

	
	public List<PdsDto> getPdsList();
	
	public boolean writePds(PdsDto pds);

	public void readCount(int seq);
	
	public void downCount(int seq);
	
	public boolean pdsDelete(int seq);
	
	public boolean ad_PdsUpdate(int seq, PdsDto dto);

	public PdsDto getPds(int seq);
	
	public boolean answer(int seq, PdsDto pds);
}
