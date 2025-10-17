package dataStructure;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class EqClass {
	class Node {
		int data;
		Node link;
		public Node(int data, Node link) {
			this.data = data;
			this.link = link;
		}
	}
	
	private Node[] seq;
	private int N;
	
	public void loadPairs(String fileName) {
		try {
			Scanner sc = new Scanner(new File(fileName));
			N = sc.nextInt();
			seq = new Node[N];
			
			while (true) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				if (x == -1 && y == -1) break;
				
				seq[x] = new Node(y, seq[x]);
				seq[y] = new Node(x, seq[y]);
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void displayEqClass() {
		LinkedStack<Integer> stack = new LinkedStack<>();
		boolean[] visited = new boolean[N];
		
		int classNum = 0;
		for (int i=0; i<N; i++) {
			if (visited[i])	continue;
			visited[i]= true;  
			System.out.printf("클래스 %d { %d ", classNum++, i);
			stack.push(i);
			while (!stack.isEmpty()) {
				int x = stack.pop();
				for (Node ptr = seq[x]; ptr != null; ptr=ptr.link) {
					if (visited[ptr.data]) {continue;}
					System.out.print(ptr.data + " ");
					visited[ptr.data]= true; 
					stack.push(ptr.data);
				}
			}
			System.out.println("}");
		}
		
	}
	
	private void printList() {
		for (int i=0; i<N; i++) {
			System.out.print(i + " -> ");
			for (Node ptr = seq[i]; ptr != null; ptr = ptr.link) {
				System.out.print(ptr.data + " ");
			}
			System.out.println();
		}
	}
}
