package control;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ChatDto;
import model.chat.ChatService;

public class ChatControl extends HttpServlet {

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
		System.out.println("command = " + command);
		
		if(command.equals("addChat")) {
			String fromID = req.getParameter("fromID");
			String toID = req.getParameter("toID");
			String chatContent = req.getParameter("chatContent");
			
			if(fromID == null || fromID.equals("") || toID == null || toID.equals("")
					|| chatContent == null || chatContent.equals("")) {
				resp.getWriter().write("0");
			} else {
				fromID = URLDecoder.decode(fromID, "UTF-8");
				toID = URLDecoder.decode(toID, "UTF-8");
				chatContent = URLDecoder.decode(chatContent, "UTF-8");
				
				ChatService service = ChatService.getInstance();
				resp.getWriter().write(service.submit(fromID, toID, chatContent) + "");
				// 성공 1, 실패 -1이 반환
			}
		}
		else if(command.equals("chatList")) {
			String fromID = req.getParameter("fromID");
			String toID = req.getParameter("toID");
			String listType = req.getParameter("listType");	// seq?
			
			if(fromID == null || fromID.equals("") || toID == null || toID.equals("")
					|| listType == null || listType.equals("")) {
				resp.getWriter().write("");
			} else if(listType.equals("ten")) {
				resp.getWriter().write(getTen(URLDecoder.decode(fromID, "UTF-8"), URLDecoder.decode(toID, "UTF-8")));
			} else {
				try {
					resp.getWriter().write(getID(URLDecoder.decode(fromID, "UTF-8"), URLDecoder.decode(toID, "UTF-8"), listType));
					// 특정한 채팅 아이디값을 기준으로 리스트를 가져옴
				}catch(Exception e) {
					resp.getWriter().write("");
				}
			}
		}
		else if(command.equals("chatBox")) {
			String userID = req.getParameter("userID");
			if(userID == null || userID.equals("")) {
				resp.getWriter().write("");
			} else {
				try {
					userID = URLDecoder.decode(userID, "UTF-8");
					resp.getWriter().write(getBox(userID));
				} catch(Exception e ) {
					resp.getWriter().write("");
				}
				
			}
		}
		
		
	}
	
	public String getTen(String fromID, String toID) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		
		ChatService service = ChatService.getInstance();
		ArrayList<ChatDto> chatList = service.getChatListByRecent(fromID, toID, 10);
		if(chatList.size() == 0) return "";
		for (int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getFromID() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}");
			if(i != chatList.size() -1 ) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getSeq() + "\"}");
		return result.toString();
		
	}
	
	public String getID(String fromID, String toID, String seq) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		
		ChatService service = ChatService.getInstance();
		ArrayList<ChatDto> chatList = service.getChatListByID(fromID, toID, seq);
		if(chatList.size() == 0) return "";
		for (int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getFromID() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() -1 ) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getSeq() + "\"}");
		return result.toString();
		
	}
	
	public String getBox(String userID) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		
		ChatService service = ChatService.getInstance();
		ArrayList<ChatDto> chatList = service.getBox(userID);
		if(chatList.size() == 0) return "";
		for (int i = 0; i < chatList.size(); i++) {
			result.append("[{\"value\": \"" + chatList.get(i).getFromID() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getToID() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\": \"" + chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() -1 ) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size() - 1).getSeq() + "\"}");
		return result.toString();
		
	}
	
	
	
}
