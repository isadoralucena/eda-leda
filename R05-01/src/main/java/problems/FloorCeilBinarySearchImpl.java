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
		Integer answer = binarySearchFloor(array, x, 0, array.length - 1);
		return answer;
	}

	private Integer binarySearchFloor(Integer[] array, Integer x, int left, int right){
		int middle = (left + right)/2;
		Integer answer =  null;
		if(array[middle] <= x){
			answer = binarySearchFloor(array, x, left -  1, right);
		} else if (array[middle] > x){
			answer = binarySearchFloor(array, x, left, right - 1);
		}
		return answer;
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		// TODO implement your code here
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
