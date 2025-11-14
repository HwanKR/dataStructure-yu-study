package dataStructure.lesson.chap6;

public interface Graph {
	int size();
	void addEdge(int v, int w);
	Iterable<Integer> adj(int v);
	int inDegree(int v);
	int outDegree(int v);
}
