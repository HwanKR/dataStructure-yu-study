package dataStructure.chap5;

public class TreeNode<E> implements Position<E> {
	private E element;
	private TreeNode<E> left, right;
	
	public TreeNode(E element) {
		this.element = element;
	}
	
	public TreeNode(E element, TreeNode<E> left, TreeNode<E> right) {
		this.element = element;
		this.left = left;
		this.right = right;
	}
	
	@Override
	public E getElement() throws IllegalStateException {
		return element;
	}
	
	public TreeNode<E> getLeft() {
		return left;
	}
	public TreeNode<E> getRight() {
		return right;
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	public void setLeft(TreeNode<E> left) {
		this.left = left;
	}
	public void setRight(TreeNode<E> right) {
		this.right = right;
	}
	
	
	public String toString() {
		return "노드(" + element + ")";
	}
	
}
