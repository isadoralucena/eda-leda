package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}
 
	@Override
	public void insert(T element) {
		if(isFull()) throw new HashtableOverflowException();

		if(element != null){
			int probe = 0;
			int hashCode = -1;
			boolean inserted = false;

			while(!inserted && probe < this.table.length){
				hashCode = getHashFunction(element, probe);
				
				if(table[hashCode] == null || table[hashCode].equals(deletedElement)){
					table[hashCode] = element;
					elements++;
					inserted = true;
				} else if (table[hashCode].equals(element)){
					inserted = true;
				} else {
					probe++;
					this.COLLISIONS++;
				}
			}
			
		}
	}

	@Override
	public void remove(T element) {
		if(element != null){
			int probe = 0;
			int hashCode = -1;
			boolean removed = false;

			while(!removed && probe < this.table.length){
				hashCode = getHashFunction(element, probe);

				if(table[hashCode] == null){
					removed = true;
				} else if (!table[hashCode].equals(deletedElement) && table[hashCode].equals(element)){
					table[hashCode] = deletedElement;
					this.elements--;
					removed = true;
				}else{
					probe++;
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		T answer =  null;
		int index = indexOf(element);
		if(index != -1){
			answer =  (T) table[index];
		}

		return answer;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;

		if(element != null){
			int probe = 0;
			boolean found = false;
			int hashCode = -1;

			while(!found && probe < this.table.length){
				hashCode = getHashFunction(element, probe);

				if(table[hashCode] == null){
					found = true;
				} else if (!table[hashCode].equals(deletedElement) && table[hashCode].equals(element)){
					index = hashCode;
					found = true;
				}else{
					probe++;
				}
			}
		}
		return index;
	}

	private int getHashFunction(T element, int probe){
		return ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
	}
}
