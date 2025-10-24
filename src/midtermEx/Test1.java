package midtermEx;

import java.util.Scanner;

// EqClass 예제와 동일한 구조
public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("데이터 파일 이름? "); // data1.txt 입력
        String fileName = sc.next();
        sc.close();

        // 1. 학생이 구현할 클래스 생성
        Sorter algorithm = new Sorter(); 

        // 2. 파일 로드 (try-catch는 Sorter 안에 있어야 함)
        algorithm.loadData(fileName); 

        // 3. 정렬 수행 및 출력 (C의 selection_sort 호출)
        algorithm.runSort(); 
    }
}