package midtermEx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ListOps {

    /**
     * [문제 3.1]
     * C의 'struct node'에 해당
     * (이 inner class는 수정할 필요 없음)
     */
    static class Node {
        int data;
        Node next;
        Node(int data, Node next) { this.data = data; this.next = next; }
    }
    
    // 리스트 A, B를 저장할 필드
    private Node A; 
    private Node B;

    /**
     * [문제 3.2]
     * fileName의 데이터를 읽어 A, B 리스트에 저장
     * (EqClass의 loadPairs에 해당)
     */
    public void loadLists(String fileName) {
        // TODO: (완성됨)
        try {
            Scanner fileSc = new Scanner(new File(fileName));
            
            // 1. 파일 스캐너를 makeList 헬퍼에 전달하여 A 로드
            A = makeList(fileSc); 
            // 2. 이어서 B 로드
            B = makeList(fileSc);
            
            fileSc.close(); // 3. 파일 스캐너 닫기
            
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다: " + fileName);
            e.printStackTrace();
        }
    }

    /**
     * [문제 3.3]
     * C의 main 로직 수행 (invert, sortMerge 호출)
     * (EqClass의 displayEqClass에 해당)
     */
    public void runOperations() {
        if (A == null && B == null) {
            System.out.println("데이터가 로드되지 않았습니다.");
            return;
        }

        printList("A =", A);
        printList("B =", B);

        // 문제 3.1 호출
        A = invert(A); 
        B = invert(B);
        printList("invert() 후의 A =", A);
        printList("invert() 후의 B =", B);

        // 문제 3.2 호출
        Node C = sortMerge(A, B); 
        printList("sort_merge(A, B)의 결과 =", C);
    }
    
    // --- 아래 헬퍼 함수 및 문제 함수들을 완성해야 함 ---

    /**
     * 헬퍼 함수 (C의 make_list)
     * (주의: C와 달리, 파일 스캐너 'sc'를 전달받음)
     */
    private Node makeList(Scanner sc) {
        Node start = null;
        // TODO: (완성됨)
        // (C의 make_list 로직과 동일 - '앞에 추가' 방식)
        while(sc.hasNextInt()) { // 정수가 있는 동안
            int data = sc.nextInt();
            if (data == -1) break; // -1을 만나면 이 리스트 종료
            start = new Node(data, start); // LIFO (스택) 방식으로 앞에 추가
        }
        return start;
    }

    /**
     * 헬퍼 함수 (C의 print_list)
     */
    private void printList(String msg, Node start) {
        // TODO: (완성됨) (C의 print_list와 동일)
        System.out.print(msg + " [ ");
        for (Node p = start; p != null; p = p.next) {
            System.out.print(p.data + " ");
        }
        System.out.println("]");
    }

    /**
     * [문제 3.1] C의 invert 함수
     * (Chap4.pdf 3-pointer 알고리즘)
     */
    private Node invert(Node head) {
        // TODO: (완성됨)
        // head가 C 코드의 lead 포인터 역할을 함
        Node middle = null;
        Node tail = null;

        while (head != null) {
            tail = middle;      // tail을 한 칸 이동
            middle = head;      // middle을 한 칸 이동
            head = head.next;   // head(lead)를 한 칸 이동
            middle.next = tail; // [핵심] middle의 링크를 뒤(tail)로 돌림
        }
        
        // 루프가 끝나면 middle이 새로운 리스트의 헤드가 됨
        return middle;
    }

    /**
     * [문제 3.2] C의 sort_merge 함수
     * (Chap4.pdf 다항식 덧셈 로직 응용, 'new Node' 없이)
     */
    private Node sortMerge(Node a, Node b) {
        // TODO: (완성됨) - 'new Node' 없는 '재연결(Re-wiring)' 버전

        // 1. A나 B 둘 중 하나가 비어있으면, 다른 하나를 반환
        if (a == null) return b;
        if (b == null) return a;

        Node cHead = null; // 결과 리스트(C)의 헤드
        Node cLast = null; // 결과 리스트(C)의 마지막 노드 (추가용 포인터)

        // 2. [초기화] cHead와 cLast를 (a, b 중) 더 작은 노드로 설정
        if (a.data <= b.data) { // (같을 때 a를 먼저 넣어 안정성(stable) 보장)
            cHead = a;
            cLast = a;
            a = a.next; // a는 다음 노드로 이동
        } else {
            cHead = b;
            cLast = b;
            b = b.next; // b는 다음 노드로 이동
        }

        // 3. [병합] a와 b 둘 다 노드가 남아있는 동안 반복
        while (a != null && b != null) {
            if (a.data <= b.data) {
                // a가 더 작거나 같음: a 노드를 cLast의 뒤에 엮음
                cLast.next = a; // [핵심] new Node 아님
                cLast = a;      // cLast를 방금 붙인 a로 이동
                a = a.next;     // a는 다음 노드로 이동
            } else {
                // b가 더 작음: b 노드를 cLast의 뒤에 엮음
                cLast.next = b; // [핵심] new Node 아님
                cLast = b;      // cLast를 방금 붙인 b로 이동
                b = b.next;     // b는 다음 노드로 이동
            }
        }

        // 4. [마무리] 둘 중 하나가 끝났으면, 남은 리스트를 cLast 뒤에 통째로 붙임
        if (a == null) {
            cLast.next = b;
        } else { // b == null
            cLast.next = a;
        }

        // 5. 결과 리스트의 헤드 반환
        return cHead;
    }
}