package sorting.variationsOfInsertionsort;

import sorting.AbstractSorting;
import util.Util;

public class RecursiveInsertionSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * Implementacaoo RECURSIVA do insertion sort. Para isso, tente definir o 
	 * caso base do algoritmo e depois o caso recursivo, que reduz o problema 
	 * para uma entrada menor em uma chamada recursiva. Seu algoritmo deve 
	 * ter complexidade quadratica O(n^2).
	 * 
	 * Restrições:
	 *  - voce NAO pode utilizar array auxiliar
	 *  - voce pode utilizar variaveis temporarias
	 *  - voce NAO pode declarar novos atributos na classe
	 *  - para as trocas no array, utilize o metodo Util.swap
	 */
	@Override
        public void sort(T[] array, int leftIndex, int rightIndex) {
			if(array != null && leftIndex >= 0 && leftIndex <= rightIndex && rightIndex <= array.length - 1 && leftIndex < array.length) {
				sort(array, leftIndex, rightIndex-1);

				T valueToInsert = array[rightIndex];
				int insertionIndex = rightIndex;
				while(insertionIndex - 1 >= leftIndex && valueToInsert.compareTo(array[insertionIndex-1]) < 0){
					array[insertionIndex] = array[insertionIndex-1];
					insertionIndex--;
				}
				array[insertionIndex] = valueToInsert;
			}
    }
}
     