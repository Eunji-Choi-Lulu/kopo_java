package p0501;

import java.util.Random;

/**
 * M4 몬스터
 * 이름: 메론, 점수: 80, 공격력: 50
 */
public class M4 extends Base {
	M4(){
		super("메론", 80, 50);
	}
	
	@Override
	public boolean catchMonster(){
		
		Random r =  new Random(); 
		int randNum = r.nextInt(100);
		
		//50%확률로 몬스터 잡기 
		if (randNum < 50){
			return true; 
		}
		
		//50% 확률로 실패 
		else {
			return false;
		}
	}
}