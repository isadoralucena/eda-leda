package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		int answer = 0;
		if(!isEmpty()){
			answer = 1 + this.getNext().size();
		}
		return answer;
	}

	@Override
	public T search(T element) {
		T answer = null;

		if(element != null && !isEmpty()){
			if (this.getData().equals(element)){
				answer = this.getData();
			}else{
				answer = this.getNext().search(element);
			}
		}
		
		return answer;
	}

	@Override
	public void insert(T element) {
		if(element != null){
			if(isEmpty()){
				this.setData(element);
				this.setNext(new RecursiveSingleLinkedListImpl<T>());
			}else{
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(element != null && !isEmpty()){
			if(this.getData().equals(element)){
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			}else{
				this.getNext().remove(element);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		toArray(array, 0);
		return array;
	}

	private void toArray(T[] array, int i) {
		if (!this.isEmpty()) {
			array[i] = this.getData();
			this.getNext().toArray(array, i + 1);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
