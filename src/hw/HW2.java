// 22011991 전승환

package hw;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class HW2 {
	
	public static class Point {
		int x;
		int y;
				
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void simpleSearch(Point[] groupA, Point[] groupB) {
		int minDistance = Integer.MAX_VALUE;
		Point bestPointA = null;
		Point bestPointB = null;
		
		long startTime = System.nanoTime();
		
		for (Point pA : groupA) {
			for (Point pB : groupB) {
				int distance = Math.abs(pA.x - pB.x) + Math.abs(pA.y - pB.y); 
				
				if (distance < minDistance) {
					minDistance = distance;
					bestPointA = pA;
					bestPointB = pB;
				}
			}
		}
		
		long endTime = System.nanoTime();
		double elapsedTime = (endTime - startTime) / 1_000_000_000.0;
		
	    System.out.println("단순한 방법: 최단 거리 = " + minDistance +
                ", i=(" + bestPointA.x + "," + bestPointA.y +
                "), p=(" + bestPointB.x + "," + bestPointB.y + ")");
	    System.out.printf("실행 시간: %.2f 초\n", elapsedTime);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("파일 이름? ");
		String fileName = sc.nextLine();
		
		try {
			File file = new File(fileName);
			Scanner fileScanner = new Scanner(file);
			
			int n = fileScanner.nextInt();
			Point[] groupA = new Point[n];
			for (int i = 0; i < n; i++) {
			    int x = fileScanner.nextInt();       
			    int y = fileScanner.nextInt();       
			    groupA[i] = new Point(x, y);         
			}
			
			int m = fileScanner.nextInt();           
			Point[] groupB = new Point[m];           
			for (int i = 0; i < m; i++) {
			    int x = fileScanner.nextInt();       
			    int y = fileScanner.nextInt();       
			    groupB[i] = new Point(x, y);        
			}
			
			System.out.println(n + "개의 A 지점과 " + m + "개의 B 지점을 읽었습니다.");
			simpleSearch(groupA, groupB);
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}
		
		
	}
}
