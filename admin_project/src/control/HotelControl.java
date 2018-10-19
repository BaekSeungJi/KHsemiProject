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
import javax.websocket.Session;

import dto.HotelDto;
import dto.MemberDto;
import dto.MonthlysalesDto;
import dto.ReserveDto;
import dto.ReviewDto;
import dto.scoreDto;
import dto.util;
import model.hotel.HotelService;
import model.member.MemberService;
import model.reserve.ReserveService;
import model.review.ReviewService;

public class HotelControl extends HttpServlet {

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
	
		

		// admin 호텔 정보 수정
		if(command.equals("ad_hotelUpdate")) {
			PrintWriter out = resp.getWriter();
			
			String hotelname = req.getParameter("hotelname");
			String DESCRIPTION = req.getParameter("description");
			int MAXPEOPLE = Integer.parseInt(req.getParameter("maxpeople"));
			int PRICE = Integer.parseInt(req.getParameter("price"));
			int HOTELPHONE = Integer.parseInt(req.getParameter("hotelphone"));
			
			System.out.println(hotelname);
			System.out.println(DESCRIPTION);
			System.out.println(MAXPEOPLE);
			System.out.println(PRICE);
			System.out.println(HOTELPHONE);
			HotelService service = HotelService.getInstance();
			
			boolean b = service.ad_HotelUpdate(hotelname, DESCRIPTION, MAXPEOPLE, PRICE, HOTELPHONE);
			
			if(b == true) {
				out.println("alert('수정 완료되었습니다');");
			
				resp.sendRedirect("HotelControl?command=ad_hotel&hotelname="+hotelname);
			}else {
				out.println("alert('수정 실패하였습니다');");
				resp.sendRedirect("HotelControl?command=ad_hotel&hotelname="+hotelname);
			}
			
		}
		
		else if (command.equals("ad_GohotelUpdate")) {
			PrintWriter out = resp.getWriter();
			
			String hotelname = req.getParameter("hotelname");
			
			HotelService service = HotelService.getInstance();
			
			HotelDto dto = service.getHoteldetail(hotelname);
		
			req.setAttribute("dto", dto);
			
			dispatch("ad_hotelUpdate.jsp", req, resp);
		}
		
		else if (command.equals("ad_hotel")) {	
			PrintWriter out = resp.getWriter();
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, 1);

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

			String hotelname = (String)req.getSession().getAttribute("hotelname");
	
			
			ReserveService Rservice = ReserveService.getInstance();
			
			List<ReserveDto> list = Rservice.getCalendarList(hotelname, year + util.two(month + ""));
		
			req.setAttribute("cal", cal);
			req.setAttribute("dayOfWeek", dayOfWeek);
			req.setAttribute("year", year);
			req.setAttribute("month", month);
			
			
			req.setAttribute("list", list);
			
			dispatch("ad_hotel.jsp", req, resp);
			
		}
		
		else if (command.equals("ad_chart")) {	
			MemberDto dto = (MemberDto)req.getSession().getAttribute("login");
			String id = dto.getId();

			String hotelname = (String)req.getSession().getAttribute("hotelname");
			
			ReserveService ser = ReserveService.getInstance();
			ReviewService Rservice = ReviewService.getInstance();
			HotelService Hser = HotelService.getInstance();
			
			int price = Integer.parseInt(Hser.getPrice(hotelname));

			List<ReviewDto> Rlist = Rservice.ad_getReview(hotelname);
			List<ReserveDto> Rdto = ser.getReserve(hotelname);
			
			MonthlysalesDto Mdto =  util.getsales(Rdto, price);
			scoreDto sdto = util.getScore(Rlist);
			
			req.setAttribute("Mdto", Mdto);
			req.setAttribute("sdto", sdto);
			
			dispatch("ad_chart.jsp", req, resp);
			
		}
		
		else if (command.equals("ad_caldetail")) {	
			String hotelname= req.getParameter("hotelname"); 
			
			String year= req.getParameter("year"); 
			String month= req.getParameter("month"); 
			String day= req.getParameter("day"); 		

			String yyyymmdd = year + util.two(month + "") + util.two(day + "");

			System.out.print(year);
			System.out.print(month);
			System.out.print(day);
			System.out.print(yyyymmdd);
			
			ReserveService Rs = ReserveService.getInstance();
			
			List<ReserveDto> list = Rs.getlist(hotelname, yyyymmdd);
			
			req.setAttribute("list", list);
			req.setAttribute("year", year);
			req.setAttribute("month", month);
			req.setAttribute("day", day);
			req.setAttribute("yyyymmdd", yyyymmdd);
			
			dispatch("ad_caldetail.jsp", req, resp);
		}
		
		else if (command.equals("ad_calDelete")) {	
			PrintWriter out = resp.getWriter();
			
			System.out.print("delete들옴");
		int seq = Integer.parseInt(req.getParameter("seq")); 
		String hotelname = req.getParameter("hotelname");
		ReserveService service = ReserveService.getInstance();
		
		System.out.print(seq);
		System.out.print(hotelname);
		
		boolean b = service.ad_reservedelete(seq);
		
		if(b == true) {
		
			out.println("alert('삭제 완료되었습니다');");

			resp.sendRedirect("HotelControl?command=ad_hotel&hotelname="+hotelname);
		}else {
	
			out.println("alert('삭제 실패하였습니다');");
		
			resp.sendRedirect("HotelControl?command=ad_hotel&hotelname="+hotelname);
		}
	}
	}
	
	
	
	public void dispatch(String urls,HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
	RequestDispatcher dispatch = req.getRequestDispatcher(urls);
	dispatch.forward(req, resp);
		}
}
