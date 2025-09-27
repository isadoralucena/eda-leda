package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double number) {
		this.root = new BSTNode<>();
		Integer floor = null;

		if (array != null && array.length > 0) {
			floor = floor(array, 0, number, null);
		}

		return floor;

	}

	private Integer floor(Integer[] arr, int index, double target, Integer currentFloor) {
		Integer floorCandidate = currentFloor;

		if (index < arr.length) {
			Integer currentValue = arr[index];
			if (currentValue != null) {
				this.insert(currentValue);

				if (currentValue <= target && (floorCandidate == null || currentValue > floorCandidate)) {
					floorCandidate = currentValue;
				}
			}

			floorCandidate = floor(arr, index + 1, target, floorCandidate);
		}

		return floorCandidate;
	}

	@Override
	public Integer ceil(Integer[] array, double number) {
		this.root = new BSTNode<>();
		Integer ceil = null;

		if (array != null && array.length > 0) {
			ceil = ceil(array, 0, number, null);
		}

		return ceil;
	}

	private Integer ceil(Integer[] arr, int index, double target, Integer currentCeil) {
		Integer ceilCandidate = currentCeil;

		if (index < arr.length) {
			Integer currentValue = arr[index];

			if (currentValue != null) {
				this.insert(currentValue);

				if (currentValue >= target && (ceilCandidate == null || currentValue < ceilCandidate)) {
					ceilCandidate = currentValue;
				}
			}
			ceilCandidate = ceil(arr, index + 1, target, ceilCandidate);
		}
		return ceilCandidate;
	}
}
