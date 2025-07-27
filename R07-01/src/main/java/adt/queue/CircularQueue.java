package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	@SuppressWarnings("unchecked")
	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(this.isFull()){
			throw new QueueOverflowException();
		}
		if(element != null){
			tail = (tail + 1) % array.length;
			array[tail] = element;
			if(elements == 0){
				head = tail;
			}
			elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(this.isEmpty()){
			throw new QueueUnderflowException();
		}

		T answer = head();
		array[head] = null;
		head = (head + 1) % array.length;
		elements--;
		return answer;
	}

	@Override
	public T head() {
		T answer = null;
		if(!this.isEmpty()){
			answer = array[head];
		}
		return answer;
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		return elements == array.length;
	}

}
