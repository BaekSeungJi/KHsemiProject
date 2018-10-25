package model.chat;

import java.util.ArrayList;

import dto.ChatDto;

public interface iChatManager {
	
	public ArrayList<ChatDto> getChatListByID(String fromID, String toID, String seq);
	
	
	public int submit(String fromID, String toID, String chatContent);
	
	public ArrayList<ChatDto> getBox(String userID);
}
