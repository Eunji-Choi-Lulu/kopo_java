package midterm;

import java.util.Random;
import java.util.Scanner;

public class WeatherManager {
	
	Weather[] weather = new Weather[30]; //30일치 데이터  
	Random random = new Random(); //랜덤 객체  
	Scanner scanner = new Scanner(System.in);
	

	// 온도 생성 메서드
	public int temperature() {
		return 10 + (random.nextInt(21) - 10); // 10도 기준 ±10도
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
	
	// 날짜, 온도, 상태 조합하여 데이터 생성
	public void makeData () {
		
		for (int i = 0; i < 30; i++) {
			int month = i + 1; 
			int date = i + 1;
			int temp = temperature();
			String cond = condition();
			
			// 날씨 객체 생성 후 배열에 저장
			weather[i] = new Weather(month, date, temp, cond);
		}		 
	}

	// 사용자에게 날짜 입력받아 날씨 출력
	public void weatherInput () {
		while(true) {
			System.out.print("검색하고 싶은 날짜를 입력하세요 (1~30 중 입력. 종료시 0 입력): ");
			String input = scanner.nextLine();
			int date = Integer.parseInt(input);
			
			if (date == 0) {
				System.out.println("날씨 조회를 종료합니다.");
				break; // 입력 종료
			}

			if (date < 1 || date > 30) {
				System.out.println("잘못 입력하셨습니다. 1~30 사이의 숫자를 입력해주세요.");
				continue; // 다시 질문
			}

			// 올바른 날짜 입력 시 해당 날씨 출력
			weather[date - 1].printWeather();
		}
	}
}	
