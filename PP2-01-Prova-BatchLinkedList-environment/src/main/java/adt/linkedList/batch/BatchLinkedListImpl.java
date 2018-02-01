package adt.linkedList.batch;

import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.DoubleLinkedListNode;
import adt.linkedList.SingleLinkedListNode;
import util.GenericException;

/**
 * Manipula elementos da LinkedList em bloco (batch).
 * 
 * @author campelo
 * @author adalberto
 *
 * @param <T>
 */
public class BatchLinkedListImpl<T> extends DoubleLinkedListImpl<T> implements BatchLinkedList<T> {

	/*
	 * Nao modifique nem remova este metodo.
	 */
	public BatchLinkedListImpl() {
		head = new DoubleLinkedListNode<T>();
		last = (DoubleLinkedListNode<T>) head;
	}

	@Override
	public void inserirEmBatch(int posicao, T[] elementos) throws GenericException {
		if (posicao < 0 || elementos == null) {
			throw new GenericException();
		}
		int pos = 0;
		DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;
		encontrarElemento(pos, posicao, aux);
		// if (pos < posicao) {
		// throw new GenericException();
		// }
		aux = ((DoubleLinkedListNode<T>) aux).getPrevious();
		inserirLista(aux, elementos, (DoubleLinkedListNode<T>) aux.getNext());
		this.head = aux;
		this.last = encontrarLast((DoubleLinkedListNode<T>) this.head);
	}

	/*
	 * Liga o novo array setando os previous e os next dos novos elementos
	 */
	private void inserirLista(DoubleLinkedListNode<T> previous, T[] elementos, DoubleLinkedListNode<T> next) {
		DoubleLinkedListNode<T> prev = previous;
		DoubleLinkedListNode<T> nex = next;
		for (int i = 0; i < elementos.length - 1; i++) {
			((DoubleLinkedListNode<T>) elementos[i]).setPrevious(prev);
			((DoubleLinkedListNode<T>) elementos[i]).setNext((SingleLinkedListNode<T>) elementos[i + 1]);
			prev = (DoubleLinkedListNode<T>) elementos[i];
		}
		((DoubleLinkedListNode<T>) elementos[elementos.length - 1]).setNext(next);
	}

	/**
	 * Enconta o ultimo elemento da lista
	 */
	private DoubleLinkedListNode<T> encontrarLast(DoubleLinkedListNode<T> aux) {
		while (aux.getNext() != null) {
			aux = (DoubleLinkedListNode<T>) aux.getNext();
		}
		return aux;
	}

	/*
	 * Encontra o elemento na lista na lista
	 */
	private void encontrarElemento(int pos, int posicao, DoubleLinkedListNode<T> aux) {
		while (aux.getNext() != null && pos != posicao) {
			pos += 1;
			aux = (DoubleLinkedListNode<T>) aux.getNext();
		}
	}

	@Override
	public void removerEmBatch(int posicao, int quantidade) throws GenericException {
		if (posicao < 0) {
			throw new GenericException();
		}
		int pos = 0;
		DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;
		encontrarElemento(pos, posicao, aux);
		// if (pos < posicao) {
		// throw new GenericException();
		// }
		aux = aux.getPrevious();
		int qtd = 0;
		DoubleLinkedListNode<T> remove = (DoubleLinkedListNode<T>) aux.getNext();
		encontrarElemento(qtd, quantidade, remove);
		aux.setNext(remove.getNext());
		remove.setPrevious(aux);
		this.head = aux;
		this.last = encontrarLast((DoubleLinkedListNode<T>) this.head);
	}

	/*
	 * NAO MODIFIQUE NEM REMOVA ESTE METODO!!!
	 * 
	 * Use este metodo para fazer seus testes
	 * 
	 * Este metodo monta uma String contendo os elementos do primeiro ao ultimo,
	 * comecando a navegacao pelo Head
	 */
	public String toStringFromHead() {

		String result = "";
		DoubleLinkedListNode<T> aNode = (DoubleLinkedListNode<T>) getHead();

		while (!aNode.isNIL()) {

			if (!result.isEmpty()) {
				result += " ";
			}

			result += aNode.getData();
			aNode = (DoubleLinkedListNode<T>) aNode.getNext();

		}

		return result;
	}

	/*
	 * NAO MODIFIQUE NEM REMOVA ESTE METODO!!!
	 * 
	 * Use este metodo para fazer seus testes
	 * 
	 * Este metodo monta uma String contendo os elementos do primeiro ao ultimo,
	 * porem comecando a navegacao pelo Last
	 * 
	 * Este metodo produz o MESMO RESULTADO de toStringFromHead()
	 * 
	 */
	public String toStringFromLast() {

		String result = "";
		DoubleLinkedListNode<T> aNode = getLast();

		while (!aNode.isNIL()) {

			if (!result.isEmpty()) {
				result = " " + result;
			}

			result = aNode.getData() + result;
			aNode = (DoubleLinkedListNode<T>) aNode.getPrevious();

		}

		return result;
	}

	@Override
	public String toString() {
		return toStringFromHead();
	}
}
