package dto;

import java.io.Serializable;

public class ReserveTableDto implements Serializable {

	private String id;
	private String name;
	private String email;
	private String phone;
	private String checkin;
	private String checkout;
	private int blacklist;
	
	public ReserveTableDto(String id, String name, String email, String phone, String checkin, String checkout,
			int blacklist) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.checkin = checkin;
		this.checkout = checkout;
		this.blacklist = blacklist;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public int getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(int blacklist) {
		this.blacklist = blacklist;
	}
	
	
}