package model.member;

import java.util.List;

import dto.MemberDto;

public class MemberManager implements iMemberManager {
	private static MemberManager memberManager = new MemberManager();
	
	public static MemberManager getInstance() {
		return memberManager;
	}

	@Override
	public boolean addMember(String id, String pwd, String name, String email, String phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MemberDto> getMemberDtoList() {
		// TODO Auto-generated method stub
		return null;
	}

}