package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (!node.isEmpty()) {
			return height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
		} else {
			return 0;
		}
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			int balance = calculateBalance(node);
			if (Math.abs(balance) > 1) {
				rotation(node);
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (!parent.isEmpty()) {
			rebalance((BSTNode<T>) parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}

	private void rotation(BSTNode<T> root) {
		int balance = calculateBalance(root);
		BSTNode<T> aux = null;

		if (balance > 0) {
			if (pendingLeft((BSTNode<T>) root.getLeft())) {
				root.setLeft(Util.leftRotation((BSTNode<T>) root.getLeft()));
			}
			aux = Util.rightRotation(root);
		} else {
			if (pendingRight((BSTNode<T>) root.getRight())) {
				root.setRight(Util.rightRotation((BSTNode<T>) root.getRight()));
			}
			aux = Util.leftRotation(root);
		}

		if (this.root.equals(root)) {
			this.root = aux;
		} else {
			if (aux.getParent().getLeft().equals(root)) {
				aux.getParent().setLeft(aux);
			} else {
				aux.getParent().setRight(aux);
			}
		}
	}

	private boolean pendingRight(BSTNode<T> node) {
		return height(node) < 0;
	}

	private boolean pendingLeft(BSTNode<T> node) {
		return height(node) > 0;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(this.root, element, new BSTNode<>());
			this.rebalance(search(element));
		}
	}

	private void insert(BSTNode<T> node, T element, BSTNode<T> parent) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.setParent(parent);
		} else if (element.compareTo(node.getData()) < 0) {
			insert((BSTNode<T>) node.getLeft(), element, node);
		} else if (element.compareTo(node.getData()) > 0) {
			insert((BSTNode<T>) node.getRight(), element, node);
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = this.search(element);
		remove(node);
	}

	private void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				this.rebalanceUp(node);
			} else if (hasOneChild(node)) {
				if (node.getParent() != null) {
					if (!node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} else {
					if (node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
					} else {
						this.root = (BSTNode<T>) node.getLeft();
					}
					this.root.setParent(null);
				}
				this.rebalanceUp(node);
			} else {
				T sucessor = sucessor(node.getData()).getData();
				remove(sucessor);
				node.setData(sucessor);
			}
		}
	}

	protected boolean hasOneChild(BSTNode<T> node) {
		return (node.getLeft().isEmpty() && !node.getRight().isEmpty())
				|| (!node.getLeft().isEmpty() && node.getRight().isEmpty());
	}
}
