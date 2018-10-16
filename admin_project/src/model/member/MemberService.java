package model.member;

import java.util.List;

import dto.MemberDto;
import dto.ReserveDto;
import dto.ReserveTableDto;
import dto.ReviewDto;

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
	
	
	
	
	//admin 해당아이디의 호텔 이름 가져오기
	public String getHotelname(String id) {
		return manager.ad_getHotelname(id);
	}
	
	//admin 해당호텔 예약명단 가져오기
	public List<ReserveTableDto> adminGetHotelmember(String hotelname) {
		return manager.ad_GetHotelmember(hotelname);
	}
	
	//admin 해당 id 예약 리스트 가져오기
	public List<ReserveDto> ad_reserveList(String id){
		return manager.ad_reserveList(id);
	}
	
	//admin 해당 id 리뷰 리스트 가져오기
		public List<ReviewDto> ad_reviewList(String id){
			return manager.ad_reviewList(id);
	}
		
	//admin 회원 정보 수정
		public boolean ad_MemberUpdate(String id, String pw, String name, String email, String phone) {
			return manager.ad_MemberUpdate(id, pw, name, email, phone);
	}
		
	//admin 임시로 로그인
		public MemberDto ad_login(String id) {
			return manager.ad_login(id);
	}
}
