package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementation of an AVL tree.
 * The AVLTree class extends BSTImpl. YOU NEED TO OVERRIDE THE IMPLEMENTATION
 * OF BSTImpl WITH YOUR OWN IMPLEMENTATION OR ELSE implement the following
 * methods that will be tested in the AVLTree class:
 * - insert
 * - preOrder
 * - postOrder
 * - remove
 * - height
 * - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	@Override
	public void insert(T element) {
		if (element != null) {
			this.insert(this.root, element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (element != null) {
			if (node.isEmpty()) {
				node.setData(element);
				BSTNode<T> leftNode = (BSTNode<T>) new BSTNode.Builder<T>()
						.data(null)
						.left(null)
						.right(null)
						.parent(node)
						.build();

				BSTNode<T> rightNode = (BSTNode<T>) new BSTNode.Builder<T>()
						.data(null)
						.left(null)
						.right(null)
						.parent(node)
						.build();
				node.setLeft(leftNode);
				node.setRight(rightNode);
			} else {
				if (element.compareTo(node.getData()) < 0) {
					insert((BSTNode<T>) node.getLeft(), element);
				} else if (element.compareTo(node.getData()) > 0) {
					insert((BSTNode<T>) node.getRight(), element);
				}
				this.rebalance(node);
			}
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> elementNode = search(element);
		if (element != null && !elementNode.isEmpty()) {
			if (elementNode.isLeaf()) {
				elementNode.setData(null);
				this.rebalanceUp(elementNode);
			} else if ((!elementNode.getLeft().isEmpty() && elementNode.getRight().isEmpty())
					|| (elementNode.getLeft().isEmpty() && !elementNode.getRight().isEmpty())) {
				if (!elementNode.equals(this.root)) {
					if (elementNode.getParent().getLeft().equals(elementNode)) {
						if (!elementNode.getLeft().isEmpty()) {
							elementNode.getParent().setLeft(elementNode.getLeft());
							elementNode.getLeft().setParent(elementNode.getParent());
						} else {
							elementNode.getParent().setLeft(elementNode.getRight());
							elementNode.getRight().setParent(elementNode.getParent());
						}
					} else {
						if (!elementNode.getLeft().isEmpty()) {
							elementNode.getParent().setRight(elementNode.getLeft());
							elementNode.getLeft().setParent(elementNode.getParent());
						} else {
							elementNode.getParent().setRight(elementNode.getRight());
							elementNode.getRight().setParent(elementNode.getParent());
						}
					}
				} else {
					if (!elementNode.getLeft().isEmpty()) {
						elementNode.getLeft().setParent(null);
						this.root = (BSTNode<T>) elementNode.getLeft();
					} else {
						elementNode.getRight().setParent(null);
						this.root = (BSTNode<T>) elementNode.getRight();
					}
				}
				this.rebalanceUp(elementNode);
			} else {
				T successor = sucessor(elementNode.getData()).getData();
				remove(successor);
				elementNode.setData(successor);
			}
		}
	}

	protected int calculateBalance(BSTNode<T> node) {
		int answer = 0;
		if (!node.isEmpty()) {
			answer = this.height((BSTNode<T>) node.getLeft()) - this.height((BSTNode<T>) node.getRight());
		}
		return answer;
	}


	/**
	 * Rebalances the given node if it violates AVL balance rules.
	 *
	 * The balance factor (BF) is defined as:
	 * BF = height(left subtree) - height(right subtree).
	 *
	 * Valid range: -1 <= BF <= 1.
	 * If BF is outside this range, one of the following rotations is applied:
	 *
	 * - LL (Left-Left): BF > 1 and left child's BF >= 0, single right rotation.
	 * - LR (Left-Right): BF > 1 and left child's BF < 0, double rotation (left on left child, then right).
	 * - RR (Right-Right): BF < -1 and right child's BF <= 0, single left rotation.
	 * - RL (Right-Left): BF < -1 and right child's BF > 0, double rotation (right on right child, then left).
	 *
	 * @param node the root of the subtree to check and rebalance
	 */
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
				} else {
					Util.leftRotation(left);
					newRoot = Util.rightRotation(node);
				}
			} else if (balance < -1) {
				int rightBalance = calculateBalance(right);

				if (rightBalance <= 0) {
					newRoot = Util.leftRotation(node);
				} else {
					Util.rightRotation(right);
					newRoot = Util.leftRotation(node);
				}
			}
			if (node == this.root && newRoot != null) {
				this.root = newRoot;
			}
		}
	}

	protected void rebalanceUp(BSTNode<T> node) {
		if (node.getParent() != null) {
			rebalance((BSTNode<T>) node.getParent());
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}
}
