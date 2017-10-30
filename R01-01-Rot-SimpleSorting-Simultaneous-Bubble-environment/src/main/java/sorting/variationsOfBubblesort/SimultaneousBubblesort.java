package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import sorting.simpleSorting.BubbleSort;

/**
 * This algorithm applies two bubblesorts simultaneously. In a same iteration, a
 * bubblesort pushes the greatest elements to the right and another bubblesort
 * pushes the smallest elements to the left. At the end of the first iteration,
 * the smallest element is in the first position (index 0) and the greatest
 * element is the last position (index N-1). The next iteration does the same
 * from index 1 to index N-2. And so on. The execution continues until the array
 * is completely ordered.
 */
public class SimultaneousBubblesort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		BubbleSort<T> bubble = new BubbleSort<T>();
		bubble.sort(array, leftIndex, (int) Math.floor(rightIndex / 2.0));
		bubble.sort(array, (int) Math.ceil(rightIndex / 2.0), rightIndex);
	}
}
