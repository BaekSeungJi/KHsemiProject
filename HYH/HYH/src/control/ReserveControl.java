package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDto;
import model.member.MemberService;

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
		
		req.getParameter("command");
		
		String command =req.getParameter("command");
		
		if(command.equals("reserve")) {
			String id = req.getParameter("id");
			
			String hotelid = req.getParameter("hid");
			
			MemberService service = MemberService.getInstance();
			
			MemberDto dto = service.login(id);
			
			String code = dto.getId();
			String name = dto.getName();
			String email = dto.getEmail();
			String phone = dto.getPhone();
			int black = dto.getBlacklist();
			int auth = dto.getAuth();
			
			System.out.println("dto.toString():" + dto.toString());
			
			req.setAttribute("dto", dto);
		
			dispatch("callendar.jsp", req, resp);
		
		}
	}

	public void dispatch(String urls,HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
		}
	}
	

