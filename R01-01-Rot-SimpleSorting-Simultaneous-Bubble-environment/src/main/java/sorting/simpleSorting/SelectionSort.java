package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for (int fixo = leftIndex; fixo < rightIndex; fixo++) {
			int menor = fixo;
			for (int i = menor + 1; i < rightIndex + 1; i++) {
				if (array[i].compareTo(array[menor]) < 0) {
					menor = i;
				}
			}
			if (menor != fixo) {
				Util.swap(array, fixo, menor);
			}
		}
	}
}
