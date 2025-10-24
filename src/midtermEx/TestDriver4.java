package midtermEx;
import java.util.Scanner;

public class TestDriver4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("미로 파일 이름? ");
        String fileName = sc.next();
        sc.close();
        
        Maze algorithm = new Maze();
        // 출구 좌표 (6, 8)을 예시로 줌
        algorithm.findPath(fileName, 6, 8); 
    }
}