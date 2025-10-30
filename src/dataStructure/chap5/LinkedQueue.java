package dataStructure.chap5;

public class LinkedQueue<E> implements Queue<E> {
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
	
	public void enqueue(E e) {
		list.addLast(e);
	}
	
	public E first() {
		return list.get(0);
	}
	
	public E dequeue() {
		if (isEmpty())
			return null;
		E element = first();
		list.remove(0);
		return element;
	}
}
