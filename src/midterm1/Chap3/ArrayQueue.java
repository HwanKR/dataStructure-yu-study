package midterm1.Chap3;

public class ArrayQueue<E> implements Queue<E> {
	public static final int CAPACITY = 1000;
	private E[] data;
	private int front = -1;
	private int rear = -1;
	
	public ArrayQueue() {
		this(CAPACITY);
	}
	
	public ArrayQueue(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	@Override
	public int size() {
		return rear - front;
	}

	@Override
	public boolean isEmpty() { 
		return size() == 0;
	}

	@Override
	public boolean isFull() {
		return data.length - 1 == rear;
	}

	@Override
	public void enqueue(E e) {
		if (isFull()) {
			System.out.println("Queue Full");
		} 
		++rear;
		data[rear] = e;
	}

	@Override
	public E first() {
		if (isEmpty()) {
			return null;
		}
		return data[front+1];
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			return null;
		}
		E item = data[front+1];
		data[front+1] = null;
		front++;		
		return item;
	}
	
	public static void main(String[] args) {
	    Queue<Integer> Q = new ArrayQueue<>(10);
	    Q.enqueue(5);
	    Q.enqueue(3);
	    System.out.println(Q.size());
	    System.out.println(Q.dequeue());
	    System.out.println(Q.isEmpty());
	    System.out.println(Q.dequeue());
	    System.out.println(Q.isEmpty());
	    System.out.println(Q.dequeue());
	    Q.enqueue(1);
	    Q.enqueue(2);
	    Q.enqueue(3);
	    System.out.println(Q.first());
	    Q.enqueue(4);
	    System.out.println(Q.size());
	    System.out.println(Q.dequeue());
	}
}
