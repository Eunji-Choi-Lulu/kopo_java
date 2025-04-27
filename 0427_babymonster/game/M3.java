package game;

import java.util.Random;

// 레어 몬스터 클래스 - Base 클래스를 상속
public class M3 extends Base{
	// 생성자 - 이름을 "주영", 점수는 50, 공격력은 5로 설정
	M3() {
      super("주영", 50, 5);
	}
	
	// catchMonster 메소드 오버라이딩 - 60% 확률로 몬스터를 잡을 수 있음
	@Override
	public boolean catchMonster() {
		// Random 객체 생성
		Random r = new Random();
		
		// 0부터 99 사이의 랜덤 숫자 생성
		int randNumber = r.nextInt(100);
		
		// 60% 확률로 몬스터를 잡음 (0~59)
		if (randNumber < 60) {
			System.out.println(this.name + "(이)가 잡혔다!");
			return true;
        }
		
		// 40% 확률로 실패
		System.out.println(this.name + "(을)를 잡기 실패ㅋ 어려운 상대였다...");
		return false;
	}
}
