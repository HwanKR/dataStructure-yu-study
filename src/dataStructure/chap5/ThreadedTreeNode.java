package dataStructure.chap5;

public class ThreadedTreeNode<E> implements Position<E> {
	private E element;
	private boolean lthread;
	private boolean rthread;
	private ThreadedTreeNode<E> left;
	private ThreadedTreeNode<E> right;
	
	public ThreadedTreeNode(E element) {
		this.element = element;
		this.lthread = true;
		this.rthread = true;
		this.left = null;
		this.right = null;
	}
	public ThreadedTreeNode(E element, boolean lthread, boolean rthread, 
            ThreadedTreeNode<E> left, ThreadedTreeNode<E> right) {
		this.element = element;
		this.lthread = lthread;
		this.rthread = rthread;
		this.left = left;
		this.right = right;
	}
	
	@Override
	public E getElement() throws IllegalStateException {
		return element;
	}
	
	public ThreadedTreeNode<E> getLeft() {
		return left;
	}
	public ThreadedTreeNode<E> getRight() {
		return right;
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	public void setLeft(ThreadedTreeNode<E> left) {
		this.left = left;
	}
	public void setRight(ThreadedTreeNode<E> right) {
		this.right = right;
	}

}
