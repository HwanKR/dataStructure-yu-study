package dataStructure.practice;

import java.util.ArrayList;

public class LinkedBinaryTree<E> {
	private TreeNode<E> root;
	
	public boolean isEmpty() {
		return root == null;
	}
	public TreeNode<E> root() {
		return root;
	}
	
	public TreeNode<E> addRoot(E e) {
		root = new TreeNode<>(e, null, null);
		return root;
	}
	
	public void addLeft(TreeNode<E> p , E e) {
		p.left = new TreeNode<>(e, null, null);
	}
	
	public void addRight(TreeNode<E> p, E e) {
		p.right = new TreeNode<>(e, null, null);
	}

	public Iterable<E> inorder() {
		ArrayList<E> result = new ArrayList<>();
		inorderSubtree(root, result);
		return result;
	}
	
	private void inorderSubtree(TreeNode<E> ptr, ArrayList<E> result) {
		if (ptr != null) {
			inorderSubtree(ptr.left, result);
			result.add(ptr.element);
			inorderSubtree(ptr.right, result);
		}
	}
	
	public int size() {
		return sizeSubtree(root);
	}
	
	private int sizeSubtree(TreeNode<E> ptr) {
		if (ptr == null) {
			return 0;
		} else {
			int lsize = sizeSubtree(ptr.left);
			int rsize = sizeSubtree(ptr.right);
			return lsize + rsize + 1;
		}
	}
	
	public int depth() {
		return depthSubtree(root);
	}
	
	private int depthSubtree(TreeNode<E> ptr) {
		if (ptr == null) {
			return 0;
		} else {
			int ldepth = depthSubtree(ptr.left);
			int rdepth = depthSubtree(ptr.right);
			return Math.max(ldepth, rdepth) + 1;
		}
	}
	
	public LinkedBinaryTree<E> swap() {
		LinkedBinaryTree<E> newTree = new LinkedBinaryTree<>();
		newTree.root = swapSubtree(root);
		return newTree;
	}
	
	private TreeNode<E> swapSubtree(TreeNode<E> original) {
		if (original == null) return null;
		else {
			TreeNode<E> copy = new TreeNode<>(original.element, swapSubtree(original.right), swapSubtree(original.left));
			return copy;
		}
	}
}
