// 22011991 전승환
package hw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class HW2 {
	public static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static class GroupedPoint {
		Point p;
		char group;
		
		public GroupedPoint(Point p, char group) {
			this.p = p;
			this.group = group;
		}
	}
	
	public static void smartSearch(Point[] groupA, Point[] groupB) {
		long startTime = System.nanoTime();
		
		int n = groupA.length;
		int m = groupB.length;
		GroupedPoint[] allPoints = new GroupedPoint[n+m];
		for (int i=0; i<n; i++) { allPoints[i] = new GroupedPoint(groupA[i], 'A'); }
		for (int i=0; i<m; i++) { allPoints[i+n] = new GroupedPoint(groupB[i], 'B'); }
		
		int minDistance = Integer.MAX_VALUE;
		Point bestPointA = null;
		Point bestPointB = null;
		
		Arrays.sort(allPoints, (a, b) -> Integer.compare(a.p.y, b.p.y));
		
		int maxSumA = Integer.MIN_VALUE;
		Point candidateA1 = null;
		
		for (GroupedPoint gp : allPoints) {
			if (gp.group == 'A') {
				if (gp.p.x + gp.p.y > maxSumA) {
					maxSumA = gp.p.x + gp.p.y;
					candidateA1 = gp.p;
				}
			} else {
				if (candidateA1 != null) {
					int distance = (gp.p.x + gp.p.y)- maxSumA;
					if (distance < minDistance) {
						minDistance = distance;
						bestPointA = candidateA1;
						bestPointB = gp.p;
					}
				}
			}
		}
		
		int maxDiffA = Integer.MIN_VALUE;
		Point candidateA2 = null;
		
		for (int i=allPoints.length-1; i>= 0; i--) {
			GroupedPoint gp = allPoints[i];
			
			if (gp.group == 'A') {
				if (gp.p.x - gp.p.y > maxDiffA) {
					maxDiffA = gp.p.x - gp.p.y;
					candidateA2 = gp.p;
				}
			} else {
				if (candidateA2 != null) {
					int distance = (gp.p.x - gp.p.y) - maxDiffA;
					if (distance < minDistance) {
						minDistance = distance;
						bestPointA = candidateA2;
						bestPointB = gp.p;
					}
				}
			}
		}
		
		long endTime = System.nanoTime();
		double elapsedTime = (endTime - startTime) / 1_000_000_000.0;
		
		System.out.println("똑똑한 방법: 최단 거리 = " + minDistance +
				", i=(" + bestPointA.x + "," + bestPointA.y +
				"), p=(" + bestPointB.x + "," + bestPointB.y + ")");
		System.out.printf("실행 시간: %.4f 초\n", elapsedTime);
	}
	
	public static void simpleSearch(Point[] groupA, Point[] groupB) {
		int minDistance = Integer.MAX_VALUE;
		Point bestPointA = null;
		Point bestPointB = null;
		 
		long startTime = System.nanoTime();
		
		for (Point pA : groupA) {
			for (Point pB : groupB) {
				int distance = Math.abs(pB.x-pA.x) + Math.abs(pB.y - pA.y);
				
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
		System.out.printf("실행 시간: %.4f 초\n", elapsedTime);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("파일 이름 : ");
		String fileName = sc.nextLine();
		
		try {
			File file = new File(fileName);
			Scanner fileScanner = new Scanner(file);
			
			int n = fileScanner.nextInt();
			Point[] groupA = new Point[n];
			for (int i=0; i<n; i++) {
				int x = fileScanner.nextInt();
				int y = fileScanner.nextInt();
				groupA[i] = new Point(x, y);				
			}
			
			int m = fileScanner.nextInt();
			Point[] groupB = new Point[m];
			for (int i=0; i<m; i++) {
				int x = fileScanner.nextInt();
				int y = fileScanner.nextInt();
				groupB[i] = new Point(x, y);
			}
			
			fileScanner.close();
			
			simpleSearch(groupA, groupB);
			smartSearch(groupA, groupB);
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}
		
		
	}
}

