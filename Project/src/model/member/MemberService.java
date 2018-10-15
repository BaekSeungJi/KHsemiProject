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

	
/*public boolean addMember(String id, String pwd, String name, String email, String phone, int blacklist, int auth) {
	return manager.addMember(id, pwd, name, email, phone, blacklist, auth);
	
}*/

	
public boolean getId(String id) {

	return manager.getId(id);
}
	
}
