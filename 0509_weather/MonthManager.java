package midterm;

import java.util.Random;
import java.util.Scanner;

public class MonthManager {
	
	Weather[] weather = new Weather[30]; //30일치 데이터  
	Month[] months = new Month[12];//12달치 데이터 
	Random random = new Random(); //랜덤 객체  
	Scanner scanner = new Scanner(System.in);
	
	
	
	String[] monthName = {"1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"};

	public int baseTemp(int month) {
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
	
	// 1년치 월별 날씨 데이터 생성
	public void yearData() {
		for (int i = 0; i < 12; i++) {
			
			int monthNumber = i + 1;
			int base = baseTemp(monthNumber);
			
			Month month = new Month(monthName[i], monthNumber, base);

			for (int j = 0; j < 30; j++) {
				
				int temp = temperature(base);
				String cond = condition();
				
				month.weather[j] = new Weather(monthNumber, j + 1, temp, cond);
			}
			months[i] = month;
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
	
	// 모든 월의 요약 보고서 출력
	public void yearReport() {
		
		System.out.println("1년치 월별 요약");
		for (int i = 0; i < 12; i++) {
			months[i].report();
		}
	}
	
	
}
