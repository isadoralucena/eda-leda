package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearchImpl implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		Integer answer = null;
		if (array != null && array.length > 0) {
			answer = binarySearchFloor(array, x, 0, array.length - 1);
		}
		return answer;
	}

	private Integer binarySearchFloor(Integer[] array, Integer x, int left, int right){
		Integer answer =  null;
		if(left >= 0 && right < array.length){
			while(left <= right){
				int middle = (left + right)/2;
				if(array[middle] <= x){
					answer = array[middle];
					left = middle + 1;
				} else {
					right = middle - 1;
				}
			}
		}
		return answer;
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		Integer answer = null;
		if (array != null && array.length > 0) {
			answer = binarySearchCeil(array, x, 0, array.length - 1);
		}
		return answer;
	}

	private Integer binarySearchCeil(Integer[] array, Integer x, int left, int right){
		Integer answer =  null;
		if(left >= 0 && right < array.length){
			while(left <= right){
				int middle = (left + right)/2;
				if(array[middle] >= x){
					answer = array[middle];
					right = middle - 1;
				} else {
					left = middle + 1;
				}
			}
		}
		return answer;
	}
}
