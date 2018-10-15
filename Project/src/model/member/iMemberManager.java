package model.member;

import java.util.List;

import dto.MemberDto;

public interface iMemberManager {

	//public boolean addMember(String id, String pwd, String name, String email, String phone, int blacklist, int auch);
	public boolean addMember(MemberDto dto);
	
	public boolean getId(String id);
	
	public List<MemberManager> getMemberList();
	
	public MemberDto login(MemberDto dto);
		
}
