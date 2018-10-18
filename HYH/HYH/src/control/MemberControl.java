package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import dto.MemberDto;
import dto.ReserveDto;
import dto.ReviewDto;

import javax.servlet.RequestDispatcher;
import model.member.MemberService;
import model.reserve.ReserveService;

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
				
		String command = req.getParameter("command");
		
		System.out.println("command:" + command);
		
		if(command.equals("mypage")) {
			String id = req.getParameter("id");
			MemberService service = MemberService.getInstance();
			
			MemberDto dto = service.login(id);

		
			
			
			List<ReserveDto> reserveList = service.reserveList(id); 
		
			req.setAttribute("dto", dto);
			req.setAttribute("reserveList", reserveList);
			dispatch("mypage.jsp", req, resp);
			
			
		}
		
		else if(command.equals("profileedit")) {
			String id = req.getParameter("id");
			MemberService service = MemberService.getInstance();
			
			MemberDto dto = service.login(id);

		
			req.setAttribute("dto", dto);
		
			dispatch("profileedit.jsp", req, resp);
		}
	
		
		
		
		else if(command.equals("profileeditaf")) {
			String id = req.getParameter("id");
			MemberService service = MemberService.getInstance();
			
			MemberDto dto = service.login(id);
			
			
			req.setAttribute("dto", dto);
		
			dispatch("profileeditaf.jsp", req, resp);
		}
		
		

		// 관리자 페이지 메인
		else if(command.equals("ad_admin")) {
			
			String id = req.getParameter("id");
	
			MemberService service = MemberService.getInstance();
			
			String hotelname= service.getHotelname(id);
			
			MemberDto dto = service.ad_login(id);
			
			
			req.setAttribute("dto", dto);
			req.setAttribute("hotelname", hotelname);
		
			dispatch("index.jsp", req, resp);
		}
		
		
		// 해당 회원 예약,리뷰 리스트 보기 
		else if(command.equals("ad_member_detail")) {
			
			String id = req.getParameter("member_id");
			
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
			
			if(b == true) {
				JOptionPane.showMessageDialog(null, "수정 완료되었습니다");
				resp.sendRedirect("MemberControl?command=ad_admin&id="+id);
			}else {
				JOptionPane.showMessageDialog(null,"수정 실패하였습니다");
				resp.sendRedirect("MemberControl?command=ad_admin&id="+id);
			}
			
		}
	}
	
	
	public void dispatch(String urls,HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
	RequestDispatcher dispatch = req.getRequestDispatcher(urls);
	dispatch.forward(req, resp);
		}
	}

	