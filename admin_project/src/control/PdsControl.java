package control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import dto.FileDto;
import dto.PdsDto;

import dto.util;
import model.pds.PdsService;

public class PdsControl extends HttpServlet {

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
		
		if(command.equals("ad_noticeGo")) {
	
			PdsService service = PdsService.getInstance();
			
			List<PdsDto> list = service.getPdsList();
			
			req.setAttribute("list", list);
			
			dispatch("ad_notice.jsp", req, resp);
			
		}
		
		else if(command.equals("ad_noticeGo2")) {
			
			PdsService service = PdsService.getInstance();
			
			List<PdsDto> list = service.getPdsList();
			
			req.setAttribute("list", list);
			
			dispatch("ad_noticeMainAdmin.jsp", req, resp);
			
		}
		else if(command.equals("ad_pdsWrite")) {
			PrintWriter out = resp.getWriter();
			
			System.out.println("ad_pdsWrite컨트롤 들옴");
			
			PdsService service = PdsService.getInstance();
			
			String id =req.getParameter("id");
			String title =req.getParameter("title");
			String content =req.getParameter("content");
			String filename =req.getParameter("filename");
			
			System.out.println(filename);
			
		
		boolean b=	service.writePds(new PdsDto(id, title, content, filename));
			
		if(b==true) {
			out.println("alert('글작성이 완료되었습니다');");
		}else {
			out.println("alert('글작성을 실패하였습니다');");
		}
		
			dispatch("PdsControl?command=ad_noticeGo", req, resp);
			
		}
		
		else if(command.equals("pdsdetail")) {
			int seq =Integer.parseInt(req.getParameter("seq"));
			
			System.out.println("시퀀스:"+seq);
		
			PdsService service = PdsService.getInstance();
			
			service.readCount(seq);
			
			PdsDto dto = service.getPds(seq);
			
			req.setAttribute("dto", dto);
			
			dispatch("ad_notice_detail.jsp", req, resp);
			
		}
		
		else if(command.equals("pdsDelete")) {
			PrintWriter out = resp.getWriter();
			
			int seq =Integer.parseInt(req.getParameter("seq"));
			
			PdsService service = PdsService.getInstance();
			
			boolean b =service.pdsDelete(seq);
			
			if(b==true) {
			out.println("alert('삭제하였습니다');");
			}else {
				out.println("alert('삭제 실패하였습니다');");
			}
			
			resp.sendRedirect("PdsControl?command=ad_noticeGo");
			
		}
		else if(command.equals("pdsUpdateGo")) {
			
			int seq =Integer.parseInt(req.getParameter("seq"));
			
			PdsService service = PdsService.getInstance();
			
			PdsDto dto = service.getPds(seq);
			
			req.setAttribute("dto", dto);
			
			dispatch("ad_notice_update.jsp", req, resp);
			
		}
		else if(command.equals("ad_pdsupdate")) {
			PrintWriter out = resp.getWriter();
			
			System.out.println("ad_pdsupdate컨트롤 들옴");
			
			PdsService service = PdsService.getInstance();
			
			String id =req.getParameter("id");
			String _seq =req.getParameter("seq");
			String title =req.getParameter("title");
			String content =req.getParameter("content");
			String filename =req.getParameter("filename");
			
			int seq = Integer.parseInt(_seq);
			System.out.println(filename);
			
			PdsDto dto = new PdsDto(id, title, content, filename);
		boolean b=	service.ad_PdsUpdate(seq, dto);
			
		if(b==true) {
			out.println("alert('글수정이 완료되었습니다');");
		}else {
			out.println("alert('글수정을 실패하였습니다');");
		}
		
			dispatch("PdsControl?command=ad_noticeGo", req, resp);
			
		}
	
		else if(command.equals("ad_notice_answer")) {
			int seq = (Integer.parseInt(req.getParameter("seq")));

			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			PdsService service = PdsService.getInstance();
			
			PdsDto dto = new  PdsDto(id, title, content);
			
			boolean b = service.answer(seq, dto);
			
			if(b==true) {
				resp.sendRedirect("PdsControl?command=ad_noticeGo");
			}else {
				resp.sendRedirect("start.jsp");
			}
		}
	}
	
	public void dispatch(String urls,HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
			}
}
