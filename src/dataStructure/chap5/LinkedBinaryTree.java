package dataStructure.chap5;

public class LinkedBinaryTree<E> {
	protected TreeNode<E> root = null;
	protected int size = 0;
	
	protected TreeNode<E> validate(Position<E> p) {
		if (!(p instanceof TreeNode)) {
			throw new IllegalArgumentException("Invalid type");
		}
		return (TreeNode<E>)p;
	}
	
	public int size() {
		return this.size();
	}
	public boolean isEmpty() {
		return size == 0;
	}
	
	public Position<E> root() {
		return this.root;
	}
	
	public Position<E> left(Position<E> p) {
		TreeNode<E> node = validate(p);
		return node.getLeft();
	}
	
	public Position<E> right(Position<E> p) {
		TreeNode<E> node = validate(p);
		return node.getRight();
	}
	
	public Position<E> addRoot(E e) {
		if (!isEmpty()) {
			throw new IllegalStateException("Tree is not empty");
		}
		root = new TreeNode<E>(e, null, null);
		size = 1;
		return root;
	}
	
	public Position<E> addLeft(Position<E> p, E e) {
		TreeNode<E> parent = validate(p);
		if (parent.getLeft() != null) {
			throw new IllegalArgumentException("p already has a left child");
		}
		TreeNode<E> child = new TreeNode<E>(e, null, null);
		parent.setLeft(child);
		size++;
		return child;
	}
	
	public Position<E> addRight(Position<E> p, E e) {
		TreeNode<E> parent = validate(p);
		if (parent.getRight() != null) {
			throw new IllegalArgumentException("p already has a right child");
		}
		TreeNode<E> child = new TreeNode<E>(e, null, null);
		parent.setRight(child);
		size++;
		return child;
	}
	
	public E set(Position<E> p, E newValue) {
		TreeNode<E> node = validate(p);
		E oldValue = node.getElement();
		node.setElement(newValue);
		return oldValue;
	}
}
