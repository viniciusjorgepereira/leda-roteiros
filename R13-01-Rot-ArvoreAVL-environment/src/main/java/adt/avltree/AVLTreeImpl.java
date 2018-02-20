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
			return 1;
		}
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (Math.abs(balance) > 1) {
			rotation(node);
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (!parent.isEmpty()) {
			parent = (BSTNode<T>) parent.getParent();
			rebalance((BSTNode<T>) parent);
		}
	}

	private void rotation(BSTNode<T> root) {
		// Left-left || Condicao 1
		// Rotacao para direita
		if ((pendingLeft(root) && pendingLeft((BSTNode<T>) root.getLeft()))
				|| (pendingLeft(root) && noPending((BSTNode<T>) root.getLeft()))) {
			Util.rightRotation(root);
		}
		// Right-right || Condicao 2
		// Rotacao para esquerda
		else if ((pendingRight(root) && pendingRight((BSTNode<T>) root.getRight()))
				|| (pendingRight(root) && noPending((BSTNode<T>) root.getRight()))) {
			Util.leftRotation(root);
		}
		// Left-right || Condicao 3
		// Rotacao Esquerda no Filho e Direita no Pai
		else if (pendingLeft(root) && pendingRight((BSTNode<T>) root.getLeft())) {
			Util.rightRotation(root);
			Util.leftRotation((BSTNode<T>) root.getLeft());
		}
		// Right-left || Condicao 4
		// Rotacao Direita no Filho e Esquerda no Pai
		else if (pendingRight(root) && pendingLeft((BSTNode<T>) root.getRight())) {
			Util.leftRotation(root);
			Util.rightRotation((BSTNode<T>) root.getRight());
		}
	}

	private boolean pendingRight(BSTNode<T> node) {
		return height(node) < 0;
	}

	private boolean noPending(BSTNode<T> node) {
		return !pendingLeft(node) && !pendingRight(node);
	}

	private boolean pendingLeft(BSTNode<T> node) {
		return height(node) > 0;
	}

	@Override
	public void insert(T element) {
		insert(this.root, element, new BSTNode<>());
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
		} else {
			rebalance(node);
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = this.search(element);
		remove(node);
	}

	private void remove(BSTNode<T> node) {
		if (node == null || node.isEmpty()) {
			return;
		}
		if (node.isLeaf()) {
			if (node == this.root) {
				this.root = new BSTNode<T>();
			} else if (isLeftChild(node, node.getParent())) {
				node.getParent().setLeft(new BSTNode<T>());
			} else {
				node.getParent().setRight(new BSTNode<T>());
			}
		} else if (justRightChild(node)) {
			if (node == this.root) {
				this.root = (BSTNode<T>) node.getRight();
			} else {
				if (isLeftChild(node, node.getParent())) {
					node.getParent().setLeft(node.getRight());
				} else {
					node.getParent().setRight(node.getRight());
				}
				node.getRight().setParent(node.getParent());
			}
		} else if (justLeftChild(node)) {
			if (node == this.root) {
				this.root = (BSTNode<T>) node.getLeft();
			} else {
				if (isLeftChild(node, node.getParent())) {
					node.getParent().setLeft(node.getLeft());
				} else {
					node.getParent().setRight(node.getLeft());
				}
				node.getLeft().setParent(node.getParent());
			}
		} else {
			BSTNode<T> auxNode = this.sucessor(node.getData());
			if (auxNode == null) {
				auxNode = this.predecessor(node.getData());
			}
			T aux = node.getData();
			node.setData(auxNode.getData());
			auxNode.setData(aux);
			this.remove(auxNode);
		}
		rebalanceUp(node);
	}
}
