package midterm1.Chap3;

public class ArrayCircularQueue<E> implements Queue<E> {
	public static final int CAPACITY = 1000;
	private E[] data;
	private int front = -1;
	private int rear = -1;
	
	public ArrayCircularQueue() {
		this(CAPACITY);
	}
	
	public ArrayCircularQueue(int capacity) {
		data = (E[]) new Object[capacity];
	}

	@Override
	public int size() {
		return (rear >= front) ? rear - front : (rear + data.length - front);
	}

	@Override
	public boolean isEmpty() {
		return rear == front;
	}

	@Override
	public boolean isFull() {
		return front == (rear + 1) % data.length;
	}

	@Override
	public void enqueue(E e) {
		if (isFull()) {
			System.out.println("Queue full");
		}
		rear = (rear + 1) % data.length;
		data[rear] = e;
	}

	@Override
	public E first() {
		if (isEmpty()) {
			return null;
		}
		return data[(front + 1) % data.length];
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			return null;
		}
		front = (front + 1) % data.length;
		E item = data[front];
		data[front] = null;
		return item;
	}
}
