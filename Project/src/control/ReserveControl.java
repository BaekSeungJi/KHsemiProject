package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import dto.MemberDto;
import dto.ReserveDto;
import dto.util;
import model.hotel.HotelService;
import model.member.MemberService;
import model.reserve.ReserveService;

public class ReserveControl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doProcess(req,resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doProcess(req,resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");

		String command =req.getParameter("command");



if(command.equals("reserve")) {
			
			System.out.println(req.getParameter("hotelname"));
			
			int seq = Integer.parseInt(req.getParameter("seq")); 
			System.out.println("seq = " + seq);
			
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, 1);
			
			
			String hotelname = req.getParameter("hotelname");
			String syear = req.getParameter("year");
			String smonth = req.getParameter("month");

			int year = cal.get(Calendar.YEAR);
			if(util.nvl(syear) == false){	// 파라미터로 넘어온 데이터가 있을 시
				year = Integer.parseInt(syear);
			}

			int month = cal.get(Calendar.MONTH) + 1;
			if(util.nvl(smonth) == false){
				month = Integer.parseInt(smonth);
			}

			if(month < 1){
				month = 12;
				year--;
			}

			if(month > 12){
				month = 1;
				year++;
			}

			cal.set(year, month - 1, 1);	// 연월일 셋팅완료

			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		

			ReserveService Rservice = ReserveService.getInstance();

			List<ReserveDto> list = Rservice.getCalendarList(hotelname, year + util.two(month + ""));

		
			
			req.setAttribute("hotelname", hotelname);
			req.setAttribute("cal", cal);
			req.setAttribute("dayOfWeek", dayOfWeek);
			req.setAttribute("year", year);
			req.setAttribute("month", month);


			req.setAttribute("list", list);

			String id = req.getParameter("id");
		
			dispatch("reserve.jsp", req, resp);

		}

		else if(command.equals("reservedel")) {
			PrintWriter out = resp.getWriter();

			String id = req.getParameter("id");
			
			
			System.out.print("delete들옴");
			int seq = Integer.parseInt(req.getParameter("seq")); 
			String hotelname = req.getParameter("hotelname");
			ReserveService service = ReserveService.getInstance();

			boolean b = service.reservedelete(seq);

		
			
			if(b == true) {

				System.out.println("삭제완료");
				out.println("alert('삭제 완료되었습니다');");
				resp.sendRedirect("index.jsp");
			}else {

				out.println("alert('삭제 실패하였습니다');");
				
				System.out.println("삭제실패");
				resp.sendRedirect("index.jsp");
				
			}
		}

		else if(command.equals("reservedetail")) {

			System.out.println(req.getParameter("hotelname"));
			
			String id = req.getParameter("id");
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, 1);
			
			
			String hotelname = req.getParameter("hotelname");
			String syear = req.getParameter("year");
			String smonth = req.getParameter("month");

			int year = cal.get(Calendar.YEAR);
			if(util.nvl(syear) == false){	// 파라미터로 넘어온 데이터가 있을 시
				year = Integer.parseInt(syear);
			}

			int month = cal.get(Calendar.MONTH) + 1;
			if(util.nvl(smonth) == false){
				month = Integer.parseInt(smonth);
			}

			if(month < 1){
				month = 12;
				year--;
			}

			if(month > 12){
				month = 1;
				year++;
			}

			cal.set(year, month - 1, 1);	// 연월일 셋팅완료

			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		

			ReserveService Rservice = ReserveService.getInstance();

			List<ReserveDto> list = Rservice.getCalendarList(hotelname, year + util.two(month + ""));

		
			
			req.setAttribute("hotelname", hotelname);
			req.setAttribute("cal", cal);
			req.setAttribute("dayOfWeek", dayOfWeek);
			req.setAttribute("year", year);
			req.setAttribute("month", month);


			req.setAttribute("list", list);

		
			dispatch("reservedetail.jsp", req, resp);
		}

		else if(command.equals("reservelist")) {
			String id = req.getParameter("id");

			MemberService memservice = MemberService.getInstance();
			ReserveService resservice = ReserveService.getInstance();
			HotelService hotelServicervice = HotelService.getInstance();


			MemberDto dto = new MemberDto(id, null, null, null, null, 0, 3);

			List<ReserveDto> list = resservice.getreserveList(id);

			for(ReserveDto reservedto : list){
				System.out.println(reservedto.toString());					
			}	

			req.setAttribute("list", list);

			dispatch("reservelist.jsp", req, resp);
		}

		else if(command.equals("reserveupdate")) {
			
			System.out.println("reserveupdate 진입");
			System.out.println(req.getParameter("hotelname"));
			
			
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, 1);
			
			
			String hotelname = req.getParameter("hotelname");
			String syear = req.getParameter("year");
			String smonth = req.getParameter("month");

			int year = cal.get(Calendar.YEAR);
			if(util.nvl(syear) == false){	// 파라미터로 넘어온 데이터가 있을 시
				year = Integer.parseInt(syear);
			}

			int month = cal.get(Calendar.MONTH) + 1;
			if(util.nvl(smonth) == false){
				month = Integer.parseInt(smonth);
			}

			if(month < 1){
				month = 12;
				year--;
			}

			if(month > 12){
				month = 1;
				year++;
			}

			cal.set(year, month - 1, 1);	// 연월일 셋팅완료

			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		

			ReserveService Rservice = ReserveService.getInstance();

			List<ReserveDto> list = Rservice.getCalendarList(hotelname, year + util.two(month + ""));

		
			
			req.setAttribute("hotelname", hotelname);
			req.setAttribute("cal", cal);
			req.setAttribute("dayOfWeek", dayOfWeek);
			req.setAttribute("year", year);
			req.setAttribute("month", month);


			req.setAttribute("list", list);

			String id = req.getParameter("id");
		
			
			dispatch("reserveupdate.jsp", req, resp);
		}

		else if(command.equals("reserveupdateaf")) {
			System.out.println("reserveupdateaf 진입");
			System.out.println(req.getParameter("hotelname"));
						
						
						
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.DATE, 1);
						
						
						String hotelname = req.getParameter("hotelname");
						String syear = req.getParameter("year");
						String smonth = req.getParameter("month");

						int year = cal.get(Calendar.YEAR);
						if(util.nvl(syear) == false){	// 파라미터로 넘어온 데이터가 있을 시
							year = Integer.parseInt(syear);
						}

						int month = cal.get(Calendar.MONTH) + 1;
						if(util.nvl(smonth) == false){
							month = Integer.parseInt(smonth);
						}

						if(month < 1){
							month = 12;
							year--;
						}

						if(month > 12){
							month = 1;
							year++;
						}

						cal.set(year, month - 1, 1);	// 연월일 셋팅완료

						int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

					

						ReserveService Rservice = ReserveService.getInstance();

						List<ReserveDto> list = Rservice.getCalendarList(hotelname, year + util.two(month + ""));

					
						
						req.setAttribute("hotelname", hotelname);
						req.setAttribute("cal", cal);
						req.setAttribute("dayOfWeek", dayOfWeek);
						req.setAttribute("year", year);
						req.setAttribute("month", month);


						req.setAttribute("list", list);

						String id = req.getParameter("id");
					

			dispatch("reserveupdateaf.jsp", req, resp);
		}

		else if(command.equals("reservewrite")) {
			String id = req.getParameter("id");

			MemberService memservice = MemberService.getInstance();
			ReserveService resservice = ReserveService.getInstance();
			HotelService hotelServicervice = HotelService.getInstance();


			MemberDto dto = new MemberDto(id, null, null, null, null, 0, 3);

			List<ReserveDto> list = resservice.getreserveList(id);

			for(ReserveDto reservedto : list){
				System.out.println(reservedto.toString());					
			}	

			req.setAttribute("list", list);

			dispatch("reservewrite.jsp", req, resp);
		}

		else if(command.equals("reservewriteAf")) {
			String id = req.getParameter("id");

			MemberService memservice = MemberService.getInstance();
			ReserveService resservice = ReserveService.getInstance();
			HotelService hotelServicervice = HotelService.getInstance();


			MemberDto dto = new MemberDto(id, null, null, null, null, 0, 3);

			List<ReserveDto> list = resservice.getreserveList(id);

			for(ReserveDto reservedto : list){
				System.out.println(reservedto.toString());					
			}	

			req.setAttribute("list", list);

			dispatch("reservewriteAf.jsp", req, resp);
		}
	
				
				// 관리자_리뷰삭제
		else if(command.equals("ad_reserveDelete")) {
				
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


	}


	public void dispatch(String urls,HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
	}
}
