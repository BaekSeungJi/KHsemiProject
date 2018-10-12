package model.member;

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

}
