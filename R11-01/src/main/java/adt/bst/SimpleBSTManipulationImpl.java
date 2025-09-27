package adt.bst;

/**
 * Esta é a unica classe que pode ser modificada
 * 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return this.equals((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}

	private boolean equals(BSTNode<T> node1, BSTNode<T> node2) {
		boolean answer = false;
		if (node1.isEmpty() || node2.isEmpty()) {
			answer = node1.isEmpty() && node2.isEmpty();
		} else {
			answer = node1.getData().equals(node2.getData())
					&& this.equals((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft())
					&& this.equals((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
		}
		return answer;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return this.isSimilar((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}

	private boolean isSimilar(BSTNode<T> node1, BSTNode<T> node2) {
		boolean answer = false;
		if (node1.isEmpty() || node2.isEmpty()) {
			answer = node1.isEmpty() && node2.isEmpty();
		} else {
			answer = !node1.isEmpty() && !node2.isEmpty()
					&& this.isSimilar((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft())
					&& this.isSimilar((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
		}
		return answer;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		T result = null;
		if (tree != null && k > 0 && k <= tree.size()) {
			int[] count = { 0 };
			result = inOrderFind((BSTNode<T>) tree.getRoot(), k, count);
		}
		return result;
	}

	private T inOrderFind(BSTNode<T> node, int k, int[] count) {
		T result = null;
		if (node != null && !node.isEmpty()) {
			result = inOrderFind((BSTNode<T>) node.getLeft(), k, count);

			if (result == null) {
				count[0]++;
				if (count[0] == k) {
					result = node.getData();
				} else {
					result = inOrderFind((BSTNode<T>) node.getRight(), k, count);
				}
			}
		}
		return result;
	}
}
