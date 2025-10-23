package midtermEx;

import java.util.Scanner;

class Node {
    int data;
    Node next;
}

public class TestDriver3 {

    static Node makeList() {
        Scanner sc = new Scanner(System.in);
        Node start = null;
        System.out.print("연결 리스트에 추가할 수를 입력(마지막은 -1): ");
        while (true) {
            int data = sc.nextInt();
            if (data == -1) break;
            Node ptr = new Node();
            ptr.data = data;
            ptr.next = start;
            start = ptr;
        }
        return start;
    }

    static void printList(String msg, Node start) {
        System.out.print(msg + " [ ");
        for (Node p = start; p != null; p = p.next) {
            System.out.printf(" " + p.data + " ");
        }
        System.out.println("]");
    }

    static Node invert(Node start) {
        // 문제 1: 노드의 링크를 반대로 변경
        return start;
    }

    static Node sortMerge(Node a, Node b) {
        // 문제 2: 두 리스트를 오름차순으로 병합
        return a;
    }

    public static void main(String[] args) {
        Node A = makeList();
        Node B = makeList();
        printList("A =", A);
        printList("B =", B);

        A = invert(A);
        B = invert(B);
        printList("invert() 후의 A =", A);
        printList("invert() 후의 B =", B);

        Node C = sortMerge(A, B);
        printList("sort_merge(A, B)의 결과 =", C);
    }
}
