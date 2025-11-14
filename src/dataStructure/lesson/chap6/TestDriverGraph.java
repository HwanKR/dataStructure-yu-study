package dataStructure.lesson.chap6;

public class TestDriverGraph {
	public static void main(String[] args) {
		
//		Graph G = new MatrixGraph(7, false); // MatrixGraph 테스트 드라이버
		Graph G = new ListGraph(7, false);
		
		G.addEdge(0, 1);
		G.addEdge(0, 2);
		G.addEdge(1, 3);
		G.addEdge(1, 4);
		G.addEdge(0, 1);
		G.addEdge(2, 5);
		G.addEdge(2, 6);
		
		System.out.print("1에 인접한 정점들: ");
		for (int v : G.adj(1))
			System.out.print(v + " ");
		System.out.println("\n0의 in-degree = " + G.inDegree(0));
		System.out.println("0의 out-degree = " + G.outDegree(0));

//		G = new MatrixGraph(6, true); // MatrixGraph 테스트 드라이버
		G = new ListGraph(7, true);
		G.addEdge(1, 0);
		G.addEdge(1, 3);
		G.addEdge(2, 1);
		G.addEdge(3, 2);
		G.addEdge(3, 4);
		G.addEdge(3, 5);
		G.addEdge(4, 0);
		G.addEdge(5, 0);
		G.addEdge(5, 1);
		G.addEdge(5, 4);
		
		System.out.print("\n1에 인접한 정점들: ");
		for (int v : G.adj(1))
			System.out.print(v + " ");
		System.out.println("\n5의 in-degree = " + G.inDegree(5) + "개");
		System.out.println("5의 out-degree = " + G.outDegree(5) + "개");
	}
}
