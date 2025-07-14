package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > 0 && leftIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex){
			int maximumInteger = array[leftIndex];
			for(int i = leftIndex + 1; i <= rightIndex; i++){
				if (array[i] > maximumInteger){
					maximumInteger = array[i];
				}
			}

			Integer[] cumulativeSum = new Integer[maximumInteger+1];

			for(int i = 0; i < cumulativeSum.length; i++){
				cumulativeSum[i] = 0;
			}

			for(int i = leftIndex; i <= rightIndex; i++){
				cumulativeSum[array[i]] += 1;
			}

			for(int i = 1; i < cumulativeSum.length; i++){
				cumulativeSum[i] += cumulativeSum[i-1];
			}

			Integer[] orderedArray = new Integer[array.length];

			for(int i = leftIndex; i <= rightIndex; i++){
				orderedArray[cumulativeSum[array[i]] - 1] = array[i];
				cumulativeSum[array[i]] -= 1;
			}

			int j = 0;
			for(int i = leftIndex; i <= rightIndex; i++){
				array[i] = orderedArray[j];
				j++;
			}
		}
	}
}
