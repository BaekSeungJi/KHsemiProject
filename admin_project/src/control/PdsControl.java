package control;

import java.io.File;
import java.io.IOException;
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
import dto.PdsfileDto;
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
			
			List<PdsfileDto> list = service.getPdsList();
			
			req.setAttribute("list", list);
			
			dispatch("ad_notice.jsp", req, resp);
			
		}
		else if(command.equals("ad_pdsWrite")) {
		
			System.out.println("ad_pdsWrite컨트롤 들옴");
			
			PdsService service = PdsService.getInstance();
			
			String id =req.getParameter("id");
			String title =req.getParameter("title");
			String content =req.getParameter("content");
			String filename =req.getParameter("filename");
			
			System.out.println(filename);
			
			service.writeFile(new FileDto(0, filename, filename));
			service.writePds(new PdsDto(id, title, content));
			
			dispatch("PdsControl?command=ad_noticeGo", req, resp);
			
		}
	}
	
	public void dispatch(String urls,HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException{
		RequestDispatcher dispatch = req.getRequestDispatcher(urls);
		dispatch.forward(req, resp);
			}
}
