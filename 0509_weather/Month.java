package midterm;

/*
 * 월 이름, 번호, 30일 날씨 배열  
 */
public class Month {

	//각 달의 데이터를 배열로 저장해야됨
	String monthName; 
	int number; 
	int temperature; 
	
	Weather[] weather = new Weather[30];

	public Month (String monthName, int number, int temperature) {
		this.monthName = monthName;
		this.number = number;
		this.temperature = temperature;
	}
		
	//요약 메서드 
		public void report() {
			int maxTemp = weather[0].temp; // 처음 값을 기준으로 초기화
			int minTemp = weather[0].temp;

			int sunny = 0;
			int cloudy = 0;
			int rainy = 0;

			for (int i = 0; i < 30; i++) {
				
				int temp = weather[i].temp;
				String condition = weather[i].condition;

				// 최대/최소 온도 비교
				if (temp > maxTemp) maxTemp = temp;
				if (temp < minTemp) minTemp = temp;

				// 기상 상태 세기
				if (condition.equals("맑음")) sunny++;
				else if (condition.equals("흐림")) cloudy++;
				else if (condition.equals("비")) rainy++;
			}

			// 결과 출력
			System.out.println("[" + monthName + "]");
			
			System.out.println("최고 온도: " + maxTemp + "도");
			
			System.out.println("최저 온도: " + minTemp + "도");
			
			System.out.println("맑은 날: " + sunny + "일, 흐린 날: " + cloudy + "일, 비 온 날: " + rainy + "일\n");
		}	
}


