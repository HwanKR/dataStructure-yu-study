// 22011991 전승환

package hw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class HW2 {

    // 좌표를 저장하는 클래스
	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// 점(Point)이 A그룹인지 B그룹인지 태그를 붙여주는 클래스
	public static class TaggedPoint {
		Point p;
		char type; // 'A' 또는 'B'

		public TaggedPoint(Point p, char type) {
			this.p = p;
			this.type = type;
		}
	}

	// 단순 탐색 메소드
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
		System.out.printf("실행 시간: %.4f 초\n", elapsedTime);
	}

	// 똑똑한 탐색(스위핑) 메소드
	public static void smartSearch(Point[] groupA, Point[] groupB) {
		System.out.println(); // 결과 구분을 위한 줄바꿈
		long startTime = System.nanoTime();

		// 1. A와 B 그룹을 하나의 배열로 합치기
		int n = groupA.length;
		int m = groupB.length;
		TaggedPoint[] allPoints = new TaggedPoint[n + m];
		for (int i = 0; i < n; i++) {
			allPoints[i] = new TaggedPoint(groupA[i], 'A');
		}
		for (int i = 0; i < m; i++) {
			allPoints[n + i] = new TaggedPoint(groupB[i], 'B');
		}

		// 2. 결과 저장을 위한 변수 초기화
		int minDistance = Integer.MAX_VALUE;
		Point bestPointA = null;
		Point bestPointB = null;

		// --- Pass 1: y 오름차순, (x_B + y_B) - (x_A + y_A) 계산 ---
		Arrays.sort(allPoints, (tp1, tp2) -> Integer.compare(tp1.p.y, tp2.p.y));

		int maxSumA = Integer.MIN_VALUE;
		Point candidateA1 = null; 

		for (TaggedPoint tp : allPoints) {
			if (tp.type == 'A') {
				// (x+y) 값이 최대인 A점을 찾아서 기억해둠
				if (tp.p.x + tp.p.y > maxSumA) {
					maxSumA = tp.p.x + tp.p.y;
					candidateA1 = tp.p;
				}
			} else { // type == 'B'
				// 이전에 A점이 나온 적이 있을 때만 계산
				if (candidateA1 != null) {
					int distance = (tp.p.x + tp.p.y) - maxSumA;
					if (distance < minDistance) {
						minDistance = distance;
						bestPointA = candidateA1;
						bestPointB = tp.p;
					}
				}
			}
		}

		// --- Pass 2: y 내림차순, (x_B - y_B) - (x_A - y_A) 계산 ---
		Arrays.sort(allPoints, (tp1, tp2) -> Integer.compare(tp2.p.y, tp1.p.y));

		int maxDiffA = Integer.MIN_VALUE;
		Point candidateA2 = null;

		for (TaggedPoint tp : allPoints) {
			if (tp.type == 'A') {
				// (x-y) 값이 최대인 A점을 찾아서 기억해둠
				if (tp.p.x - tp.p.y > maxDiffA) {
					maxDiffA = tp.p.x - tp.p.y;
					candidateA2 = tp.p;
				}
			} else { // type == 'B'
				if (candidateA2 != null) {
					int distance = (tp.p.x - tp.p.y) - maxDiffA;
					if (distance < minDistance) {
						minDistance = distance;
						bestPointA = candidateA2;
						bestPointB = tp.p;
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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("파일 이름? ");
		String fileName = sc.nextLine();
		sc.close();

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
			fileScanner.close();
			
			simpleSearch(groupA, groupB);
			smartSearch(groupA, groupB);

		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}
	}
}