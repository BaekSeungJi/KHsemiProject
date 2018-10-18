package model.hotel;

import java.util.List;

import dto.HotelDto;/*
import dto.MonthlysalesDto;*/
import dto.ReserveDto;

public interface iHotelManager {

	public boolean ad_HotelUpdate(String hotelname, String DESCRIPTION, int MAXPEOPLE, int PRICE, int HOTELPHONE);

	public HotelDto getHoteldetail(String hotelname);
	
	public List<String> getMonthlyChart(String hotelname);
	
	public String getPrice(String hotelname);
}
