package InLab2A.java;

import java.util.Arrays;
public class Stock {
	
	private int day;
	private int month;
	private int year;
	private String name;
	private int[] values = new int[7];
	
	public Stock() {
		day = 1;
		month = 1;
		year = 1;
		name = ""; 
		for(int i = 0; i < 7; i++) {
			values[i] = 0;
		}
	}
	
	public Stock(int dayIn, int monthIn, int yearIn, String nameIn, int[] valIn) {
		
		int prevDay = day;
		int prevMonth = month;
		int prevYear = year;
		
		if(monthIn >= 1 && monthIn <= 12) {
			switch (monthIn) {
			case 2:
				if (dayIn >= 1 && dayIn <= 28) {
					day = dayIn;
					month = monthIn;
				} else {
					System.out.println("Error.");
					day = prevDay;
					month = monthIn;
					return;
				}
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				if (dayIn >= 1 && dayIn <= 30) {
					day = dayIn;
					month = monthIn;
				}else {
					System.out.println("Error.");
					day = prevDay;
					month = monthIn;
					year = yearIn;
					return;
				}
				break;
			default:
				if(dayIn >= 1 && dayIn <= 31) {
					day = dayIn;
					month = monthIn;
				}else {
					System.out.println("Error.");
					day = prevDay;
					return;
				}
				break;
			}
		}else {
			System.out.println("Error. Invalid month");
			month = prevMonth;
			day = dayIn;
		}
		
		if(yearIn > 0) {
			year = yearIn;
		}else {
			System.out.println("Error. Invalid year");
			year = prevYear;
		}
		
		name = nameIn;
		
		if(valIn.length == 7){
			for(int i = 0; i < 7; i++) {
				if(valIn[i] > 0) {
					values = Arrays.copyOf(valIn, valIn.length);		
				}else {
					System.out.println("Error: negative value not allowed.");
					values[i] = 1;
				}
					
			}
		}else
			System.out.println("Error, length is too short.");
	}
	
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	public int getYear(){
		return year;
	}
	
	public String getName() {
		return name;
	}
	public int getValue(int ind) {
		if(ind >= 0 && ind <= 6) {
			return values[ind];
		}else {
			System.out.println("Error.");
			return -1;
		}

	}
	
	public void setName(String nameIn) {
		name = nameIn;
	}
	
	public void setValue(int val, int ind) {
		if(ind >= 0 && ind <= 6 && val > 0) {
			values[ind] = val;
		}else
			System.out.println("Error.");
	}
	
	public void printStock() {
		System.out.println(name + ": " + day+"."+month+"."+year + " values: \n"  + values[0] + ", " + values[1]+ ", " + values[2]+ ", " + values[3]+ ", " + values[4]+ ", " + values[5]+ ", " + values[6]);
	}
	
	public int getMean() {
		int sum = 0;
		for(int i = 0; i < 7; i++) {
			sum = sum + values[i];
		}
		int mean  = sum / 7;
		return mean;
	}
}
