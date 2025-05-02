package p0501;

import java.util.Random;

/**
 * M3 몬스터
 * 이름: 오렌지, 점수: 50, 공격력: 30
 */
public class M3 extends Base {
	M3(){
		super("오렌지", 50, 30);
	}
	
	@Override
	public boolean catchMonster(){
		
		Random r =  new Random(); 
		int randNum = r.nextInt(100);
		
		//60%확률로 몬스터 잡기 
		if (randNum < 60){
			return true; 
		}
		
		//40% 확률로 실패 
		else {
			return false;
		}
	}
}
