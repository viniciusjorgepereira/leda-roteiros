package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}
		if (element != null) {
			if (isEmpty()) {
				this.head = 0;
				this.tail = 0;
				this.array[0] = element;
			} else {
				this.tail = this.array.length - 1 - this.elements;
				this.array[this.tail] = element;
			}
			this.elements += 1;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		T valor = this.array[head];
		if (this.head == this.tail) {
			this.head = -1;
			this.tail = -1;
		} else {
			this.head = (this.head + 1) % this.array.length;
		}
		this.elements -= 1;
		return valor;
	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		} else {
			return this.array[head];
		}
	}

	@Override
	public boolean isEmpty() {
		boolean saida = false;
		if (this.head < 0 && this.tail < 0) {
			saida = true;
		}
		return saida;
	}

	@Override
	public boolean isFull() {
		boolean saida = false;
		if (this.elements == this.array.length) {
			saida = true;
		}
		return saida;
	}
}
