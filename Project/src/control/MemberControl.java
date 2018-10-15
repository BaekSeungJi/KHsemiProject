package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import dto.MemberDto;
import model.member.MemberService;

import java.util.List;

public class MemberControl extends HttpServlet {




	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}


	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");

		String command = req.getParameter("command");

		if(command.equals("list")) {

			MemberService service = MemberService.getInstance();			
			List<MemberDto> memberlist = service.getMemberList();

			req.setAttribute("memberlist", memberlist);			
			dispatch("custuserlist.jsp", req, resp);			
		}		
		else if(command.equals("add")) {
			resp.sendRedirect("Signup.jsp");
		}
		else if(command.equals("addAf")) {

			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");



			MemberService service = MemberService.getInstance();
			boolean isS = service.addMember(id, pwd, name, email, phone);			
			if(isS){
				System.out.println("성공적으로 추가되었습니다");		
			}else{		
				System.out.println("추가하지 못했습니다");		
			}	

			resp.sendRedirect("CustUserControl?command=list");
		}
	}


	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);		
	}



}

