package model.member;

public class MemberManager implements iMemberManager {
	private static MemberManager memberManager = new MemberManager();
	
	public static MemberManager getInstance() {
		return memberManager;
	}

	@Override
	public boolean addMember(MemberService ms) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getId(String ID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MemberService login(MemberService ms) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
