package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	private int probeIndex(T element, boolean forInsert) {
		int probe = 0;
		int index = -1;
		boolean done = false;
		if (element != null) {
			while (!done && probe < this.table.length) {
				int hashCode = getHashFunction(element, probe);

				if(table[hashCode] == null) {
					if (forInsert) {
						index = hashCode;
					}
					done = true;
				}else if(!table[hashCode].equals(deletedElement) && table[hashCode].equals(element)) {
					index = hashCode;
					done = true;
				}else if(forInsert && table[hashCode].equals(deletedElement)) {
					index = hashCode;
					done = true;
				}else{
					probe++;
					if (forInsert){
						this.COLLISIONS++;
					}
				}
			}
		}
		return index;
	}

	@Override
	public void insert(T element) {
		if (isFull()) throw new HashtableOverflowException();
		int index = probeIndex(element, true);
		if (index != -1 && (table[index] == null || table[index].equals(deletedElement))) {
			table[index] = element;
      elements++;
		}
	}

	@Override
	public void remove(T element) {
		int index = probeIndex(element, false);
		if(index != -1){
			table[index] = deletedElement;
      elements--;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		T answer = null;
		int index = indexOf(element);
		if (index != -1) {
			answer = (T) table[index];
		}

		return answer;
	}

	@Override
	public int indexOf(T element) {
		return probeIndex(element, false);
	}

	private int getHashFunction(T element, int probe) {
		return ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
	}
}