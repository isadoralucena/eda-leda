package adt.avltree;

import java.util.Arrays;
import java.util.HashSet;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			int balance = calculateBalance(node);
			BSTNode<T> left = (BSTNode<T>) node.getLeft();
    	BSTNode<T> right = (BSTNode<T>) node.getRight();
			BSTNode<T> newRoot = null;

			if (balance > 1) {
				int leftBalance = calculateBalance(left);

				if (leftBalance >= 0) {
					newRoot = Util.rightRotation(node);
					this.LLcounter++;
				} else {
					Util.leftRotation(left);
					newRoot = Util.rightRotation(node);
					this.LRcounter++;
				}
			} else if (balance < -1) {
				int rightBalance = calculateBalance(right);

				if (rightBalance <= 0) {
					newRoot = Util.leftRotation(node);
					this.RRcounter++;
				} else {
					Util.rightRotation(right);
					newRoot = Util.leftRotation(node);
					this.RLcounter++;
				}
			}
			if (node == this.root && newRoot != null) {
				this.root = newRoot;
			}
		}
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array != null && array.length != 0) {

			T[] bstArray = this.order();
			HashSet<T> set = new HashSet<>();
			for (T elem : bstArray) set.add(elem);
			for (T elem : array) set.add(elem);

			array = (T[]) set.toArray(new Comparable[0]);
			Arrays.sort(array);

			this.root = new BSTNode<>();
			
			int level = 0;
			while (fillWithoutRebalance(0, array.length, level, array)) {
				level++;
			}
		}
	}

	private boolean fillWithoutRebalance(int left, int right, int level, T[] array) {
		boolean filled = false;

		if (right > left) {
			int middle = left + (right - left) / 2;
			if (level == 0) {
				insert(array[middle]);
				filled = true;
			} else {
				filled = fillWithoutRebalance(left, middle, level - 1, array);
				filled = fillWithoutRebalance(middle + 1, right, level - 1, array);
			}
		}

		return filled;
	}
}