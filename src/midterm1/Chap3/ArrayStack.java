package midterm1.Chap3;

public class ArrayStack<E> implements Stack<E> {
	public static final int CAPACITY = 1000; // 배열이기 때문에 기본 크기 지정해야 함.
	private E[] data;
	private int top = -1; // 스택이 비어있을 때는 아무것도 기리키면 안됨. 
	
	public ArrayStack() {this(CAPACITY);}
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	@Override
	public int size() {
		return top + 1;
	}

	@Override
	public boolean isEmpty() {
		if (top == -1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		if (data.length == size()) {
			return true;
		}
		return false;
	}

	@Override
	public void push(E e) {
		if (isFull()) {
			System.out.println("Stack is Full");
		} else {
			top++;
			data[top] = e;
		}
	}

	@Override
	public E top() {
		if (isEmpty()) {
			return null;
		}
		return data[top];
	}

	@Override
	public E pop() {
		if (isEmpty()) {
			return null;
		}
		E output = data[top];
		data[top] = null;
		top--;
		return output;
	}
	
	public static void main(String[] args) {
		Stack<Integer> S = new ArrayStack<>();
		
		S.push(5);
		S.push(3);
		System.out.println(S.size());
		System.out.println(S.pop());
		System.out.println(S.isEmpty());
		System.out.println(S.pop());
		System.out.println(S.isEmpty());
		System.out.println(S.pop());
		S.push(7);
		S.push(9);
		System.out.println(S.top());
		S.push(4);
		System.out.println(S.size());
		System.out.println(S.pop());
		S.push(6);
		S.push(8);
		System.out.println(S.pop());
	}
}
