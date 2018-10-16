package control;

import java.io.IOException;
import java.util.ArrayList;
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
		
		// [1] 조건에 맞는 호텔 리스트 가져오기 (메인 페이지 오른쪽 검색옵션에서 'search more'버튼을 눌렀을때)
		if(command.equals("search")) {
			String place = req.getParameter("place");
			String price = req.getParameter("price");
			String people = req.getParameter("people");
			String date1 = req.getParameter("date1");
			String date2 = req.getParameter("date2");
			System.out.println("호텔 컨트롤러 -> search 들어옴");
			System.out.println("place = " + place);
			System.out.println("price = " + price);
			System.out.println("people = " + people);
			System.out.println("date1 = " + date1);
			System.out.println("date2 = " + date2);
			
			// 밑에 따로 만든 getSearchList 함수를 실행한 결과를 write로 ajax에 보낸다.(success에 data부분으로 들어갈 것.)
			resp.getWriter().write(getSearchList(place, price, people, date1, date2));
			resp.getWriter().flush();
			
		}
		
		// [2] 해당 호텔의 상세정보 가져오기 (호텔 디테일 페이지에서 '호텔정보'탭을 눌렀을때)
		else if(command.equals("detail")) {
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			System.out.println("호텔 컨트롤러 -> detail 들어옴.");
			System.out.println("seq = " + seq);
			
			
			// 디테일 가져오는 함수 실행.
			HotelService service = HotelService.getInstance();
			HotelDto detailDto = service.getHotelDetail(seq);
			// 짐싸서 보내주기(hotelDetail.jsp로)
			/*req.setAttribute("detailDto", detailDto);
			dispatch("hotelDetail.jsp", req, resp);*/
			
			// DESCRIPTION, READCOUNT, REGDATE를 넘겨주는데, 각각 태그를 임의로 설정해서 넘겨준다. 그럼 find(태그이름)으로 찾을 수 있으니까.
			resp.getWriter().write("<description>" + detailDto.getDescription() + "</description>"+
									"<readcount>" + detailDto.getReadcount() + "</readcount>" + 
									"<regdate>" + detailDto.getRegdate() + "</regdate>");
			resp.getWriter().flush();
			
		}
		
		
	}
	
	// forword만 하는 메소드(결국 쓰진 않았음...)
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// urls : 여기로 가겠다
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
		
	}
	
	// 위에서 서비스를 통해 받아온 호텔 리스트를 json파일로 만들어주는 함수.
	public String getSearchList(String place, String price, String people, String date1, String date2) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		
		HotelService service = HotelService.getInstance();
		List<HotelDto> searchList = service.getSearchHotelList(place, price, people, date1, date2);
		
		for (int i = 0; i < searchList.size(); i++) {
			System.out.println("리스트 json으로 변경중");
			// SEQ, ID, HOTELNAME, REGION, MAXPEOPLE, PRICE, HOTELPHONE, DEL, READCOUNT
			result.append("[{\"value\": \"" + searchList.get(i).getSeq() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getId() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getHotelname() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getRegion() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getMaxpeople() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getPrice() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getHotelphone() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getDel() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getReadcount() + "\"}]");
			if(i != searchList.size() -1) result.append(",");
		}
		result.append("]}");
		return result.toString();
	}
		
	
	
	

}
