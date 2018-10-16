package model.member;

import dto.MemberDto;

public interface iMemberManager {

	//public boolean addMember(String id, String pwd, String name, String email, String phone, int blacklist, int auch);
	
	public boolean addMember(MemberDto dto);
	
	public boolean getId(String id);
	
	public MemberDto login(MemberDto dto);
		
}
