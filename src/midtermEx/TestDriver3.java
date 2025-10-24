package midtermEx;

import java.util.Scanner;

public class TestDriver3 {

    // C의 struct node와 동일한 최소 정의
    static class Node {
        int data;
        Node next;
        Node(int d, Node n) { data = d; next = n; }
    }

    // C의 make_list()와 동일: 앞에 붙이기(push-front), -1이면 종료
    static Node makeList(Scanner sc) {
        System.out.print("연결 리스트에 추가할 수를 입력(마지막은 -1): ");
        Node start = null;
        while (true) {
            int x = sc.nextInt();
            if (x == -1) break;
            start = new Node(x, start); // 앞에 붙임(입력 순서가 역순이 됨)
        }
        return start;
    }

    // C의 print_list() 느낌 유지. 주소 하위바이트는 identityHashCode로 흉내
    static void printList(String msg, Node start) {
        System.out.print(msg + " [ ");
        for (Node p = start; p != null; p = p.next) {
            int lowByte = System.identityHashCode(p) & 0xff;
            System.out.printf("%2d(%02x) ", p.data, lowByte);
        }
        System.out.println("]");
    }

    static Node invert(Node start) {
    	Node lead = start, middle = null, tail;
    	while (lead != null) {
    		tail = middle;
    		middle = lead;
    		lead = lead.next;
    		middle.next = lead;
    	}
    	return middle;
    }
    
    static Node sortMerge(Node a, Node b) {
    	if (a == null) return b;
    	if (b == null) return a;
    	
    	Node head, tail;
    	
    	if (a.data <= b.data) {
    		head = tail = new Node(a.data, null);
    		a = a.next;
    	} else {
    		head = tail = new Node(b.data, null);
    		b = b.next;
    	}
    	
    	while (a != null && b != null) {
    		if (a.data <= b.data) {
    			tail.next = new Node(a.data, null);
    			tail = tail.next;
    			a = a.next;
    		} else {
    			tail.next = new Node(b.data, null);
    			tail = tail.next;
    			b = b.next;
    		}
    	}
    	
    	if (a != null) {
    		tail.next = a;
    	} else {
    		tail.next = b;
    	}
    	
    	return head;
    } 

    // C의 main() 흐름과 동일하게 구성
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Node A = makeList(sc);
        Node B = makeList(sc);
        printList("A =", A);
        printList("B =", B);

        // 문제 1
        A = invert(A);
        B = invert(B);
        printList("invert() 후의 A =", A);
        printList("invert() 후의 B =", B);

        // 문제 2
        Node C = sortMerge(A, B);
        printList("sort_merge(A, B)의 결과 =", C);

        sc.close();
    }
}
