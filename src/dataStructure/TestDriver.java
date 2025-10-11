package dataStructure;

public class TestDriver {
	public static void main(String[] args) {
//		SinglyLinkedList<String> list = new SinglyLinkedList<>();
//		
//		list.addFirst("A");
//		list.addFirst("B");
//		list.addFirst("C");
//		
//		list.addLast("D");
//		list.addLast("E");
//		list.addLast("F");
//		
//		
//		list.add(1,  "K");
//		list.add(0,  "X");
//		list.add(6,  "Y");
//		
//		System.out.println(list);
//		
//		list.remove(0);
//		list.remove("E");
//		System.out.println(list);
		
		Stack<Integer> stack = new LinkedStack<Integer>();
		
		stack.push(1);
		stack.push(3);
		stack.push(5);
		
		System.out.println(stack);
	}
}
