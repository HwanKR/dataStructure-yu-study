package midtermEx;

import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("리스트 파일 이름? "); // data3.txt 입력
        String fileName = sc.next();
        sc.close();

        // 1. 학생이 구현할 클래스 생성
        ListOps algorithm = new ListOps();

        // 2. 파일 로드
        algorithm.loadLists(fileName);

        // 3. C의 main 로직 수행
//        algorithm.runOperations();
    }
}