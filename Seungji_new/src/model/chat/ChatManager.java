package model.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DBClose;
import db.DBConnection;
import dto.ChatDto;

public class ChatManager implements iChatManager {
	
	public ChatManager() {
		DBConnection.initConnect();
	}
	
	// 채팅 리스트 출력
	@Override
	public ArrayList<ChatDto> getChatListByID(String fromID, String toID, String seq) {
		
		ArrayList<ChatDto> chatList = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM CHAT "
				+ " WHERE ((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?)) "
				+ " AND SEQ > ? ORDER BY chatTime";
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 getChatListByID Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getChatListByID Success");
			
			psmt.setString(1, fromID);
			psmt.setString(2, toID);
			psmt.setString(3, toID);
			psmt.setString(4, fromID);
			psmt.setInt(5, Integer.parseInt(seq));
			System.out.println("3/6 getChatListByID Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getChatListByID Success");
			
			chatList = new ArrayList<ChatDto>();
			
			while(rs.next()) {
				ChatDto chat = new ChatDto();
				chat.setSeq(rs.getInt("seq"));
				chat.setFromID(rs.getString("fromId").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toId").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chattime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if(chattime > 12) {
					timeType = "오후";
					chattime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chattime + " : " + rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chat);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("getChatListByID Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return chatList;
		
	}
	
	// 최근 채팅리스트만 가져오기
	@Override
	public ArrayList<ChatDto> getChatListByRecent(String fromID, String toID, int number) {
		
		ArrayList<ChatDto> chatList = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM CHAT "
				+ " WHERE ((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?)) "
				+ " AND SEQ > (SELECT MAX(SEQ) - ? FROM CHAT) ORDER BY chatTime";
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 getChatListByID Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getChatListByID Success");
			
			psmt.setString(1, fromID);
			psmt.setString(2, toID);
			psmt.setString(3, toID);
			psmt.setString(4, fromID);
			psmt.setInt(5, number);
			System.out.println("3/6 getChatListByID Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getChatListByID Success");
			chatList = new ArrayList<ChatDto>();
			
			while(rs.next()) {
				ChatDto chat = new ChatDto();
				chat.setSeq(rs.getInt("seq"));
				chat.setFromID(rs.getString("fromId").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toId").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chattime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if(chattime > 12) {
					timeType = "오후";
					chattime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chattime + " : " + rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chat);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("getChatListByID Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return chatList;
	}

	
	// 채팅입력
	@Override
	public int submit(String fromID, String toID, String chatContent) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " INSERT INTO CHAT "
				+ " VALUES(SEQ_CHAT.NEXTVAL, ?, ?, ?, SYSDATE)";
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 submit Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 submit Success");
			
			psmt.setString(1, fromID);
			psmt.setString(2, toID);
			psmt.setString(3, chatContent);
			System.out.println("3/6 submit Success");
			
			return psmt.executeUpdate();	// 입력 성공하면 1을 반환함.
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("submit Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return -1;	// 실패라면 -1을 반환해라.
	}
	
	
	// 메시지함
	@Override
	public ArrayList<ChatDto> getBox(String userID) {
		
		
		ArrayList<ChatDto> chatList = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM CHAT "
				+ " WHERE SEQ IN (SELECT MAX(SEQ) FROM CHAT WHERE TOID = ? "
				+ 					" OR FROMID = ? GROUP BY fromID, toID) ";
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/6 getBox Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/6 getBox Success");
			
			psmt.setString(1, userID);
			psmt.setString(2, userID);
			System.out.println("3/6 getBox Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getBox Success");
			chatList = new ArrayList<ChatDto>();
			
			while(rs.next()) {
				ChatDto chat = new ChatDto();
				chat.setSeq(rs.getInt("seq"));
				chat.setFromID(rs.getString("fromId").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toId").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chattime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if(chattime > 12) {
					timeType = "오후";
					chattime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chattime + " : " + rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chat);
			}
			// a가 b랑 대화한 내역 중 가장 최신의 메시지 1개만 남도록.
			for (int i = 0; i < chatList.size(); i++) {
				ChatDto x = chatList.get(i);
				for (int j = 0; j < chatList.size(); j++) {
					ChatDto y = chatList.get(j);
					if(x.getFromID().equals(y.getToID()) && x.getToID().equals(y.getFromID())) {
						if(x.getSeq() < y.getSeq()) {
							chatList.remove(x);
							i--;
							break;
						} else {
							chatList.remove(y);
							j--;
						}
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("getBox Fail");
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return chatList;
		
	}
	
	
	
	
	

}
