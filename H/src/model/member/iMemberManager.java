package model.member;

import java.lang.reflect.Member;

public interface iMemberManager {
	public boolean addMember(MemberService ms);
	public boolean getId(String ID);
	
	public MemberService login(MemberService ms);
	
	
}
