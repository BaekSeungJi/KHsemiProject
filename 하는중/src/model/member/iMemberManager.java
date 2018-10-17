package model.member;

import java.lang.reflect.Member;
import java.util.List;

import dto.MemberDto;
import dto.ReserveDto;
import dto.ReviewDto;

public interface iMemberManager {
	public boolean addMember(MemberService ms);
	public boolean getId(String ID);
	
	public MemberDto login(String id);
	
	public MemberDto ad_login(String id);
	
	public String ad_getHotelname(String id);
	
	public List<ReserveDto> ad_reserveList(String id);
	
	public List<ReviewDto> ad_reviewList(String id);
	
	public boolean ad_MemberUpdate(String id, String pw, String name, String email, String phone);
}
