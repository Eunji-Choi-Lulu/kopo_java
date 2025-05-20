package p0520;

import java.util.ArrayList;

public class ScoreManager {

    ArrayList<Score> scoreList = new ArrayList<>();

    // 데이터 추가
    // .add를 통해서 ArrayList가 한칸씩 늘어남 
    public void addScore(String name, int mid, int fin) {
        scoreList.add(new Score(name, mid, fin)); 
    }

    // 이름으로 검색
    public Score findScoreByName(String name) {
        for (Score s : scoreList) {
            if (s.name.equals(name)) {  
                return s;
            }
        }
        return null;
    }

    // 통계 출력 
    public void printStatistics() {
        int count = scoreList.size();
        int midSum = 0;
        int finalSum = 0;

        for (Score s : scoreList) {
            midSum += s.midterm;
            finalSum += s.finalExam;
        }

        System.out.println("총 입력된 데이터 수: " + count);
        System.out.println("중간고사 총점: " + midSum);
        System.out.println("기말고사 총점: " + finalSum);
    }
}