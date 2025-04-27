package game;

import java.util.Random;

// 전설 몬스터 클래스 - Base 클래스를 상속
public class M5 extends Base{
	// 생성자 - 이름을 "영철", 점수는 999999, 공격력은 50으로 설정
	M5(){
		super("영철", 999999, 50);
	}
	
	// catchMonster 메소드 오버라이딩 - 10% 확률로 전설 몬스터를 잡을 수 있음
	@Override
	public boolean catchMonster() {
		// Random 객체 생성
		Random r = new Random();
		
		// 0부터 99 사이의 랜덤 숫자 생성
		int randNumber = r.nextInt(100);
		
		// 10% 확률로 몬스터를 잡음 (0~9)
		if (randNumber < 10) {
			System.out.println("전설의 교수님 " + this.name + "(이)가 잡혔다!");
			System.out.println("야호! 전설의 교수님 " + this.name + "(을)를 잡았다!");
			System.out.println("자바 졸업이다!!");
			return true;
		}
		
		// 90% 확률로 실패
		System.out.println("전설의 교수님 " + this.name + "의 모습이 보이지 않는다...");
		System.out.println("앗! 과제가 쏟아졌다.....");
		return false;
	}
}
