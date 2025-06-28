package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for(int i = leftIndex + 1; i <= rightIndex; i++){
			T valueToInsert = array[i];
			int insertionIndex = i;

			while (insertionIndex > leftIndex && array[insertionIndex - 1].compareTo(valueToInsert) > 0){
				array[insertionIndex] = array[insertionIndex - 1];
				insertionIndex--;
			}
			
    		array[insertionIndex] = valueToInsert;
		}
	}

}
