package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import model.member.MemberService;
import model.review.ReviewService;

public class ReviewControl extends HttpServlet {

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
		
		
		
		// 관리자_리뷰삭제
		if(command.equals("ad_reviewDelete")) {
			PrintWriter out = resp.getWriter();
			
			int seq = Integer.parseInt(req.getParameter("seq"));
			
			String id = req.getParameter("id");
		
			ReviewService service = ReviewService.getInstance();
			
			boolean b = service.reviewdelete(seq);
	
			if(b == true) {
				out.println("alert('삭제 완료되었습니다');");
				resp.sendRedirect("MemberControl?command=ad_member_detail&id="+id);
			}else {
				out.println("alert('삭제 실패하였습니다');");
				resp.sendRedirect("MemberControl?command=ad_member_detail&id="+id);
			}
		}
		
	}
	
}
