package adt.bst;

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
									&& this.isSimilar((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft());
		}
		return answer;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		return this.orderStatistic((BSTNode<T>) tree.getRoot(), k);
	}

	private T orderStatistic(BSTNode<T> node, int k){
		T answer = node.getData();
		if(!node.isEmpty()){
			
		}
		return answer;
	}
}
