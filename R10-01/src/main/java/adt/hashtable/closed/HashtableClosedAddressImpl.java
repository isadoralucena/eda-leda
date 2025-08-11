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
		int answer = number;

		if (Util.isPrime(number)) {
      answer = number;
    }else{
			answer = getPrimeAbove(number + 1);
		}

		return answer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element) {
		if(element != null && search(element) == null){
			int hashCode = getHashFunction(element);

			if(table[hashCode] == null){
				table[hashCode] = new LinkedList<T>();
			}else{
				this.COLLISIONS += 1;
			}
			
			LinkedList<T> listTable = (LinkedList<T>) this.table[hashCode];

			if (!listTable.contains(element)) {
				listTable.add(element);
				this.elements += 1;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(T element) {
		if (element != null && search(element) != null){
			int hashCode = getHashFunction(element);

			LinkedList<T> listTable = (LinkedList<T>) this.table[hashCode];
			if (listTable != null && listTable.contains(element)) {
				if(listTable.size() > 1){
					this.COLLISIONS -= 1;
				}
				listTable.remove(element);
				this.elements -= 1;
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

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(T element) {
		int index = -1;
		if(element != null){
			int hashCode = this.getHashFunction(element);
			LinkedList<T> listTable = (LinkedList<T>) this.table[hashCode];
			if(listTable != null && listTable.contains(element)){
				index = hashCode;
			}
		}
		return index;
	}

	private int getHashFunction(T element){
		return ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element); 
	}
}
