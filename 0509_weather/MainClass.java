package midterm;

/*
 * 실행하는 main 클래스 
 */
public class MainClass {

	public static void main(String[] args) {
		//WeatherManager를 돌려야 함 
//		WeatherManager wm = new WeatherManager();
//		wm.makeData();
//		wm.weatherInput();
		
//		MonthManager mm = new MonthManager (); 
//		mm.yearData();
//		mm.yearReport();
//		mm.weatherInput();
//		
		YearManager yy = new YearManager (); 
		yy.yearData();
		yy.tenYearReport();
		yy.weatherInput();
	}
}
