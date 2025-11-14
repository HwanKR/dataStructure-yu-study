package dataStructure.lesson.chap6;

public interface Graph {
	int size();	// Vertex 수
	void addEdge(int v, int w); // v와 w를 연결하는 Edge 추가
	Iterable<Integer> adj(int v); // v에 연결된 정점들을 순회
	int inDegree(int v); // v의 in-degree를 출력
	int outDegree(int v); // v의 out-degree를 출력
}
