package game;

// 기본 몬스터 클래스 - Base 클래스를 상속
public class M1 extends Base{
	// 생성자 - 이름을 "은지", 점수를 10, 공격력을 0으로 설정
	M1(){
		super("은지", 10, 0);
	}
	   
	// catchMonster 메소드 오버라이딩 - 무조건 true 반환 (100% 확률로 잡힘)
	@Override
	public boolean catchMonster() {
		System.out.println("순순한 " + this.name + "(이)가 순순히 잡혔다!");
		return true;
	}
}