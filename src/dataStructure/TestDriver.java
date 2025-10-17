package dataStructure;

import java.util.Scanner;

public class TestDriver {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("파일 이름? ");
		String fileName = sc.next();
		sc.close();
		
		EqClass algorithm = new EqClass();
		algorithm.loadPairs(fileName);
		algorithm.displayEqClass();
	}
}
