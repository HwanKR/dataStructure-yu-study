package dataStructure.chap5;

import java.util.ArrayList;

public class ThreadedTreeNode<E> implements Position<E> {
	E element;
	boolean lthread;
	boolean rthread;
	ThreadedTreeNode<E> left;
	ThreadedTreeNode<E> right;
	
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
	
	public boolean isRThread() {
        return rthread;
    }
    public void setRThread(boolean rthread) {
        this.rthread = rthread;
    }
    
    public boolean isLThread() {
        return lthread;
    }
    public void setLThread(boolean lthread) {
        this.lthread = lthread;
    }
}
