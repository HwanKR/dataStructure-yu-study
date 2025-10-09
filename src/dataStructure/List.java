package dataStructure;

public interface List<E> {
	int size(); // 원소 수
	boolean isEmpty(); // Empty?
	E get(int i) throws IndexOutOfBoundsException; // i번째 원소 반혼
	E set(int i, E e) throws IndexOutOfBoundsException; // i번째 원소 변경
	void add(int i, E e) throws IndexOutOfBoundsException; // i번째 원소를 추가
	boolean remove(int i) throws IndexOutOfBoundsException; // i번째 원소 삭제
	void addFirst(E e); // 제일 앞에 추가
	void addLast(E e); // 제일 뒤에 추가
	boolean remove(E e); // 원소 e를 제거
}