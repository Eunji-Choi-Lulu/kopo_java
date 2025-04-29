package p0429;

import java.util.Arrays;

public class MainClass {


	public static void main(String[] args) {

		Student s = new Student(); 
		Student s1 =  new Student("홍길동");
		Student s2 = new Student("둘리", 800, 1000);
		
		System.out.println(s);
		System.out.println(s1);
		System.out.println(s2);
		
		
		Student[] student = {
				new Student("하니", 200, 300), 
				new Student("나예리", 900, 100),
				new Student("강감찬", 1200, 1300)};
				
		System.out.println(Arrays.toString(student));
		
		s.setPhone("010-0000-0000");
		s.setAddress("서울 서초구");
		System.out.println(s.getAddress());
		
	}
}		