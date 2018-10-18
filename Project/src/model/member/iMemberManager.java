package model.member;

import java.util.List;

import dto.MemberDto;

public interface iMemberManager {

	
	public boolean addMember(MemberDto dto);
	
	public boolean getId(String id);
	
	public MemberDto login(MemberDto dto);
		
		
	
}
