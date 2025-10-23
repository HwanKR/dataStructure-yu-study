package midtermEx;

import java.util.Scanner;

public class TestDriver {

    public static void main(String[] args) {
        int[] arr;
        int N;
        Scanner sc = new Scanner(System.in);
        System.out.print("입력 데이터 수 N, N개의 데이터: ");
        N = sc.nextInt();
        arr = new int[N]; // N개의 데이터를 저장하는 배열 할당

        for (int i = 0; i < N; i++)
            arr[i] = sc.nextInt();

        printArr(arr);
        selectionSort(arr); // 문제: 선택 정렬로 배열을 정렬하며, 각 단계를 출력하는 함수
        
        sc.close();
    }
    
    public static void printArr(int[] arr) {
    	System.out.print("[");
    	for (int i=0; i < arr.length; i++) {
    		System.out.print(" " + arr[i] + " ");
    	}
    	System.out.print("]\n");
    }
    
    public static void selectionSort(int[] arr) {
    	for (int i=0; i < arr.length-1; i++) {
    		int min = i;
    		for (int k=0; k < arr.length; k++) {
    			if (arr[k] < arr[min]) {
    				min = k;
    			}    			
    		}
    		int temp = arr[i];
    		arr[i] = arr[min];
    		arr[min] = temp;
    	}
    	printArr(arr);
    }
}
