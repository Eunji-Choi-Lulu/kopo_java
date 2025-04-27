package game;

// 몬스터의 기본 클래스
public class Base {
	// 이름
	String name; 
	
	// 점수
	int score;
	
	// 공격력 수치
	int attack;
	
	// 기본 생성자
	Base() {
		this.name = "기본";
	}
	
	// 매개변수를 받는 생성자 - 이름, 점수, 공격력을 초기화
	Base(String name, int score, int attack) {
		this.name = name;
		this.score = score;
		this.attack = attack;
	}
	
	// 몬스터를 잡는 메소드 - 현재는 무조건 true를 반환 (기본 동작)
	public boolean catchMonster() {
		return true;
	}
}
