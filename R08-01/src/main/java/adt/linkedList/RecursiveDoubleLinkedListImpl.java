package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.insert(element);
			} else {
				RecursiveDoubleLinkedListImpl<T> node = new RecursiveDoubleLinkedListImpl<>();
				node.setData(this.getData());
				this.setData(element);
				node.setNext(this.getNext());
				this.setNext(node);
				node.setPrevious(this);
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(node);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			if (this.getPrevious().isEmpty() && this.getNext().isEmpty()) {
				this.setData(null);
				this.setNext(null);
				this.setPrevious(null);
			} else {
				RecursiveDoubleLinkedListImpl<T> next = (RecursiveDoubleLinkedListImpl<T>) this.getNext();
				this.setData(next.getData());
				this.setNext(next.getNext());
				next.setPrevious(this);
			}
		}
	}

	@Override
	public void removeLast() {
		if (!this.isEmpty()) {
			if (this.getNext().isEmpty()) {
				this.setData(null);
				this.setNext(null);

				if (this.getPrevious().isEmpty()) {
					this.setPrevious(null);
				}
			} else {
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).removeLast();
			}
		}
	}

	@Override
	public void insert(T element){
		if(element != null){
			if(this.isEmpty()){
				RecursiveDoubleLinkedListImpl<T> nilNode = new RecursiveDoubleLinkedListImpl<>();
				this.setData(element);
				this.setNext(nilNode);
				nilNode.setPrevious(this);

				if(this.getPrevious() == null){
					RecursiveDoubleLinkedListImpl<T> nilNodeAux = new RecursiveDoubleLinkedListImpl<>();
					this.setPrevious(nilNodeAux);
					nilNodeAux.setNext(this);
				}
			}else{
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			if (this.getData().equals(element)) {
				if (this.getPrevious().isEmpty()) {
					this.removeFirst();
				} else if (this.getNext().isEmpty()) {
					this.removeLast();
				} else {
					this.getPrevious().setNext(this.getNext());
					((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this.getPrevious());
				}
			} else {
				this.getNext().remove(element);
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
