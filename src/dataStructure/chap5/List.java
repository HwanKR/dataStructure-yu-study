package dataStructure.chap5;

public interface List<E> {
	int size();
	boolean isEmpty();
	E get(int i) throws IndexOutOfBoundsException;
	E set(int i, E e) throws IndexOutOfBoundsException;
	void add(int i, E e) throws IndexOutOfBoundsException;
	boolean remove(int i) throws IndexOutOfBoundsException;
	void addFirst(E e);
	void addLast(E e);
	boolean remove(E e);
}
