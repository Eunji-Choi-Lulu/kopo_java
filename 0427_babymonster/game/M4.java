package game;

import java.util.Random;

// 유니크 몬스터 클래스 - Base 클래스를 상속
public class M4 extends Base{
	// 생성자 - 이름을 "승준", 점수는 100, 공격력은 10으로 설정
	M4() {
		super("승준", 100, 10);
	}
	
	// catchMonster 메소드 오버라이딩 - 40% 확률로 몬스터를 잡을 수 있음
	@Override
	public boolean catchMonster() {
		// Random 객체 생성
		Random r = new Random();
		
		// 0부터 99 사이의 랜덤 숫자 생성
		int randNumber = r.nextInt(100);
		
		// 40% 확률로 몬스터를 잡음 (0~39)
		if (randNumber < 40) {
			System.out.println("괴팍한 " + this.name + "(이)가 잡혔다!");
			return true;
		}
		
		// 60% 확률로 실패
		System.out.println("괴팍한 " + this.name + "(을)를 잡기 실패ㅋ");
		return false;
	}
}