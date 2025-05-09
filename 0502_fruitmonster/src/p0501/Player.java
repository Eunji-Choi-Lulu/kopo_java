package p0501;

/**
 * 플레이어와 관련된 정보를 포함하는 Player 클래스 
 * 사용자 이름, 체력, 점수, 잡은 몬스터의 인벤토리 정보
 */
public class Player {

	//사용자 이름, 체력, 점수 초기화
	String username; 
	int hp = 100; 
	int score = 0;
	
	//기본생성자
	Player(){
		this.username = "플레이어";
	}
	
	//사용자의 입력을 받아 생성하는 생성자
	Player(String username){
		this.username = username; 
	}
	
	//잡은 몬스터 이름을 저장할 인벤토리
	String[] inventory = new String[1000];
}
