package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(isFull()) throw new HashtableOverflowException();

		if(element != null && search(element) == null){
			int probe = 0;
			
			while(probe < this.table.length){
				int hashCode = getHashFunction(element, probe);
				
				if(table[hashCode] == null || table[hashCode].equals(deletedElement)){
					table[hashCode] = element;
					elements++;
					break;
				} else {
					probe++;
					this.COLLISIONS++;
				}
			}
			
			if (probe == table.length) throw new HashtableOverflowException();
		}
	}

	@Override
	public void remove(T element) {
		if(element != null && search(element) != null){
			int probe = 0;
			while(probe < this.table.length){
				int hashCode = getHashFunction(element, probe);

				if(table[hashCode] != null && table[hashCode].equals(element)){
					table[hashCode] = new DELETED();
					this.elements--;

					if(probe >= 1) this.COLLISIONS--;

				}else{
					probe++;
				}
			}
		}
	}

	@Override
	public T search(T element) {
		T answer =  null;
		if(indexOf(element) != -1){
			answer = element;
		}
		return answer;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;

		if(element != null){
			int probe = 0;
			while(probe < this.table.length){
				int hashCode = getHashFunction(element, probe);
				if(table[hashCode] != null && table[hashCode].equals(element)){
					index = hashCode;
					break;
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
