package game;

//게임 사용자(플레이어)를 표현하는 클래스
public class User {
	// 사용자 이름
	String userName; 
	
	// 체력 (기본값 100)
	int hp = 100;
	
	// 사용자 점수 (기본값 0)
	int score = 0;
	
	// 잡은 몬스터 이름을 저장할 인벤토리 배열
	String[] inventory = new String[999999];
	
	// 기본 생성자 - 사용자 이름을 "사용자"로 설정
	User() {
		this.userName = "사용자";
	}
	
	// 사용자 이름을 지정해서 생성하는 생성자
	User(String userName) {
		this.userName = userName;
	}
}