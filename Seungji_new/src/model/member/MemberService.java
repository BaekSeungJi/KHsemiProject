package model.member;

import java.util.List;

import dto.MemberDto;

public class MemberService {
	
	private static MemberService memberservice = null;
	public iMemberManager manager;
	
	private MemberService() {
		manager = new MemberManager();
		
	}
	
	
	public static MemberService getInstance() throws Exception {
		if(memberservice == null) {
			memberservice = new MemberService();
		}		
		return memberservice;
	}

	

	
}
