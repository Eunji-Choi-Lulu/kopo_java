package midterm;

import java.util.Random;
import java.util.Scanner;

public class YearManager {

	Year[] years = new Year[10]; // 10년치 데이터를 저장할 배열 (2011~2020년)
	Month[] months = new Month[12];//12달치 데이터 
	Random random = new Random(); // 랜덤 객체 생성
	Scanner scanner = new Scanner(System.in);
	String[] monthName = {"1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"};

	public int baseTemp (int month) {
		if (month == 12 || month == 1 || month == 2) return 0;
		else if (month >= 3 && month <= 5) return 10;
		else if (month >= 6 && month <= 8) return 20;
		else return 10;
	}
	
	// 온도 생성 메서드
	public int temperature(int BaseTemp) {
		return BaseTemp + (random.nextInt(21) - 10); // 10도 기준 ±10도
	}
	
	//기상상태 : 50%(맑음), 30%(흐림), 비(20%)
	public String condition() {
		int percent = random.nextInt(100);
		
		if (percent < 50) {
			return "맑음";
		} 
		else if (percent < 80) {
			return "흐림";
		} 
		else {
			return "비";
		}
	}
	

	
	// 1년치 데이터 생성
	public void yearData() {
		
		for (int k = 0; k < 10; k++) {
			int yearNum = 2011 + k;       // 2011 ~ 2020
			Year year = new Year(yearNum); // 해당 연도 객체 생성
		
		// MonthManager에 있는 yearData메소드를 가지고와서 1년치 요약 (1월~12월의 데이터)를 이부분에 넣어야함 
			for (int i = 0; i < 12; i++) {
				
				int monthNumber = i + 1;
				int base = baseTemp(monthNumber);
				
				Month month = new Month(monthName[i], monthNumber, base);
	
				for (int j = 0; j < 30; j++) {
					
					int temp = temperature(base);
					String cond = condition();
					month.weather[j] = new Weather(monthNumber, j + 1, temp, cond);
					}
				year.setMonth(i, month); // 월 저장하는 부분 -> 검색 후 사용
				months[i] = month;
			}
			years[k] = year;
			}
		}

	
	// 사용자로부터 월/일 입력받아 날씨 출력
	public void weatherInput() {
		while (true) {
			System.out.print("검색하고 싶은 월을 입력하세요 (1~12, 종료는 0): ");
			int month = Integer.parseInt(scanner.nextLine());

			if (month == 0) {
				System.out.println("날씨 조회를 종료합니다.");
				break;
			}
			
			if (month < 1 || month > 12) {
			
				System.out.println("1~12 사이의 숫자를 입력해주세요.");
				continue;
			}

			System.out.print("검색하고 싶은 날짜를 입력하세요 (1~30): ");
			int day = Integer.parseInt(scanner.nextLine());
			
			if (day < 1 || day > 30) {
				
				System.out.println("1~30 사이의 숫자를 입력해주세요.");
				
				continue;
			}

			Weather w = months[month - 1].weather[day - 1];
			System.out.printf("[%d월 %d일 날씨 정보]\n", month, day);
			
			w.printWeather();
			
			System.out.println();
		}
	}
	// 10년치 전체 요약 보고서 출력
	public void tenYearReport() {
		for (int i = 0; i < 10; i++) {
			years[i].printYearSummary(); // 각 연도에 저장된 월별 요약 출력
		}
	}
}