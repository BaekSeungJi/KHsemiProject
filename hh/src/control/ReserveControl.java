package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import dto.MemberDto;
import dto.ReserveDto;
import model.hotel.HotelService;
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
		
		
		
		if(command.equals("reserve")) {
			String id = req.getParameter("id");
			
			MemberService memservice = MemberService.getInstance();
			ReserveService resservice = ReserveService.getInstance();
			HotelService hotelServicervice = HotelService.getInstance();
			
	
			MemberDto dto = memservice.login(id);
	
			List<ReserveDto> list = resservice.getreserveList(id);
					
			for(ReserveDto reservedto : list){
				System.out.println(reservedto.toString());					
			}	
			
			req.setAttribute("list", list);
			
			dispatch("reserve.jsp", req, resp);

		}
		
		else if(command.equals("reservedel")) {
String id = req.getParameter("id");
			
			MemberService memservice = MemberService.getInstance();
			ReserveService resservice = ReserveService.getInstance();
			HotelService hotelServicervice = HotelService.getInstance();
			
	
			MemberDto dto = memservice.login(id);
	
			List<ReserveDto> list = resservice.getreserveList(id);
					
			for(ReserveDto reservedto : list){
				System.out.println(reservedto.toString());					
			}	
			
			req.setAttribute("list", list);
			
			dispatch("reservedel.jsp", req, resp);
		}
		
		else if(command.equals("reservedetail")) {
String id = req.getParameter("id");
			
			MemberService memservice = MemberService.getInstance();
			ReserveService resservice = ReserveService.getInstance();
			HotelService hotelServicervice = HotelService.getInstance();
			
	
			MemberDto dto = memservice.login(id);
	
			List<ReserveDto> list = resservice.getreserveList(id);
					
			for(ReserveDto reservedto : list){
				System.out.println(reservedto.toString());					
			}	
			
			req.setAttribute("list", list);
			
			dispatch("reservedetail.jsp", req, resp);
		}
		
		else if(command.equals("reservelist")) {
String id = req.getParameter("id");
			
			MemberService memservice = MemberService.getInstance();
			ReserveService resservice = ReserveService.getInstance();
			HotelService hotelServicervice = HotelService.getInstance();
			
	
			MemberDto dto = memservice.login(id);
	
			List<ReserveDto> list = resservice.getreserveList(id);
					
			for(ReserveDto reservedto : list){
				System.out.println(reservedto.toString());					
			}	
			
			req.setAttribute("list", list);
			
			dispatch("reservelist.jsp", req, resp);
		}
		
		else if(command.equals("reserveupdate")) {
String id = req.getParameter("id");
			
			MemberService memservice = MemberService.getInstance();
			ReserveService resservice = ReserveService.getInstance();
			HotelService hotelServicervice = HotelService.getInstance();
			
	
			MemberDto dto = memservice.login(id);
	
			List<ReserveDto> list = resservice.getreserveList(id);
					
			for(ReserveDto reservedto : list){
				System.out.println(reservedto.toString());					
			}	
			
			req.setAttribute("list", list);
			
			dispatch("reserveupdate.jsp", req, resp);
		}
		
		else if(command.equals("reserveupdateaf")) {
String id = req.getParameter("id");
			
			MemberService memservice = MemberService.getInstance();
			ReserveService resservice = ReserveService.getInstance();
			HotelService hotelServicervice = HotelService.getInstance();
			
	
			MemberDto dto = memservice.login(id);
	
			List<ReserveDto> list = resservice.getreserveList(id);
					
			for(ReserveDto reservedto : list){
				System.out.println(reservedto.toString());					
			}	
			
			req.setAttribute("list", list);
			
			dispatch("reserveupdate.jsp", req, resp);
		}
		
		else if(command.equals("reservewrite")) {
String id = req.getParameter("id");
			
			MemberService memservice = MemberService.getInstance();
			ReserveService resservice = ReserveService.getInstance();
			HotelService hotelServicervice = HotelService.getInstance();
			
	
			MemberDto dto = memservice.login(id);
	
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
			
	
			MemberDto dto = memservice.login(id);
	
			List<ReserveDto> list = resservice.getreserveList(id);
					
			for(ReserveDto reservedto : list){
				System.out.println(reservedto.toString());					
			}	
			
			req.setAttribute("list", list);
			
			dispatch("reservewriteAf.jsp", req, resp);
		}
		
	}
	
	
	public void dispatch(String urls,HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
			}
}
