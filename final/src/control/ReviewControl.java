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
			
			// 밑에 따로 만든 getSearchList 함수를 실행한 결과를 write로 ajax에 보낸다.(success에 data부분으로 들어갈 것.)
			resp.getWriter().write(reviewToJson(hotelname));
			resp.getWriter().flush();
			
			
		}else if(command.equals("addReview")) {
			System.out.println("리뷰 컨트롤러 -> addReview 들어옴");
			
			// review.jsp 페이지에서 넘겨받은 값.
			String sseq = req.getParameter("seq");
			int seq = Integer.parseInt(sseq);
			String name = req.getParameter("name");
			String hotel = req.getParameter("hotel");
			String title = req.getParameter("title");
			String content = req.getParameter("message");
			String sscore = req.getParameter("score");
			int score = Integer.parseInt(sscore);
			
			
			// dto로 묶어서
			ReviewDto inputDto = new ReviewDto(seq, name, hotel, title, content, score, 0, 0, "");
			
			// 리뷰 DB에 추가
			ReviewService service = ReviewService.getInstance();
			service.addReview(inputDto);
			
			dispatch("index.jsp", req, resp);
		}
		
	}
	
	// 위에서 서비스를 통해 받아온 리뷰 리스트를 json파일로 만들어주는 함수.
		public String reviewToJson(String hotelname) {
			StringBuffer result = new StringBuffer("");
			//result.append("{\"result\":[");
			result.append("[");
			
			ReviewService service = ReviewService.getInstance();
			List<ReviewDto> searchList = service.getReviewList(hotelname);
			
			for (int i = 0; i < searchList.size(); i++) {
				System.out.println("리뷰리스트 json으로 변경중");
				// NUM, ID, TITLE, CONTENT, SCORE, DEL, REGDATE
				result.append("{\"num\": \"" + searchList.get(i).getNum() + "\",");
				result.append("\"id\": \"" + searchList.get(i).getId() + "\",");
				result.append("\"title\": \"" + searchList.get(i).getTitle() + "\",");
				result.append("\"content\": \"" + searchList.get(i).getContent() + "\",");
				result.append("\"score\": \"" + searchList.get(i).getScore() + "\",");
				result.append("\"del\": \"" + searchList.get(i).getDel() + "\",");
				result.append("\"regdate\": \"" + searchList.get(i).getRegdate() + "\"}");
				if(i != searchList.size() -1) result.append(",");
			}
			
			 result.append("]");
			 
			System.out.println("파싱한 리뷰데이터 : " + result.toString());
			
			return result.toString();
			
		}
	
	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// urls : 여기로 가겠다
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
		
	}
	
	
	
	
}
