package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * Performs a left rotation on the given node, elevating its right child and
	 * adjusting pointers.
	 * Used in the RR (Right-Right) case in AVL trees.
	 *
	 * @param root the node to be rotated
	 * @return the node that became the new root after rotation
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> root) {
		BSTNode<T> newRoot = root;

		if (root != null && !root.isEmpty() && !root.getRight().isEmpty()) {
			BSTNode<T> pivot = (BSTNode<T>) root.getRight();

			root.setRight(pivot.getLeft());
			if (!pivot.getLeft().isEmpty()) {
				pivot.getLeft().setParent(root);
			}

			pivot.setLeft(root);
			pivot.setParent(root.getParent());
			root.setParent(pivot);

			if (pivot.getParent() != null) {
				if (pivot.getParent().getLeft().equals(root)) {
					pivot.getParent().setLeft(pivot);
				} else {
					pivot.getParent().setRight(pivot);
				}
			}
			newRoot = pivot;
		}

		return newRoot;
	}

	/**
	 * Performs a right rotation on the given node, elevating its left child and
	 * adjusting pointers.
	 * Used in the LL (Left-Left) case in AVL trees.
	 *
	 * @param root the node to be rotated
	 * @return the node that became the new root after rotation
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> root) {
		BSTNode<T> newRoot = root;

		if (root != null && !root.isEmpty() && !root.getLeft().isEmpty()) {
			BSTNode<T> pivot = (BSTNode<T>) root.getLeft();

			root.setLeft(pivot.getRight());
			if (!pivot.getRight().isEmpty()) {
				pivot.getRight().setParent(root);
			}

			pivot.setRight(root);
			pivot.setParent(root.getParent());
			root.setParent(pivot);

			if (pivot.getParent() != null) {
				if (pivot.getParent().getLeft().equals(root)) {
					pivot.getParent().setLeft(pivot);
				} else {
					pivot.getParent().setRight(pivot);
				}
			}
			newRoot = pivot;
		}

		return newRoot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
