package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;


import javax.servlet.RequestDispatcher;

public class MemberControl extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		try {
			doProcess(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	

		try {
			doProcess(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws Exception, ServletException {


		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=UTF-8");

		String command = req.getParameter("command");

		if(command.equals("Signup")) {			//회원가입
			resp.sendRedirect("Signup.jsp");
		}		
		else if (command.equals("회원가입")) {	//회원가입Af
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			int blacklist = Integer.parseInt("0");
			int auth = Integer.parseInt("3");
			
			req.setAttribute("id", id);
			req.setAttribute("pwd", pwd);
			req.setAttribute("name", name);
			req.setAttribute("email", email);
			req.setAttribute("phone", phone);
			dispatch("SignupAf.jsp", req, resp);
	
		}
		else if(command.equals("Login")) {		//로그인
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			req.setAttribute("id", id);
			req.setAttribute("pwd", pwd);
	
			dispatch("login.jsp", req, resp);	
		
			
			
		}

		


	}


	public void dispatch(String urls, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);		
	}



















}

