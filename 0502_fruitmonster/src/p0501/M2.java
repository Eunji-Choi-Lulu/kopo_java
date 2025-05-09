package p0501;

import java.util.Random;

/**
 * M2 몬스터
 * 이름: 레몬, 점수: 20, 공격력: 10
 */
public class M2 extends Base {
	M2(){
		super("레몬", 20, 10);
	}

	@Override
	public boolean catchMonster() {
		
		Random r = new Random(); 
		int randNum = r.nextInt(100);
		
		//80% 확률로 몬스터 잡기 
		if (randNum < 80) {
			return true;
		} 
		
		//20% 확률로 실패 
		else {
			return false;
		}
	}
}
