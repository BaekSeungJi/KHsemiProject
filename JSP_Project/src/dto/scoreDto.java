package dto;

public class scoreDto {

	private int star1 = 0;
	private int star2 = 0;
	private int star3 = 0;
	private int star4 = 0;
	private int star5 = 0;
	
	public scoreDto() {
		// TODO Auto-generated constructor stub
	}

	public int getStar1() {
		return star1;
	}

	public void setStar1(int star1) {
		this.star1 = star1;
	}

	public int getStar2() {
		return star2;
	}

	public void setStar2(int star2) {
		this.star2 = star2;
	}

	public int getStar3() {
		return star3;
	}

	public void setStar3(int star3) {
		this.star3 = star3;
	}

	public int getStar4() {
		return star4;
	}

	public void setStar4(int star4) {
		this.star4 = star4;
	}

	public int getStar5() {
		return star5;
	}

	public void setStar5(int star5) {
		this.star5 = star5;
	}

	public scoreDto(int star1, int star2, int star3, int star4, int star5) {
		super();
		this.star1 = star1;
		this.star2 = star2;
		this.star3 = star3;
		this.star4 = star4;
		this.star5 = star5;
	}
}
