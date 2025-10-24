package midtermEx;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ListOps {
	class Node {
		Node data;
		Node link;
		public Node(Node d, Node l) {
			this.data = d;
			this.link = l;
		}
	}
	
	
	int[] arrA;
	int[] arrB;
	
	public void loadLists(String fileName) {
		try {
			Scanner sc = new Scanner(new File(fileName));
			int N = 0;
			for (int i=0; sc.nextInt()!=-1; i++) {
				arrA[i] = sc.nextInt();
				N++;
				System.out.print(arrA[i]);
			}
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
