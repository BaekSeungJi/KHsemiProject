package model.member;

public class MemberManager implements iMemberManager {
	private static MemberManager memberManager = new MemberManager();
	
	public static MemberManager getInstance() {
		return memberManager;
	}
	
	
}
