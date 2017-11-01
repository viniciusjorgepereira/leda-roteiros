package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex < rightIndex) {
			int posicaoPivot = separar(array, leftIndex, rightIndex);
			sort(array, leftIndex, posicaoPivot - 1);
			sort(array, posicaoPivot + 1, rightIndex);
		}
	}

	private int separar(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];
		int inicio = leftIndex + 1;
		int fim = rightIndex;
		while (inicio <= fim) {
			if (array[inicio].compareTo(pivot) <= 0) {
				inicio += 1;
			} else if (array[fim].compareTo(pivot) > 0) {
				fim -= 1;
			} else {
				Util.swap(array, inicio, fim);
				inicio += 1;
				fim -= 1;
			}
		}
		array[leftIndex] = array[fim];
		array[fim] = pivot;
		return fim;
	}
}
