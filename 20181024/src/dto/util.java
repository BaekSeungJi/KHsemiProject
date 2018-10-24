package dto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import org.apache.tomcat.util.http.fileupload.FileItem;

public class util {


	
	// nvl 함수
	public static boolean nvl(String msg){
		return msg == null || msg.trim().equals("")?true:false;
	}

	// 날짜를 클릭하면, 그날의 일정이 모두 보이게 하는 ad_callist.jsp로 이동시키는 함수
	public static String callist(int year, int month, int day,String hotelname){
		String s = "";
		
		s += String.format("<a href='%s?command=%s&hotelname=%s&year=%d&month=%d&day=%d'>", 
							"HotelControl","ad_caldetail",hotelname, year, month, day);
		s += String.format("%2d", day);
		s += "</a>";

		return s;
	}

	// 일정을 기입하기 위해서 pen이미지를 클릭하면, calwrite.jsp로 이동
	public static String showPen(int year, int month, int day){
		String s = "";
		String url = "calWrite.jsp";
		String image = "<img src='./image/pen.gif'>";
		
		s = String.format("<a href='%s?year=%d&month=%d&day=%d'>%s</a>", 
								url, year, month, day, image);
		return s;
	}

	// 1 -> 01		2018112
	public static String two(String msg){
		return msg.trim().length() < 2 ? "0"+msg : msg.trim(); 
	}

	// 각 날짜별로 테이블을 생성하는 함수
	public static String makeTable(int year, int month, int day, List<ReserveDto> list)
	{
		String s = "";
		String dates = (year + "") + two(month + "") + two(day + ""); // 20181001
		
		s += "<table>";
		s += "<col width='98' >";
		int w = 0;

		for(ReserveDto dto : list){
			if(dto.getCheckin().substring(0, 8).equals(dates) || dto.getCheckout().substring(0, 8).equals(dates)){
				
				s += "<tr bgcolor='pink'>";
				s += "<td>";
		
				s += "<font style='font-size: 13px; color:red'>";
				s += dot3(dto.getId());
				s += "</font>";
	
				s += "</td>";
				s += "</tr>";			
				
			
		}	
		}
		s += "</table>";
		
		return s;
	}

	// 제목이 너무 길면, 제목 + ...으로 처리하는 함수		철수와 광화문에서 식사 -> 철수와 광...
	public static String dot3(String msg){
		String s = "";
		if(msg.length() >= 10){
			s = msg.substring(0, 10);
			s += "...";
		}else{
			s = msg.trim();
		}
		return s;
	}
	
	
	public static MonthlysalesDto getsales(List<ReserveDto> dto, int price){
		List<MonthlysalesDto> mlist = new ArrayList<>();
		MonthlysalesDto mdto = new MonthlysalesDto();
		
		
		for(int i = 0; i < dto.size(); i++) {
			ReserveDto rdto = dto.get(i);
			System.out.println("체크인날짜확인:"+rdto.getCheckin().substring(4, 6));
			
			switch (rdto.getCheckin().substring(4, 6)) {
			case "01": mdto.setJan(mdto.getJan()+1);
			
			break;
			case "02": mdto.setFeb(mdto.getFeb()+1);
			break;
			case "03": mdto.setMar(mdto.getMar()+1);
			break;
			case "04": mdto.setApr(mdto.getApr()+1);
			break;
			case "05": mdto.setMay(mdto.getMay()+1);
			break;
			case "06": mdto.setJun(mdto.getJun()+1);
			break;
			case "07": mdto.setJul(mdto.getJul()+1);
			break;
			case "08": mdto.setAug(mdto.getAug()+1);
			break;
			case "09": mdto.setSep(mdto.getSep()+1);
			break;
			case "10": mdto.setOct(mdto.getOct()+1);
			break;
			case "11": mdto.setNov(mdto.getNov()+1);
			break;
			case "12": mdto.setDec(mdto.getDec()+1);
			break;
			
			}
			switch (rdto.getCheckout().substring(4, 6)) {
			case "01": mdto.setJan(mdto.getJan()+1);
			
			break;
			case "02": mdto.setFeb(mdto.getFeb()+1);
			break;
			case "03": mdto.setMar(mdto.getMar()+1);
			break;
			case "04": mdto.setApr(mdto.getApr()+1);
			break;
			case "05": mdto.setMay(mdto.getMay()+1);
			break;
			case "06": mdto.setJun(mdto.getJun()+1);
			break;
			case "07": mdto.setJul(mdto.getJul()+1);
			break;
			case "08": mdto.setAug(mdto.getAug()+1);
			break;
			case "09": mdto.setSep(mdto.getSep()+1);
			break;
			case "10": mdto.setOct(mdto.getOct()+1);
			break;
			case "11": mdto.setNov(mdto.getNov()+1);
			break;
			case "12": mdto.setDec(mdto.getDec()+1);
			break;
			
			}
			
		}
		mdto.setJan(mdto.getJan() * price);
		mdto.setFeb(mdto.getFeb() * price);
		mdto.setMar(mdto.getMar() * price);
		mdto.setApr(mdto.getApr() * price);
		mdto.setMay(mdto.getMay() * price);
		mdto.setJun(mdto.getJun() * price);
		mdto.setJul(mdto.getJul() * price);
		mdto.setAug(mdto.getAug() * price);
		mdto.setSep(mdto.getSep() * price);
		mdto.setOct(mdto.getOct() * price);
		mdto.setNov(mdto.getNov() * price);
		mdto.setDec(mdto.getDec() * price);
		return mdto;
	}
	
	
	
	public static scoreDto getScore(List<ReviewDto> list){
		scoreDto dto = new scoreDto();
		
		for(int i = 0; i < list.size(); i++) {
			ReviewDto rdto = list.get(i);
			
			
			switch (rdto.getScore()) {
			case 1: dto.setStar1(dto.getStar1()+1);
			break;
			case 2: dto.setStar2(dto.getStar2()+1);
			break;
			case 3: dto.setStar3(dto.getStar3()+1);
			break;
			case 4: dto.setStar4(dto.getStar4()+1);
			break;
			case 5: dto.setStar5(dto.getStar5()+1);
			break;
			
			}
			
		}
	
		return dto;
	}
	
	

}
