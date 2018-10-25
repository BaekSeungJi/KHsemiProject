package model.chat;

import java.util.ArrayList;

import dto.ChatDto;

public class ChatService {
	
	private static ChatService chatService = null;
	public iChatManager manager;
	
	private ChatService() {
		manager = new ChatManager();
	}
	
	public static ChatService getInstance() {
		if(chatService == null) {
			chatService = new ChatService();
		}
		
		return chatService;
	}
	
	public ArrayList<ChatDto> getChatListByID(String fromID, String toID, String seq){
		return manager.getChatListByID(fromID, toID, seq);
	}
	
	public int submit(String fromID, String toID, String chatContent) {
		return manager.submit(fromID, toID, chatContent);
	}
	
	public ArrayList<ChatDto> getBox(String userID){
		return manager.getBox(userID);
	}

}
