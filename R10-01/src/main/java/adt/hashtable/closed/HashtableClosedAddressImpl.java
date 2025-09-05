package adt.hashtable.closed;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size (or the given size, if it is already prime). 
	 * For example, if size=10 then the length must
	 * be 11. If size=20, the length must be 23. You must implement this idea in
	 * the auxiliary method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize);
		}

		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}

	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number.
	 * If the given number is prime, it is returned. 
	 * You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		while (!Util.isPrime(number)) {
      number++;
    }
    return number;
	}

	@SuppressWarnings("unchecked")
	private LinkedList<T> getList(T element, boolean createIfAbsent) {
		LinkedList<T> listTable =  null;

		if (element != null){
			int hashCode = getHashFunction(element);
			listTable = (LinkedList<T>) this.table[hashCode];
			if (createIfAbsent && listTable == null) {
				listTable = new LinkedList<>();
				this.table[hashCode] = listTable;
			}
		}
		return listTable;
	}


	@Override
	public void insert(T element) {
		if(element != null){
			LinkedList<T> listTable = getList(element, true);

			if (!listTable.contains(element)) {
				if(!listTable.isEmpty()) this.COLLISIONS++;
				listTable.add(element);
				this.elements++;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null){
			LinkedList<T> listTable = getList(element, false);

			if (listTable != null && listTable.remove(element)) {
				this.elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		T answer =  null;
		if (element != null){
			LinkedList<T> listTable = getList(element, false);
			if (listTable != null){
				for (T item : listTable){
					if (item.equals(element)){
						answer = item;
					}
				}
			}
		}
		return answer;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;

		if(element != null){
			LinkedList<T> listTable = getList(element, false);

			if(listTable != null && listTable.contains(element)){
				index = getHashFunction(element);
			}
		}
		return index;
	}

	private int getHashFunction(T element){
		return ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element); 
	}
}
