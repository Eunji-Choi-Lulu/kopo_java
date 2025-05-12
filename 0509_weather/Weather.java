package midterm;

/*
 * 날짜, 온도, 기상상태 
 */

public class Weather {
	int month;
	int date;
	int temp;
	String condition; 
	
	public Weather(int month, int date, int temp, String condition){
		this.month = month; 
		this.date = date; 
		this.temp = temp; 
		this.condition = condition; 
	}
	
    public void printWeather() {
        System.out.printf("%d월 %d일: %d도, %s\n", month, date, temp, condition);
    }
}
