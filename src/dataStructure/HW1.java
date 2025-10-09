// 22011991 전승환
package dataStructure;

import java.util.HashSet;
import java.util.Scanner;

public class HW1 {
	public boolean isPrime(long n) {
		if (n < 2) {return false;}
		for (long i=2; i<=Math.sqrt(n); i++) {
			if (n%i == 0) {return false;}
		}
		return true;
	}
	
	public void findPermutations(String prefix, String remaining, HashSet<Long> numberSet) {
		if (!prefix.isEmpty()) {
			numberSet.add(Long.parseLong(prefix));
		}
		
		for (int i=0; i<remaining.length(); i++) {
			String newPrefix = prefix + remaining.charAt(i);
			String newRemaining = remaining.substring(0, i) + remaining.substring(i+1);
			findPermutations(newPrefix, newRemaining, numberSet);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("입력 문자열: ");
		String numbers = sc.nextLine();
		
//		String numbers = "0123456789";
		HW1 hw = new HW1();
		
		for (int k=4; k<=10; k++) {
			String S = numbers.substring(0, k);
			HashSet<Long> numberSet = new HashSet<>();
			
			long start = System.currentTimeMillis();
			hw.findPermutations("", S, numberSet);
			int primeCount = 0;
			for (Long num: numberSet) {
				if (hw.isPrime(num)) {
					primeCount++;
				}
			}
			long end = System.currentTimeMillis();
			double time = (end - start)/1000.0;
			System.out.printf("문자열 = %s, 소수의 수 = %d, 실행 시간 = %.2f\n", S, primeCount, time);
		}
	}
}