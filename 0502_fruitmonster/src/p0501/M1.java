package p0501;

/**
 * 최약체 M1 몬스터
 * 이름: 포도, 점수: 10, 공격력: 0
 */
public class M1 extends Base {

	//생성자로 M1 속성 초기화
	M1(){
		super("포도", 10, 0);
	}
	
	@Override 
	public boolean catchMonster() {
		return true; 
	}
}
