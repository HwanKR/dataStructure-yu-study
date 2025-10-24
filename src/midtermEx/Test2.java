package midtermEx;

import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("수식 파일 이름? "); // data2.txt 입력
        String fileName = sc.next();
        sc.close();

        // 1. 학생이 구현할 클래스 생성
        Expression algorithm = new Expression();

        // 2. 파일 처리 (try-catch는 Expression 안에 있어야 함)
        algorithm.processFile(fileName);
    }
}