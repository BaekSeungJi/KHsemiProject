package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import dto.HotelDto;
import model.hotel.HotelService;
import model.member.MemberService;

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
				JOptionPane.showMessageDialog(null, "수정 완료되었습니다");
				resp.sendRedirect("ad_hotel.jsp");
			}else {
				JOptionPane.showMessageDialog(null,"수정 실패하였습니다");
				resp.sendRedirect("ad_hotel.jsp");
			}
			
		}
		
		else if (command.equals("ad_GohotelUpdate")) {
		
			
			String hotelname = req.getParameter("hotelname");
			
			HotelService service = HotelService.getInstance();
			
			HotelDto dto = service.getHoteldetail(hotelname);
		
			req.setAttribute("dto", dto);
			
			dispatch("ad_hotelUpdate.jsp", req, resp);
		}
		
		else if (command.equals("ad_hotel")) {	
			String hotelname = req.getParameter("hotelname");
			
			HotelService service = HotelService.getInstance();
			
			List<String> list = service.getMonthlyChart(hotelname);
			
			req.setAttribute("list", list);
			
			dispatch("ad_hotel.jsp", req, resp);
			
		}
	}
	
	
	
	public void dispatch(String urls,HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
	RequestDispatcher dispatch = req.getRequestDispatcher(urls);
	dispatch.forward(req, resp);
		}
}
