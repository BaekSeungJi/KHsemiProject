package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import dto.HotelDto;
import dto.MemberDto;
import dto.MonthlysalesDto;
import dto.ReserveDto;
import dto.ReserveTableDto;
import dto.ReviewDto;
import dto.scoreDto;
import dto.util;

import javax.servlet.RequestDispatcher;

import model.hotel.HotelService;
import model.member.MemberService;
import model.reserve.ReserveService;
import model.review.ReviewService;

public class MemberControl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}

	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		
	
		String command =req.getParameter("command");
		
		

		// 관리자 페이지 메인
		if(command.equals("ad_admin")) {
			
			//로그인정보가져오기
			MemberDto dto = (MemberDto)req.getSession().getAttribute("login");
			String id = dto.getId();
			
	
			// 메인관리자면 바로 게시판으로 ㄱ
			String link = null;
			if(dto.getAuth()==1) {
				link = "PdsControl?command=ad_noticeGo";
			}else {
				link = "adminMain.jsp";
			}
			
			//서비스 생성
			MemberService service = MemberService.getInstance();
			ReviewService Rservice = ReviewService.getInstance();
			ReserveService ser = ReserveService.getInstance();
			HotelService Hser = HotelService.getInstance();
			
			// 호텔이름가져와서 세션저장
			String hotelname= service.getHotelname(id);
			req.getSession().setAttribute("hotelname", hotelname);
			HotelDto hoteldto = Hser.getHoteldetail(hotelname);
			
			// 차트 정보
			int price = Integer.parseInt(Hser.getPrice(hotelname));
			List<ReviewDto> Rlist = Rservice.ad_getReview(hotelname);
			List<ReserveDto> Rdto = ser.getReserve(hotelname);
			scoreDto sdto = util.getScore(Rlist);
			MonthlysalesDto Mdto =  util.getsales(Rdto, price);
			
		
			req.setAttribute("hoteldto", hoteldto);
			req.setAttribute("Mdto", Mdto);
			req.setAttribute("sdto", sdto);
			
			
			dispatch(link , req, resp);
		
		}
		
		// 해당 호텔 예약한 회원들 보기 
		else if(command.equals("memberGo")) {
			
			String findWord = req.getParameter("txt");
			String choice = req.getParameter("sel");

			System.out.println("findWord = " + findWord);

			System.out.println("choice = " + choice);

			if(choice == null || choice.equals("")){
				choice = "";	
			}

			if(findWord == null){
				findWord = "";
			}
			
			String id = req.getParameter("id");
			System.out.print(id);
			MemberService service = MemberService.getInstance();
			
			String hotelname= service.getHotelname(id);
			System.out.print(hotelname);
			
			List<ReserveTableDto> list = service.ad_GetHotelmember(hotelname, findWord, choice);
			
			req.setAttribute("list", list);
			
			dispatch("ad_member.jsp", req, resp);
			
		
		}
		
		// 해당 회원 예약,리뷰 리스트 보기 
		else if(command.equals("ad_member_detail")) {
			
		
			String id = req.getParameter("id");
			
			System.out.println("mem컨트롤 id 테스트"+id);
			MemberService service = MemberService.getInstance();
			
			List<ReserveDto> reserveList = service.ad_reserveList(id); 
			
			List<ReviewDto> reviewList = service.ad_reviewList(id);
			
			req.setAttribute("reviewList", reviewList);
			
			req.setAttribute("reserveList", reserveList);
			
			dispatch("ad_member_detail.jsp", req, resp);
		}
		
		
		else if(command.equals("ad_memberUpdate")) {
			
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			
			MemberService service = MemberService.getInstance();
			
			boolean b = service.ad_MemberUpdate(id, pw, name, email, phone);
			PrintWriter out = resp.getWriter();
			if(b == true) {
			
		
				out.println("alert('수정 완료되었습니다');");
				
				resp.sendRedirect("MemberControl?command=ad_admin&id="+id);
			}else {
				out.println("alert('수정 실패하였습니다');");
				resp.sendRedirect("MemberControl?command=ad_admin&id="+id);
			}
			
		}
		else if(command.equals("ad_memberUpdate")) {
			int seq = Integer.parseInt(req.getParameter("req"));
			String request = req.getParameter("request");
			String realdate = req.getParameter("realdate");
			String id = req.getParameter("id");
			
			
		}
	}
	
	
	public void dispatch(String urls,HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
	RequestDispatcher dispatch = req.getRequestDispatcher(urls);
	dispatch.forward(req, resp);
		}
	}

	