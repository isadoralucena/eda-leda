package orderStatistic;

import java.util.PriorityQueue;

public class OrderStatisticsHeapImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Existem diversas formas de se calcular uma estatistica de ordem. 
	 * Voce deve fazer isso usando uma heap com as seguintes restrições:
	 * - nenhuma cópia ou estrutura de array auxiliar será permitida, exceto o uso de
	 *   uma PriorityQueue
	 * - caso a estatística de ordem procurada nao exista no array o método deve retornar null 
	 * 
	 * @param array
	 * @param k
	 * @return T
	 */
	
	@Override
	public T getOrderStatistics(T[] array, int k) {
		T answer = null;
		if(k >= 1 && k <= array.length){
			PriorityQueue<T> heap = new PriorityQueue<T>();
			for (T item : array) {
				heap.add(item);
			}
			for (int i = k; i > 0; i--) {
				answer = heap.poll();
			}
		}
		return answer;
	}
}
