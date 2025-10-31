package dataStructure.chap5;

import java.util.ArrayList;
import java.util.List;

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
		return this.size;
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
	
	// 여기서부터 9주 3차시 이진 트리 순회 내용
	public Iterable<Position<E>> inorder() {
		List<Position<E>> result = new ArrayList<Position<E>>();
		inorderSubtree(root(), result);
		return result;
	}
	
	private void inorderSubtree(Position<E> p, List<Position<E>> result) {
		if (p != null) {
			inorderSubtree(left(p), result);
			result.add(p);
			inorderSubtree(right(p), result);
		}
	}
	
	public Iterable<Position<E>> preorder() {
		List<Position<E>> result = new ArrayList<Position<E>>();
		preorderSubtree(root(), result);
		return result;
	}
	
	private void preorderSubtree(Position<E> p, List<Position<E>> result) {
		if (p != null) {
			result.add(p);
			preorderSubtree(left(p), result);
			preorderSubtree(right(p), result);
		}
	}
	
	public Iterable<Position<E>> postorder() {
		List<Position<E>> result = new ArrayList<Position<E>>();
		postorderSubtree(root(), result);
		return result;
	}
	
	private void postorderSubtree(Position<E> p, List<Position<E>> result) {
		if (p != null) {
			postorderSubtree(left(p), result);
			postorderSubtree(right(p), result);
			result.add(p);
		}
	}
	
	public Iterable<Position<E>> iterativeInorder() {
		List<Position<E>> result = new ArrayList<Position<E>>();
		LinkedStack<Position<E>> stack = new LinkedStack<>();
		
		Position<E> p = root();
		while (true) {
			while (p != null) {
				stack.push(p);
				p = left(p);
			}
			if (stack.isEmpty()) break;
			p = stack.pop();
			result.add(p);
			p = right(p);
		}
		return result;
	}
	
	public Iterable<Position<E>> levelOrder() {
		List<Position<E>> result = new ArrayList<Position<E>>();
		LinkedQueue<Position<E>> queue = new LinkedQueue<>();
		
		if (isEmpty()) return result;
		queue.enqueue(root());
		while (true) {
			if (queue.isEmpty()) break;
			Position<E> p = queue.dequeue();
			result.add(p);
			if (left(p) != null) queue.enqueue(left(p));
			if (right(p) != null) queue.enqueue(right(p));
		}
		return result;
	}
	
	public LinkedBinaryTree<E> copy() {
		LinkedBinaryTree<E> dup = new LinkedBinaryTree<>();
		if (root() != null)
			dup.root = copy(root);
		
		return dup;
	}
	
	private TreeNode<E> copy(TreeNode<E> original) {
		if (original != null) {
			TreeNode<E> temp = new TreeNode<E>(original.getElement());
			temp.setLeft(copy(original.getLeft()));
			temp.setRight(copy(original.getRight()));
			return temp;
		}
		return null;
	}
}
