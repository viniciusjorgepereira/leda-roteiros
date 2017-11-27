package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insert(T element) {
		DoubleLinkedListNode newNode = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(),
				new DoubleLinkedListNode<>());
		if (isEmpty()) {
			head = newNode;
			last = newNode;
		} else {
			newNode.previous = last;
			last.next = newNode;
			last = newNode;
		}
	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode newNode = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(),
				new DoubleLinkedListNode<>());
		newNode.next = head;
		newNode.previous = new DoubleLinkedListNode<>();
		((DoubleLinkedListNode<T>) head).previous = newNode;
		if (head.isNIL()) {
			last = newNode;
		}
		head = newNode;
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (!head.isNIL()) {
				head = head.next;
				if (head.isNIL()) {
					last = (DoubleLinkedListNode<T>) head;
				}
				((DoubleLinkedListNode<T>) head).previous = new DoubleLinkedListNode<>();
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			last = last.previous;
			last.next = new DoubleLinkedListNode<>();
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (head.data.equals(element)) {
				removeFirst();
			} else {
				SingleLinkedListNode<T> aux = head;
				while (!aux.isNIL() && !aux.data.equals(element)) {
					aux = aux.next;
				}
				if (!aux.isNIL()) {
					((DoubleLinkedListNode<T>) aux).previous.next = aux.next;
					((DoubleLinkedListNode<T>) aux.next).previous = ((DoubleLinkedListNode<T>) aux).previous;
				}
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
}
