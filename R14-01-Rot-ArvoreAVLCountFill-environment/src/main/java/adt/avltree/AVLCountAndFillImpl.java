package adt.avltree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if (this.root.isEmpty()) {
			List<T> list = Arrays.asList(array);
			Collections.sort(list);
			while (!list.isEmpty()) {
				int index = (list.size() - 1) / 2;
				this.insert(list.get(index));
				list.remove(index);
			}
		} else {

		}
	}

	@Override
	protected void rotation(BSTNode<T> root) {
		BSTNode<T> att = new BSTNode<>();
		int balance = calculateBalance(root);
		if (balance > 0) {
			if (pendingLeft((BSTNode<T>) root.getLeft())) {
				root.setLeft(Util.leftRotation((BSTNode<T>) root.getLeft()));
				this.LRcounter += 1;
			}
			this.LLcounter += 1;
			att = Util.rightRotation(root);
		} else {
			if (pendingRight((BSTNode<T>) root.getRight())) {
				root.setRight(Util.rightRotation((BSTNode<T>) root.getRight()));
				this.RLcounter += 1;
			}
			this.RRcounter += 1;
			att = Util.leftRotation(root);
		}

		if (this.root.equals(root)) {
			this.root = att;
		} else {
			if (att.getParent().getLeft().equals(root)) {
				att.getParent().setLeft(att);
			} else {
				att.getParent().setRight(att);
			}
		}
	}

}
