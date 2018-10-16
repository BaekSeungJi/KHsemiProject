package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ReviewDto;
import model.review.ReviewService;

public class ReviewControl extends HttpServlet {

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
		System.out.println("review command = " + command);
		
		// [1] 해당 호텔 후기 전부 가져오기 (디테일 페이지에서 '사용자 후기'탭을 클릭했을때)
		if(command.equals("review")) {
			System.out.println("리뷰 컨트롤러 -> review 들어옴");
			String hotelname = req.getParameter("hotelname");
			System.out.println("hotelname = " + hotelname);
			ReviewService service = ReviewService.getInstance();
			List<ReviewDto> reviewList = service.getReviewList(hotelname);
			
			req.setAttribute("reviewList", reviewList);
			dispatch("hotelDetail.jsp", req, resp);
		}
		
	}
	
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// urls : 여기로 가겠다
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
		
	}
	
}
