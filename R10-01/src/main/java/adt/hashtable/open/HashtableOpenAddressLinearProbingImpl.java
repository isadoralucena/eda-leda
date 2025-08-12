package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T search(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public int indexOf(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	private int getHashFunction(T element, int probe){
		return ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
	}
}
