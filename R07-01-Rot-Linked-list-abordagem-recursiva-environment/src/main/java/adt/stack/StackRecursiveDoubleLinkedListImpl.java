package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (!isFull()) {
			top.insertFirst(element);
			size += 1;
		} else {
			throw new StackOverflowException();
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (!isEmpty()) {
			top.removeFirst();
			this.size -= 1;
		}
		throw new StackUnderflowException();
	}

	@Override
	public T top() {
		if (!isEmpty()) {
			return ((RecursiveDoubleLinkedListImpl<T>) top).getData();
		} else {
			return null;
		}
	}

	@Override
	public boolean isEmpty() {
		if (top.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if (top.size() == this.size) {
			return true;
		} else {
			return false;
		}
	}

}
