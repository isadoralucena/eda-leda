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
		int indexMaximumInteger = leftIndex;
		for(int i = leftIndex + 1; i <= rightIndex; i++){
			if (array[i] > indexMaximumInteger){
				indexMaximumInteger = i;
			}
		}

		Integer[] auxArray = new Integer[indexMaximumInteger];

		for(int i = leftIndex; i <= rightIndex; i++){
			auxArray[array[i]] += 1;
		}

		for(int i = leftIndex + 1; i <= rightIndex; i++){
			auxArray[i] += auxArray[i-1];
		}

		for(int i = rightIndex; i >= leftIndex; i--){
			int element = array[i];
			int occurence = auxArray[element] - auxArray[element -1];
			array[occurence] = auxArray[element];
			auxArray[element] -= 1;
		}
	}
}
