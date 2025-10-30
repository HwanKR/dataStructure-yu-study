package dataStructure.chap5;

public interface Queue<E> {
	int size();
	boolean isEmpty();
	boolean isFull();
	void enqueue(E e);
	E first();
	E dequeue();
}
