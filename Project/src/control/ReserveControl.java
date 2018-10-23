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

			dispatch("reserve.jsp", req, resp);

		}

		else if(command.equals("reservedel")) {
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

			dispatch("reservedel.jsp", req, resp);
		}

		else if(command.equals("reservedetail")) {
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

			dispatch("reserveupdate.jsp", req, resp);
		}

		else if(command.equals("reserveupdateaf")) {
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

			dispatch("reserveupdate.jsp", req, resp);
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

		//////////////////////
		else if(command.equals("reservedel")) {
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

			dispatch("reservedel.jsp", req, resp);
		}

		else if(command.equals("reservedetail")) {
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

			dispatch("reserveupdate.jsp", req, resp);
		}

		else if(command.equals("reserveupdateaf")) {
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

			dispatch("reserveupdate.jsp", req, resp);
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

				
					
					int seq = Integer.parseInt(req.getParameter("seq"));
					
					String id = req.getParameter("id");
					
					ReserveService service = ReserveService.getInstance();
					
					boolean b = service.ad_reservedelete(seq);
					
					if(b == true) {
					
						
						resp.sendRedirect("MemberControl?command=ad_member_detail&id="+id);
					}else {
				
						
						resp.sendRedirect("MemberControl?command=ad_member_detail&id="+id);
					}
				}
				
				// 관리자 예약 수정
				else if(command.equals("ad_reserveUpdate")) {

				
					
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
						
						resp.sendRedirect("MemberControl?command=ad_member_detail&id="+id);
					}else {
						
						resp.sendRedirect("MemberControl?command=ad_member_detail&id="+id);
					}
				}
		
		
		
	}


	public void dispatch(String urls,HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
	}
}
