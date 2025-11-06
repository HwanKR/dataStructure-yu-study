package dataStructure.chap5;

import java.util.ArrayList;
import java.util.List;

public class ThreadedBinaryTree<E> {
	protected ThreadedTreeNode<E> root;
	
	protected ThreadedTreeNode<E> validate(Position<E> p) {
		if (!(p instanceof TreeNode)) {
			throw new IllegalArgumentException("Invalid type");
		}
		return (ThreadedTreeNode<E>)p;
	}
	
    // 한 노드의 inorder successor 발견
    private ThreadedTreeNode<E> insucc(ThreadedTreeNode<E> p) {
    	ThreadedTreeNode<E> temp = p.getRight();
    	if (p.rthread == false)
    		while (temp.lthread == false)
    			temp = temp.getLeft();
    	return temp;
    }
    
    // Inorder Traversal
    public Iterable<Position<E>> inorder() {
    	List<Position<E>> result = new ArrayList<Position<E>>();
    	ThreadedTreeNode<E> p = root;
    	while (true) {
    		p = insucc(p);
    		if (p == root)
    			break;
    		result.add(p);
    	}
    	return result;
    }
    
    public void insertRight(Position<E> pParent, Position<E> pChild) {
    	ThreadedTreeNode<E> parent = validate(pParent);
    	ThreadedTreeNode<E> child = validate(pChild);
    	child.right = parent.right;
    	child.rthread = parent.rthread;
    	child.left = parent;
    	child.lthread = true;
    	parent.right = child;
    	parent.rthread = false;
    	if (child.rthread == false) {
    		ThreadedTreeNode<E> temp = insucc(child);
    		temp.left = child;
    	}
    	
    }
    
}
