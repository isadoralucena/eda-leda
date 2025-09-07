package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double number) {
		for (Integer integer : array) {
			this.insert(integer);
		}
		return this.floor(number, null);
	}

	private Integer floor(double number, Integer candidate) {
		Integer answer = candidate;
		Integer root = this.extractRootElement();

		if(root != null){
			if(root <= number && (candidate == null || root > candidate)){
				candidate = root;
			}
			answer = this.floor(number, candidate);
		}
		
		return answer;
	}


	@Override
	public Integer ceil(Integer[] array, double number) {
		for (Integer integer : array) {
			this.insert(integer);
		}
		return this.ceil(number, null);
	}

	private Integer ceil(double number, Integer candidate) {
		Integer answer = candidate;
		Integer root = this.extractRootElement();

		if(root != null){
			if(root >= number && (candidate == null || root < candidate)){
				candidate = root;
			}
			answer = this.ceil(number, candidate);
		}
		
		return answer;
	}
}
