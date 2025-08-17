package problems;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class ListInversionImpl extends SingleLinkedListImpl<Integer> implements ListInversion<Integer> {

	@Override
	public void reverseIterative() {
		SingleLinkedListNode<Integer> current = this.head;
		
		if(!current.isNIL()){
			SingleLinkedListNode<Integer> prev = new SingleLinkedListNode<Integer>();
			SingleLinkedListNode<Integer> next = prev;

			while(!current.isNIL()){
				next = current.getNext();
				current.setNext(prev);
				prev = current;
				current = next;
			}
			this.setHead(prev);
		}
	}
	
	@Override
	public void reverseRecursive() {
		SingleLinkedListNode<Integer> current = this.head;
		if(!current.isNIL()){
			SingleLinkedListNode<Integer> prev = new SingleLinkedListNode<Integer>();

			if(!current.isNIL()){
				this.aux(current, prev);
			}
		}
	}

	public void aux(SingleLinkedListNode<Integer> current, SingleLinkedListNode<Integer> prev){
		SingleLinkedListNode<Integer> auxiliarNext = current.getNext();
		current.setNext(prev);
		prev = current;
		current = auxiliarNext;
		if(!current.isNIL()){
			aux(current, prev);
		}else{
			this.setHead(prev);
		}
	}
	
	// NAO ALTERE NADA NESTE METODO. ELE SERA UTIL QUANDO VOCE QUISER TESTAR SUA IMPLEMENTACAO
	@Override
	public void insert(Integer element) {
		SingleLinkedListNode<Integer> auxHead = head;
		if(head.isNIL()){
			SingleLinkedListNode<Integer> newHead = new SingleLinkedListNode<Integer>(element,head);
			head = newHead;
		}else{
			while(!auxHead.getNext().isNIL()){
				auxHead = auxHead.getNext();
			}
			SingleLinkedListNode<Integer> newNode = new SingleLinkedListNode<Integer>(element,auxHead.getNext());
			auxHead.setNext(newNode);
		}
	}
}