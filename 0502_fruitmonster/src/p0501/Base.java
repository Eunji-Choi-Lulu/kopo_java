package p0501;

/**
 * 몬스터의 기본 속성을 정의하는 클래스
 */
public abstract class Base extends java.lang.Object {
	String name; 
	int score; 
	int attack; 
	
	//기본 생성자 
	Base(){
		this.name = "기본";
	}
	
	//매개변수를 받는 생성자 
	Base(String name, int score, int attack){
		this.name = name; 
		this.score = score;
		this.attack = attack; 
	}
	
	//몬스터를 잡는 메소드 
	public boolean catchMonster() {
		return true; 
	}
}
