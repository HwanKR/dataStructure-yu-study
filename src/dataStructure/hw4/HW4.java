// 22011991 전승환
package dataStructure.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HW4 {
    static class Node {
        int digit;
        Node next;

        Node(int digit) {
            this.digit = digit;
            this.next = null;
        }
    }

    public static void main(String[] args) {
    	Scanner fileScanner = null;
        try {
            Scanner keyboardScanner = new Scanner(System.in);
            System.out.print("파일 이름? ");
            String filename = keyboardScanner.nextLine();
            
            fileScanner = new Scanner(new File(filename));

            String numStr1 = fileScanner.nextLine();
            String numStr2 = fileScanner.nextLine();

            Node list1 = stringToReverseList(numStr1);
            Node list2 = stringToReverseList(numStr2);

            Node sumResult = add(list1, list2);
 
            Node productResult = multiply(list1, list2);

            System.out.println("첫번째 수 = " + numStr1);
            System.out.println("두번째 수 = " + numStr2);
            
            System.out.print("두 수의 합 = ");
            printList(sumResult);
            
            System.out.print("두 수의 곱 = ");
            printList(productResult);

            keyboardScanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다: " + e.getMessage());
        } finally {	
            if (fileScanner != null) {
                fileScanner.close();
            }
        }
    }

    public static Node stringToReverseList(String numStr) {
        Node head = null;
        for (char c : numStr.toCharArray()) {
            int digit = c - '0';
            Node newNode = new Node(digit);

            newNode.next = head;
            head = newNode;
        }
        return head;
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("0"); 
            return;
        }
        
        printListRecursive(head);
        System.out.println();
    }
    
    private static void printListRecursive(Node node) {
        if (node == null) {
            return;
        }
        printListRecursive(node.next);
        
        System.out.print(node.digit);
    }
    
    public static Node add(Node head1, Node head2) {
        Node resultHead = null; 
        Node resultTail = null;
        int carry = 0; 

        Node p1 = head1;
        Node p2 = head2;

        while (p1 != null || p2 != null || carry != 0) {
            int d1 = (p1 != null) ? p1.digit : 0;
            int d2 = (p2 != null) ? p2.digit : 0;

            int sum = d1 + d2 + carry;
            
            int newDigit = sum % 10;

            carry = sum / 10;
            
            Node newNode = new Node(newDigit);

            if (resultHead == null) {
                resultHead = newNode;
                resultTail = newNode;
            } else {
                resultTail.next = newNode;
                resultTail = newNode;
            }

            if (p1 != null) p1 = p1.next;
            if (p2 != null) p2 = p2.next;
        }
        
        return resultHead;
    }

    public static Node multiply(Node head1, Node head2) {
        if (isZero(head1) || isZero(head2)) {
            return new Node(0); 
        }

        Node totalProduct = new Node(0); 
        Node p2 = head2; 
        int place = 0;   

        while (p2 != null) {
            Node partialProduct = multiplyOneDigit(head1, p2.digit);

            Node shiftedProduct = partialProduct;
            if (place > 0) {
                shiftedProduct = shiftLeft(partialProduct, place);
            }

            totalProduct = add(totalProduct, shiftedProduct);

            p2 = p2.next;
            place++;
        }

        return totalProduct;
    }

    private static Node multiplyOneDigit(Node head, int multiplier) {
        if (multiplier == 0) {
            return new Node(0);
        }

        Node resultHead = null;
        Node resultTail = null;
        int carry = 0;
        Node p = head;

        while (p != null || carry != 0) {
            int d = (p != null) ? p.digit : 0;
            
            int product = (d * multiplier) + carry;
            
            int newDigit = product % 10;
            carry = product / 10;

            Node newNode = new Node(newDigit);
            
            if (resultHead == null) {
                resultHead = newNode;
                resultTail = newNode;
            } else {
                resultTail.next = newNode;
                resultTail = newNode;
            }

            if (p != null) p = p.next;
        }

        return resultHead;
    }
    
    private static Node shiftLeft(Node head, int n) {
        if (isZero(head)) return head;

        Node newHead = head;
        for (int i = 0; i < n; i++) {
            Node zeroNode = new Node(0);
            zeroNode.next = newHead;
            newHead = zeroNode;
        }
        return newHead;
    }

    private static boolean isZero(Node head) {
    	return head == null || (head.digit == 0 && head.next == null);
    }
}