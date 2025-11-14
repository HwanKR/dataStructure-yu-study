package dataStructure.lesson.chap6;

import java.util.ArrayList;

public class MatrixGraph implements Graph {
	private int N;
	private boolean directed;
	private int[][] adj;
	
	public MatrixGraph(int size, boolean directed) {
		this.N = size;
		this.directed = directed;
		adj = new int[N][N];
	}
	
	@Override
	public int size() {
		return N;
	}

	@Override
	public void addEdge(int v, int w) {
		adj[v][w] = 1;
		if (directed == false) // 방향성인가?
			adj[w][v] = 1; // 방향성은 반대 방향도 1로
	}

	@Override
	public Iterable<Integer> adj(int v) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i=0; i < N; i++) 
			if (adj[v][i] == 1)
				result.add(i);
		return result;
	}

	@Override
	public int inDegree(int v) {
		int count = 0;
		for (int i=0; i < N; i++)
			if (adj[i][v] == 1)
				count++;
		return count;
	}

	@Override
	public int outDegree(int v) {
		int count = 0;
		for (int i=0; i < N; i++)
			if (adj[v][i] == 1)
				count++;
		return count;
	}
}
