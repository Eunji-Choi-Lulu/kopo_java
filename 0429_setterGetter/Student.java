package p0429;

public class Student {

	String name;
	int middleScore; 
	int finalScore; 
	String phone; 
	String address; 
	
	//생성자1. 아무것도 안들어왔을 때 
	Student(){
		
	}
	
	//생성자2. 이름만 입력받았을 때 
	Student(String name){
		this.name = name;
	}
	
	Student(String name, int middleScore, int finalScore){
		this.name = name;
		this.middleScore = middleScore; 
		this.finalScore =finalScore; 
	}
	
	@Override
	public String toString() {
		return this.name + "(" + this.middleScore + "," + this.finalScore +")";
	}
	
	
	//캡슐화, 고전적인 java에서는 이것이 기본이였음
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name; 
	}
	
	public void setMiddleScore(int score) {
		this.middleScore = score; 
	}
	
	public int getMiddleScore() {
		return this.middleScore; 
	}
	
	public void setFinalScore(int score) {
		this.finalScore = score;
	}
	
	public int getFinalScore() {
		return this.finalScore;
	}
	
	public void setPhone(String phone) {
		this.phone = phone; 
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return this.address; 
	}
}
	
