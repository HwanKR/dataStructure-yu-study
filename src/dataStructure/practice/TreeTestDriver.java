package dataStructure.practice;

public class TreeTestDriver {

	public static void main(String[] args) {
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		TreeNode<Integer> root = tree.addRoot(10);
		tree.addLeft(root, 5);
		tree.addRight(root, 9);
		tree.addLeft(root.right, 7);
		tree.addRight(root.right, 6);
		
		System.out.println("Inorder: ");
		for (int x : tree.inorder()) {
			System.out.print(x + " ");
		}
		System.out.println();
		
		System.out.println("노드 수 = " + tree.size());
		System.out.println("트리의 깊이 = " + tree.depth());
		
		LinkedBinaryTree<Integer> newTree = tree.swap();
		System.out.println("Inorder of NewTree: ");
		for (int x : newTree.inorder()) {
			System.out.print(x + " ");
		}
		System.out.println();
	}

}
