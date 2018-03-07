package adt.skipList.fromBST;

import java.util.Arrays;

import adt.bst.BST;
import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.skipList.SkipListImpl;
import adt.skipList.SkipListNode;

public class SkipListFromBSTImpl extends SkipListImpl<Integer> implements SkipListFromBST<Integer> {

	public SkipListFromBSTImpl(int maxHeight) {
		super(maxHeight);
	}

	public void importFromBST(BST<Integer> bst) {
		Integer[] array = bst.preOrder();

		int altura = bst.height();
		this.root = resetSkip(this.root, altura);

		for (int i = 0; i < array.length; i++) {
			BSTNode<Integer> raiz = (BSTNode<Integer>) bst.getRoot();
			int alt = bst.height();
			int key = raiz.getData();
			bst.remove(key);
			insert(key, array[i], alt);
		}

	}

	private SkipListNode<Integer> resetSkip(SkipListNode<Integer> root, int altura) {
		return new SkipListNode<Integer>(Integer.MIN_VALUE, altura, null);
	}

	public static void main(String[] args) {
		BSTImpl bst = new BSTImpl<>();

		SkipListFromBSTImpl skip = new SkipListFromBSTImpl(5);
		skip.importFromBST(bst);

		bst.insert(3);
		bst.insert(8);
		bst.insert(25);
		bst.insert(47);

		System.out.println(Arrays.toString(bst.order()));

		System.out.println(Arrays.toString(skip.toArray()));

	}

}
