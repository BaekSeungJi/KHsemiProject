package dto;

import java.io.Serializable;

public class MonthlysalesDto implements Serializable{

	private int Jan = 0;
	private int Feb= 0;
	private int Mar= 0;
	private int Apr= 0;
	private int May= 0;
	private int Jun= 0;
	private int Jul= 0;
	private int Aug= 0;
	private int Sep= 0;
	private int Oct= 0;
	private int Nov= 0;
	private int Dec= 0;
	
	public MonthlysalesDto() {

	}

	public MonthlysalesDto(int jan, int feb, int mar, int apr, int may, int jun, int jul, int aug, int sep, int oct,
			int nov, int dec) {
		super();
		Jan = jan;
		Feb = feb;
		Mar = mar;
		Apr = apr;
		May = may;
		Jun = jun;
		Jul = jul;
		Aug = aug;
		Sep = sep;
		Oct = oct;
		Nov = nov;
		Dec = dec;
	}

	public int getJan() {
		return Jan;
	}

	public void setJan(int jan) {
		Jan = jan;
	}

	public int getFeb() {
		return Feb;
	}

	public void setFeb(int feb) {
		Feb = feb;
	}

	public int getMar() {
		return Mar;
	}

	public void setMar(int mar) {
		Mar = mar;
	}

	public int getApr() {
		return Apr;
	}

	public void setApr(int apr) {
		Apr = apr;
	}

	public int getMay() {
		return May;
	}

	public void setMay(int may) {
		May = may;
	}

	public int getJun() {
		return Jun;
	}

	public void setJun(int jun) {
		Jun = jun;
	}

	public int getJul() {
		return Jul;
	}

	public void setJul(int jul) {
		Jul = jul;
	}

	public int getAug() {
		return Aug;
	}

	public void setAug(int aug) {
		Aug = aug;
	}

	public int getSep() {
		return Sep;
	}

	public void setSep(int sep) {
		Sep = sep;
	}

	public int getOct() {
		return Oct;
	}

	public void setOct(int oct) {
		Oct = oct;
	}

	public int getNov() {
		return Nov;
	}

	public void setNov(int nov) {
		Nov = nov;
	}

	public int getDec() {
		return Dec;
	}

	public void setDec(int dec) {
		Dec = dec;
	}


}
