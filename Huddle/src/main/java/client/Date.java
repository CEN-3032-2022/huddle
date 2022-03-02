package client;

public class Date {
	private int month;
	private int day;
	private int year;
	
	public Date() {
		month = -1;
		day = -1;
		year = -1;
	}
	
	public Date(int month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public String getMonthName(){
		
		String monthName = "invalid month";
		
		switch(month) {
			case 1: monthName = "January";
			break;
			
			case 2: monthName = "February";
			break;
			
			case 3: monthName = "March";
			break;
			
			case 4: monthName = "April";
			break;

			case 5: monthName = "May";
			break;

			case 6: monthName = "June";
			break;

			case 7: monthName = "July";
			break;

			case 8: monthName = "August";
			break;

			case 9: monthName = "September";
			break;

			case 10: monthName = "October";
			break;
			
			case 11: monthName = "November";
			break;

			case 12: monthName = "December";
			break;
		}
		
		return monthName;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public String toString() {
		String output = getMonth() + "/" + getDay() + "/" + getYear(); 
		
		return output;
	}
}
