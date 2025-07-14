package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguintes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > 0 && leftIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex){
			int maximumInteger = array[leftIndex];
			int minimumInteger = array[leftIndex];

			for(int i = leftIndex + 1; i <= rightIndex; i++){
				if (array[i] > maximumInteger){
					maximumInteger = array[i];
				}
				if (array[i] < minimumInteger){
					minimumInteger = array[i];
				}
			}

			int offset = 0 - minimumInteger;

			Integer[] cumulativeSum = new Integer[1 + maximumInteger + offset];

			for(int i = 0; i < cumulativeSum.length; i++){
				cumulativeSum[i] = 0;
			}

			for(int i = leftIndex; i <= rightIndex; i++){
				cumulativeSum[array[i] + offset] += 1;
			}

			for(int i = 1; i < cumulativeSum.length; i++){
				cumulativeSum[i] += cumulativeSum[i-1];
			}

			Integer[] orderedArray = new Integer[array.length];

			for(int i = leftIndex; i <= rightIndex; i++){
				orderedArray[cumulativeSum[array[i] + offset] - 1 ] = array[i];
				cumulativeSum[array[i] + offset] -= 1;
			}

			int j = 0;
			for(int i = leftIndex; i <= rightIndex; i++){
				array[i] = orderedArray[j];
				j++;
			}
		}
	}
}