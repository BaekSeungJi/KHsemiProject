package model.member;

import java.util.List;

import dto.MemberDto;
import dto.ReserveDto;
import dto.ReserveTableDto;
import dto.ReviewDto;

public interface iMemberManager {

	
	public boolean addMember(MemberDto dto);
	
	public boolean getId(String id);
	
	public MemberDto login(MemberDto dto);
		
	public MemberDto suchid(MemberDto dto);
	
	public MemberDto suchpwd(MemberDto dto);
	//
	public String ad_getHotelname(String id);
	
	public List<ReserveTableDto> ad_GetHotelmember(String hotelname,String sWord,String selected);
	
	public List<ReserveDto> ad_reserveList(String id);
	
	public List<ReviewDto> ad_reviewList(String id);
	
	public boolean ad_MemberUpdate(String id, String pw, String name, String email, String phone);

	
}
