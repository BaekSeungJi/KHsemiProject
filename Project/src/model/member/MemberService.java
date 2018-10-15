package model.member;

import java.util.List;

import dto.MemberDto;

public class MemberService {
	private static MemberService memberservice = null;
	public iMemberManager manager;
		
	private MemberService() {
		manager = new MemberManager();
	}
	
	
	public static MemberService getInstance() {
		if(memberservice == null) {
			memberservice = new MemberService();
		}		
		return memberservice;
	}

public List<MemberDto> getMemberList(){
	return manager.getMemberDtoList();
}
	

public boolean addMember(String id, String pwd, String name, String email, String phone) {
	return manager.addMember(id, pwd, name, email, phone);
	
}
	
public boolean isDupId(String id) {
	return manager.isDupId(id);
}
	
}
