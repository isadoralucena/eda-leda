package adt.bst;

import adt.bt.BTNode;

public class BSTNode<T extends Comparable<T>> extends BTNode<T> {
	public BSTNode() {
		super();
	}

	/**
	 * Example of using the Builder pattern to create a BSTNode object
	 * without calling the constructor directly.
	 *
	 * Example of creating an empty node:
	 *
	 * BSTNode<Integer> node = (BSTNode<Integer>) new BSTNode.Builder<Integer>()
	 * .data(null)
	 * .left(null)
	 * .right(null)
	 * .parent(null)
	 * .build();
	 */

	public static class Builder<T extends Comparable<T>> {
		T data;
		BTNode<T> left;
		BTNode<T> right;
		BTNode<T> parent;

		public BSTNode.Builder<T> data(T data) {
			this.data = data;
			return this;
		}

		public BSTNode.Builder<T> left(BTNode<T> left) {
			this.left = left;
			return this;
		}

		public BSTNode.Builder<T> right(BTNode<T> right) {
			this.right = right;
			return this;
		}

		public BSTNode.Builder<T> parent(BTNode<T> parent) {
			this.parent = parent;
			return this;
		}

		public BSTNode<T> build() {
			return new BSTNode<T>(this);
		}
	}

	private BSTNode(BSTNode.Builder<T> builder) {
		this.data = builder.data;
		this.left = builder.left;
		this.right = builder.right;
		this.parent = builder.parent;
	}
}
