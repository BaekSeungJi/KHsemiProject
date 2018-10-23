package dto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.pds.PdsService;





public class FileDownloader extends HttpServlet {

	private ServletConfig mConfig = null;
	private static final int BUFFER_SIZE = 8192;   // 8kb
	
	
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		mConfig= config;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("FileDownloader doget");
		
		String filename = req.getParameter("filename");
		
		// download 횟수 증가
		
		
		PdsService ser = PdsService.getInstance();
		
		ser.downCount(Integer.parseInt(req.getParameter("seq")));
	
		
		
		BufferedOutputStream out = new BufferedOutputStream(resp.getOutputStream());
		String filePath = "";
		
		// tomcat
//		filePath = mConfig.getServletContext().getRealPath("/upload");
	
		// folder
		filePath = "c:\\tmp";
	
		try{
			filePath = filePath + "\\" + filename;
			
			File f = new File(filePath);
			System.out.println("파일경로:"+filePath);
			
			
			// window download 설정 
			if(f.exists() && f.canRead()) {
				resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
				resp.setHeader("Content-Transfer-Encoding", "binary;");
				resp.setHeader("Content-Length", "" + f.length());
				resp.setHeader("Pragma", "no-cache;"); 
				resp.setHeader("Expires", "-1;");
				
				// 파일을 생성, 기입
				BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));
				byte buffer[] = new byte[BUFFER_SIZE];
				
				int read = 0;
				
				while((read = fileInput.read(buffer))!= -1) {
					out.write(buffer,0,read);
				}
				fileInput.close();
				out.flush();
				
			}
			else {
				System.out.println("파일이 존재하지 않습니다");
			}
		}catch(Exception e){
			
		}finally {
			if(out != null) {
				out.flush();
				out.close();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
