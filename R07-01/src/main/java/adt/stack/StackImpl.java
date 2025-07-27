package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		T answer = null;
		if (!this.isEmpty()){
			answer = array[top];
		}

		return answer;
	}

	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public boolean isFull() {
		return this.top == (array.length - 1);
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(this.isFull()){
			throw new StackOverflowException();
		}
		if(element != null){
			array[++top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(this.isEmpty()){
			throw new StackUnderflowException();
		}
		T answer = top();
		array[top--] = null;
		return answer;
	}
}
