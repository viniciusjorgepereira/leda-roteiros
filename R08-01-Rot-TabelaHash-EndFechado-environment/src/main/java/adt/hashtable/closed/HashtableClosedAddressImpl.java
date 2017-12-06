package adt.hashtable.closed;

import java.util.LinkedList;
import java.util.List;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size of
	 * the table to an integer that is prime. This can be achieved by producing such
	 * a prime number that is bigger and close to the desired size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and getPrimeAbove as
	 * documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number greater
	 * than the given size. For example, if size=10 then the length must be 11. If
	 * size=20, the length must be 23. You must implement this idea in the auxiliary
	 * method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
			// the immediate prime
			// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. You can use the method Util.isPrime to check if a number is prime.
	 */
	int getPrimeAbove(int number) {
		int prime = number;
		if (Util.isPrime(prime)) {
			prime += 1;
		}
		while (!Util.isPrime(prime)) {
			prime += 1;
		}
		return prime;
	}

	private int getIndex(T element) {
		int index = 0;
		if (this.hashFunction instanceof HashFunctionClosedAddress) {
			index = ((HashFunctionClosedAddress) this.hashFunction).hash(element);
		}
		return index;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			int index = this.getIndex(element);
			if (this.table[index] == null) {
				List<T> list = new LinkedList<>();
				list.add(element);
				this.table[index] = list;
			} else {
				List<T> list = (List<T>) this.table[index];
				list.add(element);
				this.COLLISIONS += 1;
			}
			this.elements += 1;
		}
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			int index = this.getIndex(element);
			if (index >= 0) {
				List<T> list = (List<T>) this.table[index];
				list.remove(element);
				this.elements -= 1;
			}
		}
	}

	@Override
	public T search(T element) {
		T exit = null;
		if (element != null) {
			int index = this.getIndex(element);
			if (this.table[index] != null) {
				List<T> list = (List<T>) this.table[index];
				if (list.contains(element)) {
					exit = list.get(list.indexOf(element));
				}
			}
		}
		return exit;
	}

	@Override
	public int indexOf(T element) {
		boolean result = false;
		int index = this.getIndex(element);
		if (element != null) {
			if (this.table[index] != null) {
				List<T> list = (List<T>) this.table[index];
				if (list.contains(element)) {
					result = true;
				}
			}
		}
		if (result) {
			return index;
		} else {
			return -1;
		}
	}
}
