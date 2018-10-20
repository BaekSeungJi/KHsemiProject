package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import dto.ReserveDto;
import model.member.MemberService;
import model.reserve.ReserveService;

public class ReserveControl extends HttpServlet {

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
		
		
		// 관리자_리뷰삭제
		if(command.equals("ad_reserveDelete")) {

			PrintWriter out = resp.getWriter();
			
			int seq = Integer.parseInt(req.getParameter("seq"));
			
			String id = req.getParameter("id");
			
			ReserveService service = ReserveService.getInstance();
			
			boolean b = service.ad_reservedelete(seq);
			
			if(b == true) {
			
				out.println("alert('삭제 완료되었습니다');");
				resp.sendRedirect("MemberControl?command=ad_member_detail&id="+id);
			}else {
		
				out.println("alert('삭제 실패하였습니다');");
				resp.sendRedirect("MemberControl?command=ad_member_detail&id="+id);
			}
		}
		
		else if(command.equals("ad_reserveUpdate")) {

			PrintWriter out = resp.getWriter();
			
			int seq = Integer.parseInt(req.getParameter("seq"));	
			String id = req.getParameter("id");
			String checkin = req.getParameter("checkin");
			String checkout = req.getParameter("checkout");
			String request = req.getParameter("request");
		
			System.out.println("리저브테스트id"+id);
			System.out.println("리저브테스트realdate:"+checkin);
			System.out.println("리저브테스트request:"+request);
			
			
			ReserveService service = ReserveService.getInstance();
			
			boolean b = service.ad_reserveUpdate(seq, checkin,checkout, request);
			
			if(b == true) {
				out.println("<script>alert('수정 완료되었습니다');</script>");
				resp.sendRedirect("MemberControl?command=ad_member_detail&id="+id);
			}else {
				out.println("<script>alert('수정 실패하였습니다');</script>");
				resp.sendRedirect("MemberControl?command=ad_member_detail&id="+id);
			}
		}
		
		
		else if(command.equals("ad_hotel")) {
			String id = req.getParameter("id");
			
			String yyyyMM = req.getParameter("yyyyMM");
			
			ReserveService service = ReserveService.getInstance();
			
			List<ReserveDto> dto = service.getCalendarList(id, yyyyMM);
			
			req.setAttribute("dto", dto);
			
			dispatch("ad_hotel.jsp", req, resp);

		}
		
	}
	
	
	public void dispatch(String urls,HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
			}
}
