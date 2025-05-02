package p0501;

import java.util.Random;

/**
 * M5 몬스터
 * 이름: 수박, 점수: 100, 공격력: 90
 */
public class M5 extends Base {
	M5(){
		super("수박", 100, 90);
	}
	
	@Override
	public boolean catchMonster(){
		
		Random r =  new Random(); 
		int randNum = r.nextInt(100);
		
		//20%확률로 몬스터 잡기 
		if (randNum < 20){
			return true; 
		}
		
		//80% 확률로 실패 
		else {
			return false;
		}
	}
}