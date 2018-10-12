package model.member;

import java.util.List;

import dto.MemberDto;

public interface iMemberManager {
	
	public boolean addMember(String id, String pwd, String name, String email, String phone);

	public List<MemberDto> getMemberDtoList();

}
