package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve conectar
	 * todos os forward. Senao o ROOT eh inicializado com level=1 e o metodo deve
	 * conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	@Override
	public void insert(int key, T newValue, int height) {
		insert(this.root, key, newValue, height);
	}

	private void insert(SkipListNode<T> list, int key, T newValue, int height) {
		if (height < this.maxHeight) {
			SkipListNode<T>[] update = new SkipListNode[list.height()];
			SkipListNode<T> temp = list;
			for (int i = list.height() - 1; i >= 0; i--) {
				while (temp.forward[i] != null && temp.forward[i].key < key) {
					temp = temp.forward[i];
				}
				update[i] = temp;
			}
			temp = temp.forward[0];
			if (temp.key == key) {
				temp.value = newValue;
			} else {
				int v = height;
				temp = new SkipListNode<>(key, height, newValue);
				for (int i = 0; i < v; i++) {
					temp.forward[i] = update[i].forward[i];
					update[i].forward[i] = temp;
				}
			}
		}
	}

	@Override
	public void remove(int key) {
		remove(this.root, key);
	}

	private void remove(SkipListNode<T> list, int key) {
		SkipListNode<T>[] update = new SkipListNode[list.height()];
		SkipListNode<T> temp = list;
		for (int i = list.height() - 1; i >= 0; i--) {
			while (temp.forward[i] != null && temp.forward[i].key < key) {
				temp = temp.forward[i];
			}
			update[i] = temp;
		}
		temp = temp.forward[0];
		if (temp.key == key) {
			for (int i = 0; i < list.height(); i++) {
				if (update[i].forward[i] != temp) {
					break;
				}
				update[i].forward[i] = temp.forward[i];
			}
		}
	}

	@Override
	public int height() {
		SkipListNode<T> temp = this.root;
		int result = 0;
		while (temp.forward[0].key != Integer.MAX_VALUE) {
			temp = temp.forward[0];
			if (temp.height() > result) {
				result = temp.height();
			}
		}
		return result;
	}
	
	@Override
	public SkipListNode<T> search(int key) {
		if (this.root.key == key) {
			return this.root;
		}
		return search(this.root, key);
	}

	private SkipListNode<T> search(SkipListNode<T> list, int key) {
		if (this.NIL.key == key) {
			return this.NIL;
		} else {
			SkipListNode<T> temp = list;
			for (int i = list.height() - 1; i >= 0; i--) {
				while (temp.forward[i] != null && temp.forward[i].key < key) {
					temp = temp.forward[i];
				}
			}
			temp = temp.forward[0];
			if (temp.key == key) {
				return (SkipListNode<T>) temp;
			} else {
				return null;
			}
		}
	}

	@Override
	public int size() {
		return size(this.root);
	}

	private int size(SkipListNode<T> list) {
		SkipListNode<T> aux = list.getForward(0);
		int result = 0;
		while (aux.value != null) {
			aux = aux.forward[0];
			result += 1;
		}
		return result;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T>[] list = new SkipListNode[this.size() + 2];
		SkipListNode<T> temp = this.root;
		for (int i = 0; i < list.length; i++) {
			list[i] = temp;
			temp = temp.forward[0];
		}
		return list;
	}
}