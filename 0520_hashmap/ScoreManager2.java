package p0520;

import java.util.HashMap;

public class ScoreManager2 {

    // 이름을 key로, Score2 객체를 value로 저장
    HashMap<String, Score2> map = new HashMap<>();

    // 데이터 추가
    public void addScore(String name, int mid, int fin) {
        map.put(name, new Score2(name, mid, fin));
    }

    // 이름으로 검색
    public Score2 findScoreByName(String name) {
        return map.get(name);  // key로 바로 접근
    }

    // 통계 출력
    public void printStatistics() {
        int count = map.size();
        int midSum = 0;
        int finalSum = 0;

        for (Score2 s : map.values()) {
            midSum += s.midterm;
            finalSum += s.finalExam;
        }

        System.out.println("총 입력된 데이터 수: " + count);
        System.out.println("중간고사 총점: " + midSum);
        System.out.println("기말고사 총점: " + finalSum);
        System.out.println("중간고사 평균: " + (double)midSum / count);
        System.out.println("기말고사 평균: " + (double)finalSum / count);

    }
}