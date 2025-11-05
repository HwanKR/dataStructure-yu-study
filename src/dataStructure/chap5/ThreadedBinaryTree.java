package dataStructure.chap5;

import java.util.ArrayList;
import java.util.List;

public class ThreadedBinaryTree<E> {
	protected ThreadedTreeNode<E> root;
	
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
    
}
