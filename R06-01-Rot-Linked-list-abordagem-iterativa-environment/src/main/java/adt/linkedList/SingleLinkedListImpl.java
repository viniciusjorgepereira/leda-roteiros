package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		boolean exit = false;
		if (head.isNIL()) {
			exit = true;
		}
		return exit;
	}

	@Override
	public int size() {
		int exit = 0;
		SingleLinkedListNode<T> aux = head;
		while (!aux.isNIL()) {
			exit += 1;
			aux = aux.next;
		}
		return exit;
	}

	@Override
	public T search(T element) {
		T exit = null;
		if (!isEmpty()) {
			SingleLinkedListNode<T> aux = head;
			while (!aux.isNIL()) {
				if (aux.getData().equals(element)) {
					exit = aux.getData();
				}
				aux = aux.getNext();
			}
		}
		return exit;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> auxHead = head;
		if (head.isNIL()) {
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<>(element, new SingleLinkedListNode<>());
			newHead.next = head;
			head = newHead;
		} else {
			while (!auxHead.next.isNIL()) {
				auxHead = auxHead.next;
			}
			SingleLinkedListNode newNode = new SingleLinkedListNode<>(element, new SingleLinkedListNode<>());
			newNode.next = auxHead.next;
			auxHead.next = newNode;
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (head.data.equals(element)) {
				head = head.next;
			} else {
				SingleLinkedListNode<T> aux = head;
				SingleLinkedListNode<T> previous = null;
				while (!aux.isNIL() && aux.data != element) {
					previous = aux;
					aux = aux.next;
				}
				if (!aux.isNIL()) {
					previous.next = aux.next;
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] exit = (T[]) new Object[size()];
		if (!isEmpty()) {
			SingleLinkedListNode<T> aux = head;
			int i = 0;
			while (!aux.isNIL()) {
				exit[i] = aux.data;
				aux = aux.next;
				i += 1;
			}
		}
		return exit;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
