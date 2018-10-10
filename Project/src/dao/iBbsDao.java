package dao;

import java.util.List;

import dto.BbsDto;
import dto.PagingBean;

public interface iBbsDao {

	public List<BbsDto> getBbsList();
	
	public List<BbsDto> getBbsPagingList(PagingBean paging, String searchWord, String choice);
	
	public boolean writeBbs(BbsDto bbs);	
	public BbsDto getBbs(int seq);	
	public void readcount(int seq);
	
	public boolean updateBbs(int seq, String title, String content);
	public boolean deleteBbs(int seq);
	
	public boolean answer(int seq, BbsDto bbs);
	
}





