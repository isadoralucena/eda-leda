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
		if (array.length > 0 && leftIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex){
			insertOrdered(array, leftIndex, rightIndex);
			sort(array, leftIndex + 1, rightIndex);
		}
	}

	private void insertOrdered(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex){
			if (array[rightIndex].compareTo(array[rightIndex-1]) < 0){
				Util.swap(array, rightIndex, rightIndex-1);
			}
			insertOrdered(array, leftIndex, rightIndex -1);
		}
	}
}
     