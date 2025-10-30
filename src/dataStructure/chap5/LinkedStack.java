package dataStructure.chap5;

public class LinkedStack<E> implements Stack<E> {
	SinglyLinkedList<E> list = new SinglyLinkedList<>();
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public boolean isFull() {
		return false;
	}
	
	public void push(E e) {
		list.addFirst(e);
	}
	
	public E top() {
		if (isEmpty()) 
			return null;
		return list.get(0);
	}
	
	public E pop() {
		if (isEmpty())
			return null;
		E item = top();
		list.remove(0);
		return item;
	}
	
}
