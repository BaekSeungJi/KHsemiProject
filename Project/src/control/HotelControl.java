package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.HotelDto;
import dto.MemberDto;
import dto.MonthlysalesDto;
import dto.ReserveDto;
import dto.ReviewDto;
import dto.scoreDto;
import dto.util;
import model.hotel.HotelService;
import model.reserve.ReserveService;
import model.review.ReviewService;

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
			resp.getWriter().write(hotelListToJson(place, price, people, date1, date2));
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
		else if(command.equals("ad_hotelUpdate")) {
			PrintWriter out = resp.getWriter();

			String hotelname = req.getParameter("hotelname");
			String DESCRIPTION = req.getParameter("description");
			int MAXPEOPLE = Integer.parseInt(req.getParameter("maxpeople"));
			int PRICE = Integer.parseInt(req.getParameter("price"));
			String HOTELPHONE = req.getParameter("hotelphone");

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

	// forword만 하는 메소드(결국 쓰진 않았음...)
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// urls : 여기로 가겠다
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);

	}

	// 위에서 서비스를 통해 받아온 호텔 리스트를 json파일로 만들어주는 함수.
	public String hotelListToJson(String place, String price, String people, String date1, String date2) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");

		HotelService service = HotelService.getInstance();
		List<HotelDto> searchList = service.getSearchHotelList(place, price, people, date1, date2);

		for (int i = 0; i < searchList.size(); i++) {
			System.out.println("호텔리스트 json으로 변경중");
			// SEQ, ID, HOTELNAME, REGION, MAXPEOPLE, PRICE, HOTELPHONE, DEL, READCOUNT, IMAGE
			result.append("[{\"value\": \"" + searchList.get(i).getSeq() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getId() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getHotelname() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getRegion() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getMaxpeople() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getPrice() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getHotelphone() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getDel() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getReadcount() + "\"},");
			result.append("{\"value\": \"" + searchList.get(i).getImage() + "\"}]");
			if(i != searchList.size() -1) result.append(",");
		}
		result.append("]}");
		return result.toString();
	}





}
