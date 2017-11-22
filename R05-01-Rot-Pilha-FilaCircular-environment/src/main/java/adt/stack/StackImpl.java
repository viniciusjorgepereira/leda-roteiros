package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if (isEmpty()) {
			return null;
		} else {
			return array[top];
		}
	}

	@Override
	public boolean isEmpty() {
		boolean saida = false;
		if (top < 0) {
			saida = true;
		}
		return saida;
	}

	@Override
	public boolean isFull() {
		boolean saida = false;
		if (top == array.length - 1) {
			saida = true;
		}
		return saida;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}
		if (element != null) {
			top += 1;
			if (!isFull()) {
				this.array[top] = element;
			}
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		} else {
//			this.top -= 1;
			return this.array[top--];
		}
	}
}
