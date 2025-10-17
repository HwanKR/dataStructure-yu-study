package dataStructure;

public class DoublyLinkedList<E> {
	protected class Node<E> {
		E item;
		Node<E> next;
		Node<E> prev;
		Node(E element, Node<E> prev, Node<E> next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}
	
	protected Node<E> first, last;
	protected int size = 0;
	
	void dinsert(Node<E> node, Node<E> newnode) {
		// newnode를 node의 오른쪽에 추가
		newnode.prev = node;
		newnode.next = node.next;
		node.next.prev = newnode;
		node.next = newnode;
		
	}
	
}
