package dataStructure.practice;

public class TreeNode<E> {
	E element;
	TreeNode<E> left, right;
	
	public TreeNode(E element, TreeNode<E> left, TreeNode<E> right) {
		this.element = element;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "TreeNode [element=" + element + "]";
	}
}
