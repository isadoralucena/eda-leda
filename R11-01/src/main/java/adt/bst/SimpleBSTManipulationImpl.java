package adt.bst;

import java.util.HashMap;
import java.util.Map;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return this.equals((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}

	private boolean equals(BSTNode<T> node1, BSTNode<T> node2){
		boolean answer = false;
		if(node1.isEmpty() || node2.isEmpty()){
			answer = node1.isEmpty() && node2.isEmpty();
		}else{
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

	private boolean isSimilar(BSTNode<T> node1, BSTNode<T> node2){
		boolean answer = false;
		if(node1.isEmpty() || node2.isEmpty()){
			answer = node1.isEmpty() && node2.isEmpty();
		}else{
			answer = !node1.isEmpty() && !node2.isEmpty()
									&& this.isSimilar((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft())
									&& this.isSimilar((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
		}
		return answer;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		T answer = null;
		if (k >= 1) {
			Map<BSTNode<T>,Integer> subtreeSizes = new HashMap<>();
    	computeSubtreeSizes((BSTNode<T>) tree.getRoot(), subtreeSizes);
			answer = this.orderStatistic((BSTNode<T>) tree.getRoot(), k, subtreeSizes);
		}
		return answer;
	}

	private T orderStatistic(BSTNode<T> node, int k, Map<BSTNode<T>,Integer> sizes){
		T answer = null;

		if(!node.isEmpty()){
			int sizeLeft = sizes.getOrDefault(node.getLeft(), 0);
			if (k == sizeLeft + 1) {
        answer = node.getData();
			} else if (k <= sizeLeft) {
				answer = orderStatistic((BSTNode<T>) node.getLeft(), k, sizes);
			} else {
				answer = orderStatistic((BSTNode<T>) node.getRight(), k - (sizeLeft+1), sizes);
			}
		}
		return answer;
	}

	/*
	 * Computes and stores the size of each subtree rooted at the given node
	 * The size of a subtree is the number of non-empty nodes it contains
	 * This helps improve the efficiency of accessing subtree sizes later
	 */
	private int computeSubtreeSizes(BSTNode<T> node, Map<BSTNode<T>,Integer> sizes) {
		int totalSize = 0;
    if (node != null && !node.isEmpty()) {
			int leftSize = computeSubtreeSizes((BSTNode<T>) node.getLeft(), sizes);
			int rightSize = computeSubtreeSizes((BSTNode<T>) node.getRight(), sizes);
			totalSize = 1 + leftSize + rightSize;
			sizes.put(node, totalSize);
		}
		return totalSize;
	}
}
