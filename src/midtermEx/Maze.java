package midtermEx;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze {
	private static class Items {
		int row, col, dir;
		Items(int r, int c, int d) {
			row = r;
			col = c;
			dir = d;
		}
	}
	
	private static class Move {
		int r, c;
		Move(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	private int[][] maze;
	private int[][] mark;
	private Move[] move;
	
	private void loadMaze(String fileName) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fileName));
		
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		maze = new int[M+2][N+2];
		mark = new int[M+2][N+2];
		
		for (int i=0; i < M+2; i++) {
			maze[i][0] = 1;
			maze[i][N+1] = 1;
		}
		for (int j=0; j < N+2; j++) {
			maze[0][j] = 1;
			maze[M+1][j] = 1;
		}
		
		for (int i=1; i <= M; i++) {
			for (int j=1; j <= N; j++) {
				maze[i][j] = sc.nextInt();
			}
		}
		
		sc.close();
		
		move = new Move[8];
	}
    
}