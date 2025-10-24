package midtermEx;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestDriver {

    // 1. C의 struct/enum 대신 inner class 사용
    
    // (row, col, dir) 저장용 (Chap3.pdf의 Items)
    private static class Items {
        int row, col, dir;
        Items(int r, int c, int d) { row=r; col=c; dir=d; }
    }
    
    // 8방향 좌표 (Chap3.pdf의 move)
    private static class Move {
        int r, c;
        Move(int r, int c) { this.r = r; this.c = c; }
    }
    
    // 2. 필드 (지도, 방문기록, 나침반)
    private int[][] maze;
    private int[][] mark;
    private Move[] move;
    
    // 3. (핵심) 파일 로드 및 "경계벽" 구현
    private void loadMaze(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        
        int M = sc.nextInt(); // 6
        int N = sc.nextInt(); // 8
        
        // [핵심 트릭 1] 경계벽(padding)을 위해 (M+2) x (N+2) 크기
        maze = new int[M+2][N+2];
        mark = new int[M+2][N+2];
        
        // 1. 테두리 전체를 '1'(벽)로 채우기
        for (int i = 0; i < M+2; i++) {
            maze[i][0] = 1;      // 왼쪽 벽
            maze[i][N+1] = 1;    // 오른쪽 벽
        }
        for (int j = 0; j < N+2; j++) {
            maze[0][j] = 1;      // 윗쪽 벽
            maze[M+1][j] = 1;    // 아랫쪽 벽
        }
        
        // 2. 안쪽(1,1 부터 M,N 까지)을 파일 데이터로 채우기
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                maze[i][j] = sc.nextInt();
            }
        }
        
        sc.close();
        
        // 3. move 배열 초기화 (Chap3.pdf)
        move = new Move[8];
        move[0] = new Move(-1, 0); // 북
        move[1] = new Move(-1, 1); // 북동
        move[2] = new Move(0, 1);  // 동
        move[3] = new Move(1, 1);  // 남동
        move[4] = new Move(1, 0);  // 남
        move[5] = new Move(1, -1); // 남서
        move[6] = new Move(0, -1); // 서
        move[7] = new Move(-1, -1);// 북서
    }

    // 4. (핵심) 경로 탐색 알고리즘
    public void findPath(String fileName, int exitRow, int exitCol) {
        
        // 1. 파일 로드
        try {
            loadMaze(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
            return;
        }

        // 2. 스택 직접 구현 (ArrayStack 클래스 X)
        Items[] stack = new Items[maze.length * maze[0].length];
        int top = -1;
        
        // 3. 시작점 (1, 1) 방문 표시 및 push
        mark[1][1] = 1;
        stack[++top] = new Items(1, 1, 0); // (1,1)에서 0(북) 방향부터 시작

        // 4. 백트래킹 루프 (Chap3.pdf 로직)
        while (top != -1) { // 스택이 비어있지 않은 동안
            Items pos = stack[top--]; // [POP]
            
            while (pos.dir <= 7) { // 8방향 탐색
                int r = pos.row + move[pos.dir].r;
                int c = pos.col + move[pos.dir].c;
                
                // [성공] 출구 도달
                if (r == exitRow && c == exitCol) {
                    System.out.println("경로를 찾았습니다:");
                    // 스택에 쌓인 경로 출력
                    for (int i = 0; i <= top; i++) {
                        System.out.println("(" + stack[i].row + ", " + stack[i].col + ")");
                    }
                    System.out.println("(" + r + ", " + c + ")"); // 마지막 위치
                    return;
                }
                
                // [전진] 길이(0)고 방문 안 했(0)으면
                if (maze[r][c] == 0 && mark[r][c] == 0) {
                    mark[r][c] = 1; // 방문 표시
                    
                    // [PUSH 1] 현재 위치+다음방향 (백트래킹용)
                    pos.dir++;
                    stack[++top] = pos; 
                    
                    // 새 위치로 전진
                    pos = new Items(r, c, 0); // (r,c)에서 0(북) 방향부터 다시 시작
                } else {
                    // [다음 방향] 벽이거나 방문했으면
                    pos.dir++;
                }
            } // (8방향 탐색 끝)
        } // (백트래킹 루프 끝)
        
        System.out.println("경로를 찾을 수 없습니다.");
    }
}