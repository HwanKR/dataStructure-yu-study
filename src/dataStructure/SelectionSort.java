package dataStructure;

public class SelectionSort {
	public void sort(int[] A) {
		for (int i=0; i<A.length-1; i++) {
			int min = i;
			for (int j=i+1; j<A.length; j++) {
				if (A[j] < A[min]) {
					min = j;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		SelectionSort driver = new SelectionSort();
		int [] input = {10, 4, 5, 2, 1, 8, 3, 6};
		driver.sort(input);
		for (int i=0; i<input.length; i++) {
			System.out.println(input[i] + "");
		}
		System.out.println(input[input.length]);
	}
}
