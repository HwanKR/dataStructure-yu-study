package dataStructure.chap5;

public class BST<K extends Comparable<K>, V> {
	protected Node<K, V> root;
	
	class Node<K, V> {
		K key;
		V value;
		Node<K, V> left, right, parent;
		
		public Node(K key, V val) {
			this.key = key;
			this.value = val;
		}
	}
	
	protected Node<K, V> treeSearch(K searchKey) {
		if (root == null) return null;
		
		return recursiveSearchHelper(root, searchKey);
	}
	
	private Node<K, V> recursiveSearchHelper(Node<K, V> currentNode, K searchKey) {
		int cmp = searchKey.compareTo(currentNode.key);
		
		if (cmp == 0 || (currentNode.left == null && currentNode.right == null)) 
			return currentNode;
		
		if (cmp < 0) {
			if (currentNode.left != null) 
				return recursiveSearchHelper(currentNode.left, searchKey);
			else 
				return currentNode;
		}
		else {
			if (currentNode.right != null)
				return recursiveSearchHelper(currentNode.right, searchKey);
			else 
				return currentNode;
		}
	}
}
