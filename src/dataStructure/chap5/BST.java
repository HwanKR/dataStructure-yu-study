package dataStructure.chap5;

public class BST<K extends Comparable<K>, V> {
	class Node<K, V> {
		K key;
		V value;
		Node<K, V> left, right, parent;
		
		public Node(K key, V val) {
			this.key = key;
			this.value = val;
		}
	}
}
