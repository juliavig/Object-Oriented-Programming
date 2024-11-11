package InLab2A.java;

public class Activity {
	
	private int day;
	private int month;
	private int year;
	
	public Activity() {
		day = 1;
		month = 1;
		year = 1;
	}
	
	public Activity(int dayIn, int monthIn, int yearIn) {
		if (dayIn >= 1 && dayIn <= 31) {
			day = dayIn;
		}else {
			System.out.println("Error. Invalid day.");
			day = 1;
		}
		
		if (monthIn >=1 && monthIn <= 12) {
			month = monthIn;
		}else {
			System.out.println("Error. Invalid month.");
			month = 1;
		}
		
		if(yearIn > 0) {
			year = yearIn;
		}else {
			System.out.println("Error. Invalid year");
			year = 1;
		}
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
	
	public void setDate(int dayIn, int monthIn, int yearIn) {
		
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
		
	}
	
	public void printDate() {
		System.out.println(day + "." + month + "." + year);
	}
}

