package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.getHead().isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> auxHead = this.getHead();
		while(!auxHead.isNIL()){
			auxHead = auxHead.getNext();
			size++;
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> auxHead = this.getHead();

		while(!auxHead.isNIL() && auxHead.getData() != element){
			auxHead = auxHead.getNext();
		}

		return auxHead.getData();
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> auxHead = this.getHead();

		if(element != null){
			if(head.isNIL()){
				SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, auxHead);
				this.setHead(newHead);
			} else{
				while(!auxHead.isNIL()){
					auxHead = auxHead.getNext();
				}
				auxHead.setData(element);
				auxHead.setNext(new SingleLinkedListNode<>());
			}
		}
	}

	@Override
	public void remove(T element) {
		SingleLinkedListNode<T> auxHead = this.getHead();

		while(auxHead.getNext().getData() != element){
			auxHead = auxHead.getNext();
		}

		if(!auxHead.isNIL()){
			auxHead.setNext(auxHead.getNext().getNext());	
		}		
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		SingleLinkedListNode<T> auxHead = this.getHead();
		T[] answer = (T[]) new Object[size()];

		int count = 0;
		while(!auxHead.isNIL()){
			answer[count] = auxHead.getData();
			auxHead = auxHead.getNext();
			count++;
		}

		return answer;
	}

	public SingleLinkedListNode<T> getHead() {
		return this.head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
