package p0520;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ScoreManager manager = new ScoreManager();  // ScoreManager 객체 생성

        while (true) {
            // 메뉴 출력
            System.out.println("\n=== 성적 관리 시스템 ===");
            System.out.println("1. 성적 데이터 입력");
            System.out.println("2. 이름으로 성적 조회");
            System.out.println("3. 전체 통계 출력");
            System.out.println("4. 프로그램 종료");
            System.out.print("메뉴를 선택하세요: ");

            String input = scanner.nextLine();  // 사용자 메뉴 입력

            if (input.equals("1")) {
                // 성적 입력
                System.out.print("이름: ");
                String name = scanner.nextLine();

                System.out.print("중간고사 점수: ");
                int midterm = Integer.parseInt(scanner.nextLine());

                System.out.print("기말고사 점수: ");
                int finalExam = Integer.parseInt(scanner.nextLine());

                manager.addScore(name, midterm, finalExam);
                System.out.println("입력이 완료되었습니다.");

            } else if (input.equals("2")) {
                // 이름으로 조회
                System.out.print("조회할 이름: ");
                String searchName = scanner.nextLine();

                Score result = manager.findScoreByName(searchName);
                if (result != null) {
                    result.printInfo();
                } else {
                    System.out.println("해당 이름의 데이터가 없습니다.");
                }

            } else if (input.equals("3")) {
                // 통계 출력
                manager.printStatistics();

            } else if (input.equals("4")) {
                // 종료
                System.out.println("프로그램을 종료합니다.");
                break;

            } else {
                // 잘못된 입력
                System.out.println("잘못된 입력입니다. 1~4번 중에서 선택하세요.");
            }
        }
    }
}