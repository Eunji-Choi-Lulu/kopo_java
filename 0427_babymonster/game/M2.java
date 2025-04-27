package game;

import java.util.Random;

// 일반 몬스터 클래스 - Base 클래스를 상속받음
public class M2 extends Base{	
	// 생성자 - 이름을 "성진", 점수는 30, 공격력은 1로 설정
    M2() {
        super("성진", 30, 1);
    }
    
    // catchMonster 메소드 오버라이딩 - 80% 확률로 몬스터를 잡을 수 있음
    @Override
    public boolean catchMonster() {
    	// Random 객체 생성
        Random r = new Random(); 
        
        // 0부터 99 사이의 랜덤 숫자 생성
        int randNumber = r.nextInt(100);
        
        // 80% 확률로 몬스터를 잡음 (0~79)
        if (randNumber < 80) {
            System.out.println("키 큰 " + this.name + "(이)가 잡혔다!");
            return true;
        }
        
        // 20% 확률로 실패
        System.out.println("키 큰 " + this.name + "(을)를 잡기 실패ㅋ 어릴 때 우유 좀 먹지...");
        return false;
    }
}
