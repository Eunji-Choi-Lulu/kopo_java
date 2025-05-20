package p0520;


public class Score {

    String name;
    int midterm;
    int finalExam;

    public Score(String name, int midterm, int finalExam) {
        this.name = name;
        this.midterm = midterm;
        this.finalExam = finalExam;
    }

    public void printInfo() {
        System.out.println("이름: " + name);
        System.out.println("중간고사: " + midterm);
        System.out.println("기말고사: " + finalExam);
    }
}
