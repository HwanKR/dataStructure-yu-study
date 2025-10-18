package dataStructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E>, Iterable<E> {
	@SuppressWarnings("hiding")
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
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		Node<E> x = node(i);
		return x.item;
	}

	public E set(int i, E newValue) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		Node<E> x = node(i);
		E oldVlaue = x.item;
		x.item = newValue;
		return oldVlaue;
	}

	public void addFirst(E e) {
		Node<E> newNode = new Node<>(e, first);
		first = newNode;
		if (last == null)
			last = newNode;
		size++;
	}
	
	public void addLast(E e) {
		Node<E> newNode = new Node<>(e, null);
		if (last == null)
			first = newNode;
		else
			last.next = newNode;
		last = newNode;
		size++;
	}

	public void invert() {
		Node<E> lead = first, middle, tail;
		
		middle = null;
		while(lead != null) {
			tail = middle;
			middle = lead;
			lead = lead.next;
			middle.next = tail;
		}
		last = first;
		first = middle;
	}
	
	public void concatenate(SinglyLinkedList<E> other) {
		if (this.size() == 0) {
			this.first = other.first;
			this.last = other.last;
			return;
		}
		this.last.next = other.first;
	}
	
	protected Node<E> node(int i) throws IndexOutOfBoundsException {
		Node<E> ptr = first;
		for (int k = 0; k < i; k++)
			ptr = ptr.next;
		return ptr;
	}
	
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if (i < 0 || i >= n)
			throw new IndexOutOfBoundsException("Illegal index: " + i);
	}
	
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator();
	}
	
	private class SinglyLinkedListIterator implements Iterator<E> {
		private Node<E> lastReturned;
        private Node<E> nextNode;
        private int nextIndex;
        
        public SinglyLinkedListIterator() {
        	nextNode = first;
        }
        
		public boolean hasNext() {
			return nextIndex < size;
		}

		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			lastReturned = nextNode;
			nextNode = nextNode.next;
			nextIndex++;
			return lastReturned.item;
		}
	}

	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		if (i <= 0) addFirst(e);
		else if (i >= size) addLast(e);
		else {
			Node<E> prev = node(i-1);
			Node<E> newNode = new Node<>(e, prev.next);
			prev.next = newNode;
			size++;
		}
	}

	@Override
	public boolean remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		if (i == 0) {
			first = first.next;
			if (first == null) last = null;
		} else {
			Node<E> prev = node(i-1);
			Node<E> deleted = prev.next;
			prev.next = deleted.next;
			if (last == deleted) last = prev;
		}
		size--;		
		return true;
	}

	@Override
	public boolean remove(E e) {
		if (isEmpty()) return false;
		
		if (first.item.equals(e)) {
			remove(0);
			return true;		
		} else {
			Node<E> prev;
			for (prev=first; prev.next!=null; prev=prev.next) {
				if (prev.next.item.equals(e)) {
					Node<E> deleted = prev.next;
					prev.next = deleted.next;
					if (last == deleted) last = prev;
					size--;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (Node<E> ptr=first; ptr != null; ptr = ptr.next) {
			sb.append(ptr.item + " ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	
}
