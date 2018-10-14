package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.HotelDto;
import model.hotel.HotelService;

public class HotelControl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String command = req.getParameter("command");
		System.out.println("command = " + command);
		
		// 메인 페이지 오른쪽 검색옵션에서 검색버튼을 눌렀을때
		if(command.equals("search")) {
			String place = req.getParameter("place");
			String price = req.getParameter("price");
			String people = req.getParameter("people");
			String date1 = req.getParameter("date1");
			String date2 = req.getParameter("date2");
			System.out.println(place);
			System.out.println(price);
			System.out.println(people);
			System.out.println(date1);
			System.out.println(date2);
			
			HotelService service = HotelService.getInstance();
			List<HotelDto> searchList = service.getSearchHotelList(place, price, people, date1, date2);
			
			req.setAttribute("searchList", searchList);
			dispatch("searchList.jsp", req, resp);
		}
		
		
	}
	
	
	// forword만 하는 메소드
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// urls : 여기로 가겠다
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
		
	}

}
