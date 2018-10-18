package model.pds;

import java.util.List;

import dto.FileDto;
import dto.PdsDto;
import dto.PdsfileDto;



public interface iPdsManager {

	
	public List<PdsfileDto> getPdsList();
	
	public boolean writePds(PdsDto pds);
	
	public boolean writeFile(FileDto File);
	
	public void readCount(int seq);
	
	public void downCount(int seq);
	
	public boolean pdsDelete(int seq);


	public PdsfileDto getPds(int seq);
}
