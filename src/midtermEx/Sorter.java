package midtermEx;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Sorter {
	int[] arr;
	
	public void loadData(String fileName) {
		try {
			Scanner sc = new Scanner(new File(fileName));
			int n = sc.nextInt();
			arr = new int[n];
			
			for (int i=0; i < arr.length; i++) {
				arr[i] = sc.nextInt();
			}
			
			System.out.print("[ ");
			for (int i=0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.print("]");
			System.out.println();
			sc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void runSort() {
		for (int i=0; i < arr.length-1; i++) {
			int min = i;
			for (int k=i+1; k < arr.length; k++) {
				if (arr[k] < arr[min]) {
					min = k;
				}
			}
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}
		System.out.print("[ ");
		for (int i=0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.print("]");
	}
}
