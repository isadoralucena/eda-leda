package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {
	
	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl(){
		this.last = new DoubleLinkedListNode<>();
		this.head = this.last;
	}

	@Override
	public void insertFirst(T element) {
		if(element != null){ 
			DoubleLinkedListNode<T> oldHead = (DoubleLinkedListNode<T>) this.getHead();
			DoubleLinkedListNode<T> nilNode =  new DoubleLinkedListNode<T>();
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, oldHead, nilNode);

			oldHead.setPrevious(newHead);
			nilNode.setNext(newHead);

			if(this.getHead().isNIL()){
				this.setLast(newHead);
			}
			this.setHead(newHead);
		}
	}

	@Override
	public void removeFirst() {
		DoubleLinkedListNode<T> oldHead = (DoubleLinkedListNode<T>) this.getHead();

		if(!oldHead.isNIL()){
			DoubleLinkedListNode<T> newHead = (DoubleLinkedListNode<T>) oldHead.getNext();
			this.setHead(newHead);

			if(newHead.isNIL()){
				this.setLast(newHead);
			}else{
				newHead.setPrevious(new DoubleLinkedListNode<T>());
			}
		}
	}

	@Override
	public void removeLast() {
		DoubleLinkedListNode<T> oldLast = this.getLast();

		if(!oldLast.isNIL()){
			DoubleLinkedListNode<T> newLast = oldLast.getPrevious();
			this.setLast(newLast);

			if(newLast.isNIL()){
				this.setHead(newLast);
			}else{
				newLast.setNext(new DoubleLinkedListNode<T>());
			}
		}
	}

	@Override
	public void remove(T element){
		if(!this.isEmpty() && element != null){
			DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) this.getHead();

			while(!auxHead.isNIL() && !auxHead.getData().equals(element)){
				auxHead = (DoubleLinkedListNode<T>) auxHead.getNext();
			}

			if(!auxHead.isNIL()){
				DoubleLinkedListNode<T> prevNode = auxHead.getPrevious();
				DoubleLinkedListNode<T> nextNode = (DoubleLinkedListNode<T>) auxHead.getNext();
				
				if(!nextNode.isNIL()){
					nextNode.setPrevious(prevNode);
				}else{
					this.setLast(prevNode);
				}

				if (!prevNode.isNIL()) {
					prevNode.setNext(nextNode);
				} else {
					this.setHead(nextNode);
				}
			}		
		}
	}

	@Override
	public void insert(T element){
		if(element != null){ 
			DoubleLinkedListNode<T> nilNode =  new DoubleLinkedListNode<T>();
			DoubleLinkedListNode<T> oldLastNode = this.getLast();
			DoubleLinkedListNode<T> newLastNode = new DoubleLinkedListNode<T>(element, nilNode, oldLastNode);

			if(!oldLastNode.isNIL()){
				oldLastNode.setNext(newLastNode);
			}else {
				this.setHead(newLastNode);
		}
			nilNode.setPrevious(newLastNode);
			this.setLast(newLastNode);
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return this.last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
}