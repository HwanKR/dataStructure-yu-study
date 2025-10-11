package dataStructure;

public class LinkedStack<E> implements Stack<E> {
	SinglyLinkedList<E> list = new SinglyLinkedList<>();
	
	public LinkedStack() {
		list = new SinglyLinkedList<>();
	}
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public boolean isFull() { // 일반적으로 잘 일어나지 않는다는 가정.
		return false;
	}
	
	public void push(E e) {
		list.addFirst(e);
	}
	
	public E top() {
		if (isEmpty()) {
			return null;
		}
		return list.get(0);		
	}
	
	public E pop() {
		if (isEmpty()) {
			return null;
		}
		E item = top();
		list.remove(0); // O(1) 보장을 위해 remove(0) 사용. remove(item)은 최대 O(n)의 복잡도를 가짐.
		return item;
	}

	@Override
	public String toString() {
		return "LinkedStack: " + list + ", top = " + top(); 
	}
	
	
}
