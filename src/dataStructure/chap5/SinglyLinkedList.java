package dataStructure.chap5;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E>, Iterable<E> {
	protected class Node<E> {
		E item;
		Node<E> next;
		Node(E element, Node<E> next) {
			this.item = element;
			this.next = next;
		}
	}
	
	protected Node<E> first;
	protected Node<E> last;
	protected int size = 0;
	
	public int size() { return size;}
	
	public boolean isEmpty() { return size == 0; }
	
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		Node<E> x = node(i);
		return x.item;
	}
	
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i >= n)
			throw new IndexOutOfBoundsException("Illegal index: " + i);
	}
	
	protected Node<E> node(int i) throws IndexOutOfBoundsException {
		Node<E> ptr = first;
		for (int k=0; k < i; k++)
			ptr = ptr.next;
		return ptr;
	}
	
	public E set(int i, E newValue) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		Node<E> x = node(i);
		E oldValue = x.item;
		x.item = newValue;
		return oldValue;
	}
	
	public void addFirst(E e) {
		Node<E> newNode = new Node<>(e, first);
		first = newNode;
		if (last == null) last = newNode;
		size++;
	}
	
	public void addLast(E e) {
		Node<E> newNode = new Node<>(e, null);
		if (last == null) first = newNode;
		else last.next = newNode;
		last = newNode;
		size++;
	}
	
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size+1);
		if (i == 0)
			addFirst(e);
		else if (i == size-1)
			addLast(e);
		else {
			Node<E> x = node(i-1);
			Node<E> newNode = new Node<>(e, x.next);
			x.next = newNode;
			size++;
		}
	}
	
	public boolean remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		if (i == 0) {
			first = first.next;
			if (first == null)
				last = null;
		} else {
			Node<E> x = node(i-1);
			Node<E> deleted = x.next;
			x.next = deleted.next;
			if (deleted == last)
				last = x;
		}
		size--;
		return true;
	}
	
	public boolean remove (E e) {
		if (first == null) return false;
		
		if (e.equals(first.item)) {
			first = first.next;
			if (first == null) last = null;
			size--;
			return true;
		}
		
		for (Node<E> x = first; x.next != null; x = x.next) {
			if (e.equals(x.next.item)) {
				Node<E> deleted = x.next;
				x.next = deleted.next;
				if (deleted == last) last = x;
				size--;
				return true;
			}
		}
		return false;
	}
	
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator();
	}
	
	private class SinglyLinkedListIterator implements Iterator<E> {
		private Node<E> lastReturned, nextNode;
		private int nextIndex;
		
		public SinglyLinkedListIterator() { nextNode = first; }
		
		public boolean hasNext() {return nextIndex < size;}
		
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			lastReturned = nextNode;
			nextNode = nextNode.next;
			nextIndex++;
			return lastReturned.item;
		}
	}
}
