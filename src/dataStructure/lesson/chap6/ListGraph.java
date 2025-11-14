package dataStructure.lesson.chap6;

import java.util.LinkedList;

public class ListGraph implements Graph {
	private int N;
	private boolean directed;
	private LinkedList<Integer>[] adj; // 나가는 것 표시
	private LinkedList<Integer>[] inv; // 들어오는 것 표시
	
	@SuppressWarnings("unchecked")
	public ListGraph(int size, boolean directed) {
		this.N = size;
		this.directed = directed;
		adj = new LinkedList[size];
		for (int i=0; i < size; i++)
			adj[i] = new LinkedList<Integer>();	
		if (directed == true) {
			inv = new LinkedList[size];
			for (int i=0; i < size; i++)
				inv[i] = new LinkedList<Integer>();	
		}
	}
	
	@Override
	public int size() {
		return N;
	}

	@Override
	public void addEdge(int v, int w) {
		adj[v].add(w);
		if (directed == false)
			adj[w].add(v);
		else
			inv[w].add(v);
	}

	@Override
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	@Override
	public int inDegree(int v) {
		if (directed == false)
			return adj[v].size();
		else
			return inv[v].size();
	}

	@Override
	public int outDegree(int v) {
		return adj[v].size();
	}
}
