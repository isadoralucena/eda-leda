package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	private int probeIndex(T element, boolean forInsert) {
		int probe = 0;
		int index = -1;
		boolean done = false;

		if (element != null) {
			while (!done && probe < this.table.length) {
				int hashCode = getHashFunction(element, probe);

				if (table[hashCode] == null) {
					if (forInsert) {
						index = hashCode;
					}
					done = true;
				} else if (!table[hashCode].equals(deletedElement) && table[hashCode].equals(element)) {
					index = hashCode;
					done = true;
				} else if (forInsert && table[hashCode].equals(deletedElement)) {
					index = hashCode;
					done = true;
				} else {
					probe++;
					if (forInsert) {
						this.COLLISIONS++;
					}
				}
			}
		}
		return index;
	}

	@Override
	public void insert(T element) {
		if (isFull())
			throw new HashtableOverflowException();

		if (element != null) {
			int index = probeIndex(element, true);

			if (index != -1 && (table[index] == null || table[index].equals(deletedElement))) {
				table[index] = element;
				elements++;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			int index = probeIndex(element, false);
			if (index != -1) {
				table[index] = deletedElement;
				elements--;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		T answer = null;
		int index = probeIndex(element, false);
		;
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
		return ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
	}
}
