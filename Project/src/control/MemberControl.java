package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.servlet.RequestDispatcher;

import dto.MemberDto;
import model.member.MemberManager;
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



	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws Exception, ServletException {
	
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");

		String command = req.getParameter("command");
	//	MemberService service = MemberService.getInstance();	
	
		System.out.println("여기까지");
		if(command.equals("signup")) {
			System.out.println("여기까지");
			resp.sendRedirect("signup.jsp");
		}		
		else if (command.equals("SignupAf")) {
		
			resp.sendRedirect("SignupAf.jsp");
			
			
			/*String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			

			boolean isS = service.manager.addMember(new MemberDto(id, pwd, name, email, phone, 0, 3));			
		
			if(isS){
				System.out.println("회원가입 성공");
				resp.sendRedirect("./loginview.jsp");
				dispatch("loginview.jsp", req, resp);
			}else{		
				System.out.println("회원가입 실패");
				resp.sendRedirect("./Signup.jsp");
				dispatch("Signup.jsp", req, resp);
			}	*/
		}

		else if(command.equals("login")) {
			resp.sendRedirect("login.jsp");		
		/*	String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
						
			MemberDto in = service.manager.login(new MemberDto(id, pwd, null, null, null, 0, 3));
			if(in==null) {
				resp.sendRedirect("./loginview.jsp");
				dispatch("loginview.jsp", req, resp);
				
			}else {
				
				resp.sendRedirect("MemberControl?command=Signup");
			}
			

			
			resp.sendRedirect("MemberControl?command=Signup");*/
		}



	}


	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);		
	}



















}

